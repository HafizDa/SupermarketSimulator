/**
 * Represents the clock used in the supermarket simulation.
 * The clock keeps track of the current simulation time.
 */
package com.supermarket.simulation.core;
/**
 * Represents the clock used in the supermarket simulation.
 * The clock keeps track of the current simulation time.
 */

public class Clock {
    private static Clock instance;
    private double currentTime;

    /**
     * Private constructor for the Clock class.
     * Initializes the current time to the system's current time in milliseconds.
     */
    private Clock() {
        currentTime = System.currentTimeMillis();
    }

    /**
     * Gets the singleton instance of the Clock class.
     * If the instance does not exist, it is created.
     * @return The singleton instance of the Clock class.
     */
    public static synchronized Clock getInstance() {
        if (instance == null) {
            instance = new Clock();
        }
        return instance;
    }

    /**
     * Gets the current simulation time.
     * @return The current simulation time.
     */
    public double getClock() {
        return currentTime;
    }
}