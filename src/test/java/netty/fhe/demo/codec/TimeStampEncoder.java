package netty.fhe.demo.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import netty.fhe.demo.LoopBackTimeStamp;

public class TimeStampEncoder extends MessageToByteEncoder<LoopBackTimeStamp> {

	@Override
	protected void encode(ChannelHandlerContext ctx, LoopBackTimeStamp msg, ByteBuf out) throws Exception {
		// TODO Auto-generated method stub
		out.writeBytes(msg.toByteArray());
	}

}
