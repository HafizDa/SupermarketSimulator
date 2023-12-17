/**
 * Generates random arrival times for customers in the supermarket simulation.
 * Arrival times are generated based on a specified distribution.
 */
package com.supermarket.simulation.core;

import java.util.Random;

/**
 * Generates random arrival times for customers in the supermarket simulation.
 * Arrival times are generated based on a specified distribution.
 */
public class ArrivalGenerator {
    private double lambda;

    /**
     * A Random object used to generate random numbers for the arrival times.
     */
    private Random random;

    /**
     * Constructor for the ArrivalGenerator class.
     * @param lambda The average number of customers arriving per unit time.
     */
    public ArrivalGenerator(double lambda) {
        this.lambda = lambda;
        this.random = new Random();
    }

    /**
     * Generates a new arrival event with a random arrival time.
     * The arrival time is generated based on an exponential distribution with rate lambda.
     * @return The generated arrival event.
     */
    public Event generateArrival() {
        double interArrivalTime = -Math.log(1 - random.nextDouble()) / lambda;
        double arrivalTime = Clock.getInstance().getClock() + interArrivalTime;
        return new Event(arrivalTime, EventType.ARRIVAL);
    }
}