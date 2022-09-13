package Controller;

import Model.Passenger;
import Model.Route;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.List;
import java.util.Queue;
import java.util.Random;

public class PassengerProducer implements Runnable {
    List<Route> routes;
    Random random = new Random();
    private static final Logger logger = (Logger) LogManager.getLogger();

    /**
     * constructor
     * @param routes all routes of the buses
     */
    public PassengerProducer(List<Route> routes) {
        this.routes = routes;
    }

    /**
     * Generator of random people on bus stops
     */
    @Override
    public void run() {
        logger.info("New passenger is coming...");
        var routeNumber = random.nextInt(routes.size());
        var route = routes.get(routeNumber);
        var stops = route.getStops();
        var firstStop = random.nextInt(stops.size() - 1);
        var lastStop = random.nextInt(stops.size() - firstStop) + firstStop + 1;
        var passenger = new Passenger(route, firstStop, lastStop);
        stops.get(firstStop).pushToQueue(passenger);
        logger.info("New passenger added " + passenger);
    }

}
