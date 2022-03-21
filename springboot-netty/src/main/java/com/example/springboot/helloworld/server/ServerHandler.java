package com.example.springboot.helloworld.server;

import io.netty.channel.*;

import java.net.InetAddress;
import java.util.Date;

//ChannelInboundHandlerAdapter 或SimpleChannelInboundHandler类，在这里顺便说下它们两的区别吧。
//继承SimpleChannelInboundHandler类之后，会在接收到数据后会自动release掉数据占用的Bytebuffer资源。并且继承该类需要指定数据格式。
//而继承ChannelInboundHandlerAdapter则不会自动释放，需要手动调用ReferenceCountUtil.release()等方法进行释放。继承该类不需要指定数据格式。
//所以在这里，个人推荐服务端继承ChannelInboundHandlerAdapter，手动进行释放，防止数据未处理完就自动释放了。而且服务端可能有多个客户端进行连接，
// 并且每一个客户端请求的数据格式都不一致，这时便可以进行相应的处理。 客户端根据情况可以继承SimpleChannelInboundHandler类。
//好处是直接指定好传输的数据格式，就不需要再进行格式的转换了。

/**
 * @create 2022-01-07 0:10
 */

//标注一个channel handler可以被多个channel安全地共享。
@ChannelHandler.Sharable
public class ServerHandler extends SimpleChannelInboundHandler<String> {


    //建立连接时，写下Message
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.write("Welcome to " + InetAddress.getLocalHost().getHostName() + "!\r\n");
        ctx.write("this is " + new Date() + "now!\r\n");
        ctx.flush();
    }

    //业务逻辑处理
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String request) throws Exception {
        // Generate and write a response.
        String response;
        boolean close = false;
        if (request.isEmpty()) {
            response = "Please type something.\r\n";
        } else if ("bye".equals(request.toLowerCase())) {
            response = "Have a good day!\r\n";
            close = true;
        } else {
            response = "Did you say '" + request + "'?\r\n";
        }

        ChannelFuture future = ctx.write(response);

        if (close) {
            future.addListener(ChannelFutureListener.CLOSE);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx)  {
        //Server读取后刷新
        ctx.flush();
    }

    //异常处理
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
