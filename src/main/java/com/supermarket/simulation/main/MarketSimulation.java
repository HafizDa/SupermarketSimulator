/**
 * Represents the overall market simulation, coordinating queues, service points, and the graphical user interface.
 * This class initializes and runs the simulation with specified parameters.
 */

package com.supermarket.simulation.main;

import com.supermarket.simulation.db.CustomerDAO;
import com.supermarket.simulation.core.*;

import com.supermarket.simulation.service.ServicePoint;

import java.sql.Connection;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * Represents the overall market simulation, coordinating queues, service points, and the graphical user interface.
 * This class initializes and runs the simulation with specified parameters.
 */
public class MarketSimulation implements Runnable {
    /**
     * The queue of customers in the supermarket.
     * The queue is shared by all service points.
     * The queue is also shared with the graphical user interface.
     * The queue is initialized in the constructor.
     * The queue is updated by the service points.
     */
    private final CustomerQueue queue;
    private final ServicePoint[] servicePoints;
    private final ScheduledExecutorService executor;
    private final TotalSum totalSum;
    private final ArrivalGenerator arrivalGenerator;
    private final CustomerDAO customerDAO;
    private final Connection dbConnection;

    /**
     * Constructor for the MarketSimulation class.
     *
     * @param numServicePoints The number of service points in the supermarket.
     * @param queue            The queue of customers in the supermarket.
     * @param lambda           The average number of customers arriving per unit time.
     * @param dbConnection     The database connection.
     */
    public MarketSimulation(int numServicePoints, CustomerQueue queue, double lambda, Connection dbConnection) {
        this.queue = queue;
        this.servicePoints = new ServicePoint[numServicePoints];
        this.executor = Executors.newScheduledThreadPool(numServicePoints);
        this.totalSum = new TotalSum(queue);
        this.arrivalGenerator = new ArrivalGenerator(lambda);
        this.dbConnection = dbConnection;
        this.customerDAO = new CustomerDAO();
        initializeServicePoints();
    }

    /**
     * Constructor for the MarketSimulation class with default lambda value.
     *
     * @param numServicePoints The number of service points in the supermarket.
     * @param dbConnection     The database connection.
     */
    public MarketSimulation(int numServicePoints, Connection dbConnection) {
        this(numServicePoints, new CustomerQueue(dbConnection), 1, dbConnection);
    }

    /**
     * Initializes the service points.
     */

    private void initializeServicePoints() {
        for (int i = 0; i < servicePoints.length; i++) {
            servicePoints[i] = new ServicePoint(i + 1, queue, totalSum);
        }
    }

    /**
     * The run method for the Runnable interface.
     * This method starts the simulation.
     */

    @Override
    public void run() {
        // Schedule tasks for each service point
        for (ServicePoint servicePoint : servicePoints) {
            executor.scheduleWithFixedDelay(servicePoint, 0, 1, TimeUnit.SECONDS);
        }

        // Simulate customer arrivals
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Event arrivalEvent = arrivalGenerator.generateArrival();
                double arrivalTime = arrivalEvent.getTime();

                // Wait until the arrival time
                while (Clock.getInstance().getClock() < arrivalTime) {
                    Thread.sleep(1000); // Sleep for a while before checking the time again
                }

                // Customer arrives
                // Randomly will select a service point for the customer
                int randomServicePoint = (int) (Math.random() * servicePoints.length);
                Customer newCustomer = new Customer(queue.getCustomerNumber());
                servicePoints[randomServicePoint].addCustomer(newCustomer);

                // this will save customer information to the database
                CustomerDAO.insertCustomer(newCustomer.getNumber(), newCustomer.getArrivalTime(), newCustomer.getNumberOfItems());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }


    /**
     * Stops the simulation.
     */
    public void stopSimulation() {
        // Stop the scheduled executor and interrupt the simulation thread
        executor.shutdownNow();
        Thread.currentThread().interrupt();
    }
}
