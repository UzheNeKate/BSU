package by.bsu.famcs.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@ChannelHandler.Sharable
public class ServerHandler extends SimpleChannelInboundHandler<String> {
    @Getter
    private static final Map<String, List<Channel>> connections = new HashMap<>();
    private static final ChannelGroup connectedChannels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) {
        System.out.println(s);
    }


    /**
     * @param ctx channel context that contents info about connection
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        var accepted = ctx.channel();
        logger.info(String.format("The server has received a new client connection: %s",
                accepted.remoteAddress().toString()));
        connectedChannels.add(accepted);
        var addr = (InetSocketAddress) accepted.remoteAddress();
        if (!connections.containsKey(addr.getAddress().getHostAddress())){
            connections.put(addr.getAddress().getHostAddress(), new LinkedList<>());
        }
        connections.get(addr.getAddress().getHostAddress()).add(accepted);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
