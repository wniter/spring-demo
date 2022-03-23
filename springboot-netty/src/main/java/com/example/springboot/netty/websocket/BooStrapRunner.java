package com.example.springboot.netty.websocket;


import com.example.springboot.netty.pojo.NettyData;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

/**
 * @create 2022-01-15 20:09
 */
@Component
//BootsrapRunner 实现了 ApplicationRunner, ApplicationListener<ContextClosedEvent>, ApplicationContextAware 接口。
//NettyBootsrapRunner 可以在App的启动和关闭时执行Websocket服务的启动和关闭。而且通过 ApplicationContextAware 还能获取到 ApplicationContext
public class BooStrapRunner implements ApplicationRunner, ApplicationListener<ContextClosedEvent>, ApplicationContextAware {
    private static final Logger LOGGER = LoggerFactory.getLogger(BooStrapRunner.class);


    private ApplicationContext applicationContext;

    private Channel channel;

    @Autowired
    NettyData nettyData;

    //实现方式
    //SpringApplication.run(NettySpringbootApplication.class, args);
    @Override
    public void run(ApplicationArguments args) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workGroup);
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.localAddress(new InetSocketAddress(nettyData.getIp(), nettyData.getPort()));
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    ChannelPipeline pipeline = socketChannel.pipeline();

                    pipeline.addLast(new HttpServerCodec());
                    pipeline.addLast(new ChunkedWriteHandler());
                    pipeline.addLast(new HttpObjectAggregator(65536));
                    pipeline.addLast(new ChannelInboundHandlerAdapter() {
                        @Override
                        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                            if (msg instanceof FullHttpRequest) {
                                FullHttpRequest fullHttpRequest = (FullHttpRequest) msg;
                                String uri = fullHttpRequest.uri();
                                if (!uri.equals(nettyData.getPath())) {
                                    // 访问的路径不是 websocket的端点地址，响应404
                                    ctx.channel().writeAndFlush(new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.NOT_FOUND))
                                            .addListener(ChannelFutureListener.CLOSE);
                                    return;
                                }
                                super.channelRead(ctx, msg);
                            }
                        }
                    });
                    pipeline.addLast(new WebSocketServerCompressionHandler());
                    pipeline.addLast(new WebSocketServerProtocolHandler(nettyData.getPath(), null, true, nettyData.getMaxFrameSize()));

                    /**
                     * 从IOC中获取到Handler
                     */
                    pipeline.addLast(applicationContext.getBean(WebsocketMessageHandler.class));
                }
            });

            Channel channel0 = serverBootstrap.bind().sync().channel();
            this.channel = channel0;
            LOGGER.info("websocket 服务启动，ip={},port={}", nettyData.getIp(), nettyData.getPort());
            channel0.closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        if (this.channel != null) {
            LOGGER.info("websocket close");
            this.channel.close();
        }
//        LOGGER.info("websocket still open");
    }
}
