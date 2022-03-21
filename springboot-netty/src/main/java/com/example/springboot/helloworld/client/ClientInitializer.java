package com.example.springboot.helloworld.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @create 2022-01-07 1:08
 */
public class ClientInitializer  extends ChannelInitializer<SocketChannel> {
    private static final StringDecoder decoder = new StringDecoder();
    private static final StringEncoder encoder = new StringEncoder();

    private static final ClientHandler handler = new ClientHandler();


    @Override
    public void initChannel(SocketChannel ch) {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));

        pipeline.addLast(decoder);
        pipeline.addLast(encoder);

        pipeline.addLast(handler);
    }
}
