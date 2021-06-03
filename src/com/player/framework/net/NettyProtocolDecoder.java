package com.player.framework.net;

import java.util.List;

import com.player.framework.serializer.Message;
import com.player.framework.serializer.Serializer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class NettyProtocolDecoder extends ByteToMessageDecoder {

	private final int length = 10;

	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		try {
			if (in.readableBytes() < 2) {
				in.resetReaderIndex();
				return;
			}
			int length = in.readUnsignedShortLE();
			if (in.readableBytes() >= length - 2) {
				short module = in.readShortLE();
				short cmd = in.readShortLE();
				in.readInt();
				byte[] body = null;
				if (length > this.length) {
					body = new byte[length - this.length];
					in.readBytes(body);
				}
				Message message = Serializer.decode(module, cmd, body);
				if (message != null) {
					out.add(message);
				}
				in.markReaderIndex();
			} else {
				in.resetReaderIndex();
			}
		} catch (Exception e) {
			throw new Exception(e);
		}

	}

}
