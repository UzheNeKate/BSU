package Model;

import lombok.Getter;

import java.util.List;
import java.util.Objects;

public class Route {
    @Getter
    private final int name;
    @Getter
    private final List<BusStop> stops;
    @Getter
    private final List<Integer> times;

    /**
     * @param name the name of the route
     * @param stops list of stops in this route
     * @param times list of times between the stops
     * @throws IllegalTimeListException
     */
    public Route(int name, List<BusStop> stops, List<Integer> times) throws IllegalTimeListException {
        this.name = name;
        if (times.size() != stops.size() - 1) {
            throw new IllegalTimeListException();
        }
        this.stops = stops;
        this.times = times;
    }

    /**
     * @param i the index of next stop
     * @return time for next stop
     */
    public int getTimeToNextStop(int i) {
        return times.get(i);
    }

    /**
     * @param o object to compare
     * @return true if this == o, else false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Objects.equals(name, route.name) && Objects.equals(stops, route.stops);
    }

    /**
     * @return hash of this route
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, stops);
    }
}
