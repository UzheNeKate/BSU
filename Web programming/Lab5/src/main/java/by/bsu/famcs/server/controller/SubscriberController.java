package by.bsu.famcs.server.controller;

import by.bsu.famcs.server.model.Subscriber;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;

/**
 * SubscriberController is used to get actual subscribers
 */
public class SubscriberController {
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger();

    @Getter
    private long lastModified;
    private final List<Subscriber> subscribers = new LinkedList<>();
    private final File file;

    /**
     * constructor
     * @param path path to load subscribers
     */
    public SubscriberController(String path) {
        this.file = new File(path);
        lastModified = file.lastModified();
        try {
            loadSubscribers();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return list of subscribers. If the file was modified, it will be reloaded
     */
    public List<Subscriber> getSubscribers() {
        if (lastModified < file.lastModified()) {
            subscribers.clear();
            try {
                loadSubscribers();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return subscribers;
    }

    /**
     * loads subscribers
     * @throws IOException where cannot read from file
     */
    private void loadSubscribers() throws IOException {
        logger.info("Updating subscribers list...");
        var rawAddresses = Files.readAllLines(file.toPath());
        lastModified = file.lastModified();
        for (var rawAddress : rawAddresses) {
            subscribers.add(Subscriber.parse(rawAddress));
        }
    }
}
