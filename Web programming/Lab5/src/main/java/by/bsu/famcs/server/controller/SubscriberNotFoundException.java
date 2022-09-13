package by.bsu.famcs.server.controller;

import by.bsu.famcs.server.model.Subscriber;

/**
 * Used for situations when it's impossible to find a connection with a subscriber
 */
public class SubscriberNotFoundException extends Exception {
    private final Subscriber subscriber;

    /**
     * @param subscriber subscriber that throws this exception
     */
    public SubscriberNotFoundException(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    /**
     * @return the string represents the message of this exception
     */
    @Override
    public String toString() {
        return String.format("Subscriber with address %s not found", subscriber);
    }
}
