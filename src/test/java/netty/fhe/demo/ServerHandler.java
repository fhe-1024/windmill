package netty.fhe.demo;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class ServerHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		// TODO Auto-generated method stub
		LoopBackTimeStamp ts = (LoopBackTimeStamp) msg;
		ts.setRecvTimeStamp(System.nanoTime());
		System.out.println("loop delay in ms:" + 1.0 * ts.timeLapseinNanoSecond() / 1000000L);
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		// TODO Auto-generated method stub
		if (evt instanceof IdleStateEvent) {
			IdleStateEvent event = (IdleStateEvent) evt;
			if (event.state() == IdleState.ALL_IDLE) {
				ctx.writeAndFlush(new LoopBackTimeStamp());
			}
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
		cause.printStackTrace();
		ctx.close();
	}

}
