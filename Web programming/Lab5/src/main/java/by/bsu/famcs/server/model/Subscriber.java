package by.bsu.famcs.server.model;

import lombok.Getter;

/**
 * Represents a record for a subscriber: its address and port
 */
public class Subscriber {
    @Getter
    private final String address;
    @Getter
    private final int port;

    public Subscriber(String address, int port) {
        this.address = address;
        this.port = port;
    }

    /**
     * Parses a string to a Subscriber instance.
     *
     * @param rawAddress string in <ADDRESS>:<PORT> format. Port is int.
     * @return a new instance
     */
    public static Subscriber parse(String rawAddress) {
        String[] parts = rawAddress.split(":");
        String addr = parts[0];
        int port = parts.length > 1 ? Integer.parseInt(parts[1]) : -1;
        return new Subscriber(addr, port);
    }

    /**
     * @return true if port is specified, else false
     */
    public boolean portSpecified() {
        return port > 0;
    }

    @Override
    public String toString() {
        return portSpecified() ? address + ":" + port : address;
    }
}
