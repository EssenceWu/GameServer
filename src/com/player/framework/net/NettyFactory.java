package com.player.framework.net;

import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

public enum NettyFactory {

	INSTANCE;

	private EventLoopGroup bossGroup = new NioEventLoopGroup(4);
	private EventLoopGroup workerGroup = new NioEventLoopGroup();

	public void initialize(int port) throws Exception {
		try {
			System.out.println("Loading netty service...");
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, 1024)
					.childHandler(new NettyHandler());
			ChannelFuture f = b.bind(new InetSocketAddress(port)).sync();
			System.out.println("Loading netty service successfully!");
			System.out.println("Loading all service successfully!");
			f.channel().closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

	protected class NettyHandler extends ChannelInitializer<SocketChannel> {
		protected void initChannel(SocketChannel channel) throws Exception {
			ChannelPipeline p = channel.pipeline();
			p.addLast(new NettyProtocolEncoder());
			p.addLast(new NettyProtocolDecoder());
			p.addLast(new IdleStateHandler(0, 0, 300));
			p.addLast(new NettyChannelHandler(MessageDispatcherFactory.INSTANCE));
		}
	}

}
