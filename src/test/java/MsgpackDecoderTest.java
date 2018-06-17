package java;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;
import phei.netty.codec.msgpack.MsgpackDecoder;

import static org.junit.Assert.assertTrue;

public class MsgpackDecoderTest {
    @Test
    public void TestMsgpackDecoder() {
        ByteBuf buf = Unpooled.buffer();
        for (int i=0; i < 9; i++) {
            buf.writeByte(i);
        }
        ByteBuf input = buf.duplicate();
        EmbeddedChannel channel = new EmbeddedChannel(new MsgpackDecoder());
        assertTrue(channel.writeInbound(input.retain()));
        assertTrue(channel.finish());

        ByteBuf read = channel.readInbound();

    }

}
