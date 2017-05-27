package cn.chaos.netty;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class WriteHandler extends  ChannelHandlerAdapter{
	private ChannelHandlerContext ctx;
	
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		this.ctx = ctx;
	}
	
	public void send(String msg){
		ctx.writeAndFlush(msg);
	}
}
