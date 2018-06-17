package phei.netty.codec.msgpack;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class EchoClientHandler extends ChannelInboundHandlerAdapter {
    private final int sendNumber;

    public EchoClientHandler(int sendNumber) {
        this.sendNumber = sendNumber;
    }

    private User[] getUserArray(int userNum) {
        User[] users = new User[userNum];
        User user = null;
        for(int i=0; i < userNum; i++) {
            user = new User();
            user.setName("ABCDEF --->" + i);
            user.setAge(i);
            users[i] = user;
        }
        return users;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        User[] users = getUserArray(sendNumber);
        for(User user:users) {
            ctx.write(user);
        }
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        System.out.println("Client receive the msgpack message :" + msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
