package netty.fhe.demo.codec;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import netty.fhe.demo.LoopBackTimeStamp;

public class TimeStampDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		// TODO Auto-generated method stub
		final int messageLength = Long.SIZE / Byte.SIZE * 2;
		if (in.readableBytes() < messageLength) {
			return;
		}
		byte[] ba = new byte[messageLength];
		in.readBytes(ba, 0, messageLength);
		LoopBackTimeStamp loopBackTimeStamp = new LoopBackTimeStamp();
		loopBackTimeStamp.fromByteArray(ba);
		out.add(loopBackTimeStamp);
	}

}
