package netty.fhe.demo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import netty.fhe.demo.codec.TimeStampDecoder;
import netty.fhe.demo.codec.TimeStampEncoder;

public class NettyClient {
	public static void main(String[] args) throws InterruptedException {
		NioEventLoopGroup workerGroup = new NioEventLoopGroup();
		Bootstrap b = new Bootstrap();
		b.group(workerGroup);
		b.channel(NioSocketChannel.class);
		b.handler(new ChannelInitializer<SocketChannel>() {

			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				// TODO Auto-generated method stub
				ch.pipeline().addLast(new TimeStampEncoder(), new TimeStampDecoder(), new ClientHandler());
			}

		});
		String serverIp = "localhost";
		ChannelFuture future = b.connect(serverIp, 19000).sync();
		future.channel().writeAndFlush("helloworld");
	}
}
