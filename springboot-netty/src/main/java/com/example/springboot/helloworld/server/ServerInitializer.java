package com.example.springboot.helloworld.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;


/**
 * @create 2022-01-06 23:57
 */
//服务端主类编写完毕之后,再来设置下相应的过滤条件。 这里需要继承Netty中ChannelInitializer类，
// 然后重写initChannel该方法，进行添加相应的设置，传输协议设置，以及相应的业务实现类。
public class ServerInitializer extends ChannelInitializer<SocketChannel> {

    //new一个解码
    private static final StringDecoder decoder = new StringDecoder();
    //new一个编码
    private static final StringDecoder encoder = new StringDecoder();

    //创建一个ServerHandler，用来继承SimpleChannelInboundHandler
    private static final ServerHandler handler = new ServerHandler();

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        //添加帧限定符来防止粘包现象
        pipeline.addLast(new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        //编码和解码与客户端保持一致
        pipeline.addLast(encoder);
        pipeline.addLast(decoder);

        //业务逻辑实现类
        pipeline.addLast(handler);

    }

}
