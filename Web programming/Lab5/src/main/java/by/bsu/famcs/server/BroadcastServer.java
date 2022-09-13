package by.bsu.famcs.server;

import by.bsu.famcs.server.controller.MessageSender;
import by.bsu.famcs.server.controller.SubscriberController;
import by.bsu.famcs.server.controller.SubscriberNotFoundException;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.InetSocketAddress;
import java.util.Scanner;

/**
 * BroadcastServer provides functionality for sending messages to listeners according to the file
 */
public class BroadcastServer {
    private static final SubscriberController ctrl = new SubscriberController("subscribers");
    private static final Logger logger = LogManager.getLogger();

    private final int port;

    public BroadcastServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        new BroadcastServer(8081).start();
    }

    public void start() throws Exception {
        final ServerHandler serverHandler = new ServerHandler();
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) {
                            ch.pipeline().addLast(new StringDecoder());
                            ch.pipeline().addLast(new StringEncoder());
                            ch.pipeline().addLast(serverHandler);
                        }
                    });
            new Thread(this::startBroadcasting);

            ChannelFuture f = b.bind().sync();
            logger.info("Server started and listening for connections on " + f.channel().localAddress());
            processInput();
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }

    /**
     * Starting broadcasting log file
     */
    private void startBroadcasting() {
        try {
            LogEventBroadcaster.start(8080);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * works with input data
     */
    private static void processInput() {
        Scanner scanner = new Scanner(System.in);
        logger.info("WELCOME!\nYou can type your messages");
        while (scanner.hasNextLine()) {
            var msg = scanner.nextLine();
            System.out.println(msg);
            try {
                MessageSender.send(msg, ctrl.getSubscribers(), ServerHandler.getConnections());
            } catch (SubscriberNotFoundException e) {
                logger.warn(e);
            }
            logger.info("File modified timestamp: " + ctrl.getLastModified());
        }
    }
}
