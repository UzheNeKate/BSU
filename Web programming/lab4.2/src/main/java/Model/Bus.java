package Model;

import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Bus implements Runnable {
    private final int capacity = 30;
    @Getter
    private final Route route;
    @Getter
    private final BlockingQueue<Passenger> passengers = new LinkedBlockingQueue<Passenger>();
    private static final Logger logger = (Logger) LogManager.getLogger();

    /**
     * constructor
     * @param route the route for this bus
     */
    public Bus(Route route) {
        this.route = route;
    }

    /**
     * @param p passenger to add to this bus
     */
    public void addPassenger(Passenger p) {
        if (isFull()) {
            logger.info("Bus is full!");
            return;
        }
        passengers.add(p);
    }

    /**
     * @return true if the bus is full, else false
     */
    public boolean isFull() {
        return passengers.size() >= capacity;
    }

    /**
     * The route of the bus from the first to the last stop
     */
    @Override
    public void run() {
        var stops = route.getStops();
        for (var i = 0; i < stops.size(); i++) {
            var stop = stops.get(i);
            logger.info("Bus " + this + " " + route.getName() + " arrived to " + stop.getName());
            stop.addBus(this);
            int current = i;
            passengers.stream()
                    .filter(p -> p.getLastStopIndex() == current)
                    .forEach(p -> logger.info(p + " removed from " + this + " (" + route.getName() + ") "));
            passengers.removeIf(pass -> pass.getLastStopIndex() == current);
            while (!this.isFull() && !stop.queueIsEmpty(route)) {
                var p = stop.pollQueue(route);
                logger.info(p + " sat to " + this + " (" + route.getName() + ") ");
                addPassenger(p);
            }
            logger.info("Bus " + this + " leaving from " + stop.getName());
            stop.removeBus(this);
            if (i < stops.size() - 1) {
                try {
                    Thread.sleep(route.getTimeToNextStop(i));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
