package com.example.springboot.helloworld.server;
//官网：新手入门
//https://netty.io/wiki/requirements-for-4.x.html

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @create 2022-01-06 22:41
 */
//Server begin
//服务端启动类
public class Server {
    public static void main(String[] args) {
        //Configure the server
        //创建两个EventLoopGroup对象
        //用于服务端接收客户端的连接
        NioEventLoopGroup bossgroup = new NioEventLoopGroup();
        //用于进行客户端SocketChannel的数据读写
        NioEventLoopGroup workgroup = new NioEventLoopGroup();
        //为什么是两个EventLoopGroup对象
        //https://my.oschina.net/u/1859679/blog/1844109
        try {
            //创建SeverBootstrap对象
            ServerBootstrap bootstrap = new ServerBootstrap();
            //设置使用的两个EvenLoopGroup对象
            bootstrap.group(bossgroup, workgroup)
                    //设置要被实例化的为 NioServerSocketChannel 类
                    .channel(NioServerSocketChannel.class)
                    // 设置 NioServerSocketChannel 的处理器
                    .handler(new LoggingHandler(LogLevel.INFO))
                    // 设置连入服务端的 Client 的 SocketChannel 的处理器
                    .childHandler(new ServerInitializer());
            //绑定端口，并同步等待成功，启动服务端。
            ChannelFuture f = bootstrap.bind(8888);

            //关闭服务端，并阻塞等待。
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //关闭EventLooopGroup两个对象
            bossgroup.shutdownGracefully();
            workgroup.shutdownGracefully();
        }
    }

}

//02:06:11.224 [main] DEBUG io.netty.util.Recycler - -Dio.netty.recycler.maxCapacityPerThread: 4096
//02:06:11.224 [main] DEBUG io.netty.util.Recycler - -Dio.netty.recycler.maxSharedCapacityFactor: 2
//02:06:11.224 [main] DEBUG io.netty.util.Recycler - -Dio.netty.recycler.linkCapacity: 16
//02:06:11.224 [main] DEBUG io.netty.util.Recycler - -Dio.netty.recycler.ratio: 8
//yes
//
//hello world
//
//  Did you say 'yes'?
//bye
//hello world
//
//bye