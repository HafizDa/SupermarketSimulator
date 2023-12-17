/**
 * Represents a list of events in the supermarket simulation.
 * The event list is responsible for managing and prioritizing events based on their scheduled times.
 */
package com.supermarket.simulation.core;

import java.util.PriorityQueue;

/**
 * Represents a list of events in the supermarket simulation.
 * The event list is responsible for managing and prioritizing events based on their scheduled times.
 */
public class EventList {
    private final PriorityQueue<Event> events;

    /**
     * Constructor for the EventList class.
     * Initializes the priority queue of events.
     */
    public EventList() {
        this.events = new PriorityQueue<>();
    }

    /**
     * Adds an event to the event list.
     * @param event The event to be added.
     */
    public void addEvent(Event event) {
        events.add(event);
    }

    /**
     * Retrieves and removes the next event from the event list.
     * The next event is the one with the lowest scheduled time.
     * @return The next event, or null if the event list is empty.
     */
    public Event getNextEvent() {
        return events.poll();
    }

    /**
     * Checks if the event list is empty.
     * @return true if the event list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return events.isEmpty();
    }
}