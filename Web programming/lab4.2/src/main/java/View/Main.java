package View;

import Controller.PassengerProducer;
import Model.Bus;
import Model.IllegalTimeListException;
import Model.Route;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import util.RouteGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {

    private static final Logger logger = (Logger) LogManager.getLogger();;

    public static void main(String[] args) {
        var routes = new ArrayList<Route>();
        for (int i = 0; i < 3; i++) {
            try {
                routes.add(RouteGenerator.generateRoute());
            } catch (IllegalTimeListException e) {
                e.printStackTrace();
            }
            logger.info(routes.get(i).getName() + ": " + routes.get(i).getStops());
        }
        var buses = new ArrayList<Bus>();
        for (var route : routes) {

            buses.add(new Bus(route));
        }
        var scheduler = new ScheduledThreadPoolExecutor(1);
        scheduler.scheduleWithFixedDelay(new PassengerProducer(routes), 0, 1000, TimeUnit.MILLISECONDS);
        List<Thread> busThreads = new ArrayList<>();
        for (var bus : buses) {
            var t = new Thread(bus);
            busThreads.add(t);
            t.start();
        }
        for (var t : busThreads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
