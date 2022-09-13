package Model;

import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.LinkedList;
import java.util.List;

public class Bus implements Runnable {
    private final int capacity = 30;
    @Getter
    private final Route route;
    @Getter
    private final List<Passenger> passengers = new LinkedList<>();
    private static final Logger logger = (Logger) LogManager.getLogger();

    /**
     * Constructor
     * @param route the route for this bus
     */
    public Bus(Route route) {
        this.route = route;
    }

    /**
     * @param p passenger went to the bus
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
            synchronized (stop.getBuses()) {
                while (stop.busesAreFull()) {
                    try {
                        logger.info("Bus is waiting on " + stop.getName());
                        stop.getBuses().wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                stop.addBus(this);
            }
            int current = i;
            passengers.stream()
                    .filter(p -> p.getLastStopIndex() == current)
                    .forEach(p -> logger.info(p + " removed from " + this + " (" + route.getName() + ") "));
            passengers.removeIf(pass -> pass.getLastStopIndex() == current);
            while (!this.isFull() && !stop.queueIsEmpty(route)) {
                synchronized (stop.getQueue(route)) {
                    var p = stop.pollQueue(route);
                    logger.info(p + " sat to " + this + " (" + route.getName() + ") ");
                    addPassenger(p);
                }
            }
            logger.info("Bus " + this + " leaving from " + stop.getName());
            synchronized (stop.getBuses()) {
                stop.removeBus(this);
                stop.getBuses().notify();
            }
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
