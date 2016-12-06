package netty.fhe.demo;

import java.nio.ByteBuffer;

public class LoopBackTimeStamp {
	private long sendTimeStamp;
	private long recvTimeStamp;

	public LoopBackTimeStamp() {
		this.sendTimeStamp = System.nanoTime();
	}

	public long timeLapseinNanoSecond() {
		return recvTimeStamp - sendTimeStamp;
	}

	public long getSendTimeStamp() {
		return sendTimeStamp;
	}

	public void setSendTimeStamp(long sendTimeStamp) {
		this.sendTimeStamp = sendTimeStamp;
	}

	public long getRecvTimeStamp() {
		return recvTimeStamp;
	}

	public void setRecvTimeStamp(long recvTimeStamp) {
		this.recvTimeStamp = recvTimeStamp;
	}

	public byte[] toByteArray() {
		final int byteOfLong = Long.SIZE / Byte.SIZE;
		byte[] ba = new byte[byteOfLong * 2];
		byte[] t1 = ByteBuffer.allocate(byteOfLong).putLong(sendTimeStamp).array();
		byte[] t2 = ByteBuffer.allocate(byteOfLong).putLong(recvTimeStamp).array();
		for (int i = 0; i < byteOfLong; i++) {
			ba[i] = t1[i];
		}
		for (int i = 0; i < byteOfLong; i++) {
			ba[byteOfLong + i] = t2[i];
		}
		return ba;
	}

	public void fromByteArray(byte[] content) {
		int len = content.length;
		final int byteOfLong = Long.SIZE / Byte.SIZE;
		if (len != byteOfLong * 2) {
			System.out.println("Error on content length!");
			return;
		}
		ByteBuffer buf1 = ByteBuffer.allocate(byteOfLong).put(content, 0, byteOfLong);
		ByteBuffer buf2 = ByteBuffer.allocate(byteOfLong).put(content, byteOfLong, byteOfLong);
		buf1.rewind();
		buf2.rewind();
		this.sendTimeStamp = buf1.getLong();
		this.recvTimeStamp = buf2.getLong();
	}
}
