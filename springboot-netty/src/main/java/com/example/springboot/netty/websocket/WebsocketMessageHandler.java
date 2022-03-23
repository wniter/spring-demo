package com.example.springboot.netty.websocket;


import com.example.springboot.netty.service.DiscardService;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketCloseStatus;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @create 2022-01-15 21:59
 * 通过IOC管理 Netty 的Handler
 */

@ChannelHandler.Sharable
@Component
public class WebsocketMessageHandler extends SimpleChannelInboundHandler<WebSocketFrame> {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebsocketMessageHandler.class);

    @Autowired
    DiscardService discardService;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, WebSocketFrame webSocketFrame) throws Exception {
        if (webSocketFrame instanceof TextWebSocketFrame) {
            TextWebSocketFrame textWebSocketFrame = (TextWebSocketFrame) webSocketFrame;
            // 业务层处理数据
            //消息注入
            this.discardService.discard(textWebSocketFrame.text());
            // 响应客户端
            channelHandlerContext.channel().writeAndFlush(new TextWebSocketFrame("我收到了你的消息：" + System.currentTimeMillis()));
        } else {
            // 不接受文本以外的数据帧类型
            channelHandlerContext.channel().writeAndFlush(WebSocketCloseStatus.INVALID_MESSAGE_TYPE).addListener(ChannelFutureListener.CLOSE);
        }

    }

    //打印日志
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        LOGGER.info("链接创建：{}", ctx.channel().remoteAddress());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        LOGGER.info("链接断开：{}", ctx.channel().remoteAddress());
    }
}
