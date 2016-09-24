package com.fm.fixconnector.nu;

import java.io.IOException;





import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.util.CharsetUtil;

public class EchoServerHandler extends SimpleChannelHandler {
 
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
         ChannelBuffer  buf = (ChannelBuffer) e.getMessage();
            while(buf.readable()) {
                System.out.println((char) buf.readByte());
                System.out.flush();
            }
            ChannelFuture channelFuture	=e.getFuture();
        	Channel channel=channelFuture.getChannel();
        	channel.write(buf);
        	
        	System.out.println("Sent.........");
    }
    @Override
    public void connectRequested(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
    	System.out.println("conenct re");
    	
	}
    
    public void writeRequested(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
	System.out.println("write re");
	}

	public void bindRequested(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		System.out.println("bind reques");
	}

	

 
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
        e.getCause().printStackTrace();
         
        Channel ch = (Channel) e.getChannel();
        ch.close();
    }
}
