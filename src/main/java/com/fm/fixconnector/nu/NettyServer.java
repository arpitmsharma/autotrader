package com.fm.fixconnector.nu;

import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import static org.jboss.netty.channel.Channels.*;

public class NettyServer {
	  public static void main(String args[]) throws Exception {
	        ChannelFactory factory =
	            new NioServerSocketChannelFactory(
	                    Executors.newCachedThreadPool(),
	                    Executors.newCachedThreadPool());
	 
	        ServerBootstrap bootstrap = new ServerBootstrap(factory);
	      
	        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
	            public ChannelPipeline getPipeline() {
	                return   org.jboss.netty.channel.Channels.pipeline(new EchoServerHandler());
	            }
	        });
	 
	        bootstrap.setOption("child.tcpNoDelay", true);
	        bootstrap.setOption("child.keepAlive", true);
	 
	        bootstrap.bind(new InetSocketAddress(8888));
	        System.out.println("Server Started!");
	    }
}
