package by.bsu.famcs.server.controller;

import by.bsu.famcs.server.model.Subscriber;
import io.netty.channel.Channel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MessageSender {
    private static final Logger logger = LogManager.getLogger();

    /**
     * Tries to find a connection and send a message
     *
     * @param msg         a message to send
     * @param subscribers a list of subscribers who want to receive a message
     * @param connections a map with all the connections. Key is an address, value is a list of all connections
     *                    for this address
     * @throws SubscriberNotFoundException thrown when the connection for a subscriber is not found
     */
    public static void send(String msg, List<Subscriber> subscribers, Map<String, List<Channel>> connections)
            throws SubscriberNotFoundException {
        logger.info("Trying to send a message");
        for (var subscriber : subscribers) {
            var maybeSocketChannel = tryFindSocket(subscriber, connections);
            if (maybeSocketChannel.isEmpty()) {
                logger.error("Subscriber " + subscriber + " not found");
                throw new SubscriberNotFoundException(subscriber);
            }
            var channel = maybeSocketChannel.get();
            channel.writeAndFlush(msg);
            logger.info("Message successfully sent to " + subscriber);
        }
    }

    /**
     * @param subscriber  subscriber
     * @param connections connections to servers
     * @return optional of needed socket
     */
    private static Optional<Channel> tryFindSocket(Subscriber subscriber, Map<String,
            List<Channel>> connections) {
        if (!connections.containsKey(subscriber.getAddress())) {
            return Optional.empty();
        }
        var channels = connections.get(subscriber.getAddress());
        if (channels.isEmpty()) {
            return Optional.empty();
        }
        if (!subscriber.portSpecified()) {
            return Optional.of(channels.get(0));
        }
        for (var channel : channels) {
            var port = ((InetSocketAddress)channel.remoteAddress()).getPort();
            if (port == subscriber.getPort()) {
                return Optional.of(channel);
            }
        }
        return Optional.empty();
    }
}
