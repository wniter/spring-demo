package com.example.springboot.helloworld.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @create 2022-01-06 22:40
 */
//客户端启动类
public class Client {
    public static void main(String[] args) {
        //创建一个NioEventLoopGroup对象
        NioEventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientInitializer());
            Channel ch = b.connect("127.0.0.1", 8888).sync().channel();


            ChannelFuture lastWriteFuture = null;
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            for (; ; ) {
                String line = in.readLine();
                if (line == null) {
                    break;
                }

                // Sends the received line to the server.
                lastWriteFuture = ch.writeAndFlush(line + "\r\n");

                // If user typed the 'bye' command, wait until the server closes
                // the connection.
                if ("bye".equals(line.toLowerCase())) {
                    ch.closeFuture().sync();
                    break;
                }
            }

            // Wait until all messages are flushed before closing the channel.
            if (lastWriteFuture != null) {
                lastWriteFuture.sync();
            }
        } catch (IOException | InterruptedException e) {
            e.getMessage();
        } finally {
            group.shutdownGracefully();
        }
    }

}

//02:06:02.227 [nioEventLoopGroup-2-1] INFO io.netty.handler.logging.LoggingHandler - [id: 0x72fbf57d] BIND: 0.0.0.0/0.0.0.0:8888
//02:06:02.230 [nioEventLoopGroup-2-1] INFO io.netty.handler.logging.LoggingHandler - [id: 0x72fbf57d, L:/0:0:0:0:0:0:0:0:8888] ACTIVE
//02:06:05.349 [nioEventLoopGroup-2-1] INFO io.netty.handler.logging.LoggingHandler - [id: 0x72fbf57d, L:/0:0:0:0:0:0:0:0:8888] READ: [id: 0x8632b0e4, L:/127.0.0.1:8888 - R:/127.0.0.1:53170]
//02:06:05.350 [nioEventLoopGroup-2-1] INFO io.netty.handler.logging.LoggingHandler - [id: 0x72fbf57d, L:/0:0:0:0:0:0:0:0:8888] READ COMPLETE
//02:06:05.358 [nioEventLoopGroup-3-1] DEBUG io.netty.buffer.AbstractByteBuf - -Dio.netty.buffer.checkAccessible: true
//02:06:05.358 [nioEventLoopGroup-3-1] DEBUG io.netty.buffer.AbstractByteBuf - -Dio.netty.buffer.checkBounds: true
//02:06:05.359 [nioEventLoopGroup-3-1] DEBUG io.netty.util.ResourceLeakDetectorFactory - Loaded default ResourceLeakDetector: io.netty.util.ResourceLeakDetector@7ce90859
//02:06:11.235 [nioEventLoopGroup-3-1] DEBUG io.netty.util.Recycler - -Dio.netty.recycler.maxCapacityPerThread: 4096
//02:06:11.236 [nioEventLoopGroup-3-1] DEBUG io.netty.util.Recycler - -Dio.netty.recycler.maxSharedCapacityFactor: 2
//02:06:11.236 [nioEventLoopGroup-3-1] DEBUG io.netty.util.Recycler - -Dio.netty.recycler.linkCapacity: 16
//02:06:11.236 [nioEventLoopGroup-3-1] DEBUG io.netty.util.Recycler - -Dio.netty.recycler.ratio: 8
//yes
//
//Did you say 'yes'?