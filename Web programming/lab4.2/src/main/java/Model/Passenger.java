package Model;

import lombok.Getter;

public class Passenger {
    @Getter
    private final Route route;
    @Getter
    private final int firstStopIndex;
    @Getter
    private final int lastStopIndex;

    /**
     * @param route route the passenger need go for
     * @param firstStopIndex the stop where the passenger come
     * @param lastStopIndex the stop where the passenger need to go
     */
    public Passenger(Route route, int firstStopIndex, int lastStopIndex) {
        this.route = route;
        this.firstStopIndex = firstStopIndex;
        this.lastStopIndex = lastStopIndex;
    }

    /**
     * @return string represents this passenger
     */
    @Override
    public String toString() {
        return "Passenger{" +
                "route=" + route.getName() +
                ", firstStopIndex=" + firstStopIndex +
                ", lastStopIndex=" + lastStopIndex +
                '}';
    }
}
