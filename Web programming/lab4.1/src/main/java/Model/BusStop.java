package Model;

import lombok.Getter;

import java.util.*;

public class BusStop {
    private static final int DEFAULT_CAPACITY = 3;
    @Getter
    private int capacity;
    @Getter
    private final String name;
    @Getter
    private List<Bus> buses;
    @Getter
    private final Map<Route, Queue<Passenger>> passengerQueues = new HashMap<>();

    /**
     * constructor
     * @param name the name of the stop
     */
    public BusStop(String name) {
        this(DEFAULT_CAPACITY, name);
    }

    /**
     * constructor
     * @param capacity number of buses can stay on the stop at one time
     * @param name name of the stop
     */
    public BusStop(int capacity, String name) {
        this.capacity = capacity;
        this.name = name;
        buses = new LinkedList<>();
    }

    /**
     * @param route the route to delete the passenger from
     * @return the deleted passenger
     */
    public Passenger pollQueue(Route route) {
        return passengerQueues.get(route).poll();
    }

    /**
     * @param route the route to find queue in
     * @return true if queue is empty, else false
     */
    public boolean queueIsEmpty(Route route) {
        var queue = passengerQueues.get(route);
        return queue == null || queue.isEmpty();
    }

    /**
     * @param passenger passenger to add to queue
     */
    public void pushToQueue(Passenger passenger) {
        passengerQueues.putIfAbsent(passenger.getRoute(), new LinkedList<>());
        passengerQueues.get(passenger.getRoute()).add(passenger);
    }

    /**
     * @return true if count of buses on the stop equals capacity of the stop
     */
    public boolean busesAreFull() {
        return buses.size() >= capacity;
    }

    /**
     * @param bus bus arrived to the stop
     */
    public void addBus(Bus bus) {
        buses.add(bus);
    }

    /**
     * @param bus bus to delete from the stop
     */
    public void removeBus(Bus bus) {
        buses.remove(bus);
    }

    /**
     * @return the string represents this bus stop
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * @param route the route to get the passengers queue
     * @return the queue for target route
     */
    public Queue<Passenger> getQueue(Route route) {
        return passengerQueues.get(route);
    }
}
