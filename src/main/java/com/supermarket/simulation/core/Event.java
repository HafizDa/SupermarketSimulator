/**
 * Represents an event in the supermarket simulation.
 * Events are scheduled to occur at specific times and trigger specific actions.
 */
package com.supermarket.simulation.core;

/**
 * Represents an event in the supermarket simulation.
 * Events are scheduled to occur at specific times and trigger specific actions.
 */
public class Event implements Comparable<Event> {
    private double time;
    private EventType type;

    /**
     * Constructor for the Event class.
     * @param time The time at which the event is scheduled to occur.
     * @param type The type of the event.
     */
    public Event(double time, EventType type) {
        this.time = time;
        this.type = type;
    }

    /**
     * Gets the time at which the event is scheduled to occur.
     * @return The time of the event.
     */
    public double getTime() {
        return time;
    }

    /**
     * Gets the type of the event.
     * @return The type of the event.
     */
    public EventType getType() {
        return type;
    }

    /**
     * Returns a string representation of the event.
     * @return A string representation of the event.
     */
    @Override
    public String toString() {
        return time + " [" + type + "]";
    }

    /**
     * Compares this event with another event based on their times.
     * @param e The event to be compared.
     * @return A negative integer, zero, or a positive integer as this event's time is less than, equal to, or greater than the specified event's time.
     */
    @Override
    public int compareTo(Event e) {
        if (time < e.time)
            return -1;
        else if (time > e.time)
            return 1;
        return 0;
    }
}