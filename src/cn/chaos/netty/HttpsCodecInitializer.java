package cn.chaos.netty;

import javax.net.ssl.SSLEngine;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpContentCompressor;
import io.netty.handler.codec.http.HttpContentDecompressor;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslHandler;
import io.netty.internal.tcnative.SSLContext;

public class HttpsCodecInitializer extends ChannelInitializer<Channel>{

	private final SslContext context;
	private final boolean client;
	
	
	
	public HttpsCodecInitializer(SslContext context, boolean client) {
		this.context = context;
		this.client = client;
	}



	@Override
	protected void initChannel(Channel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		SSLEngine engine = context.newEngine(ch.alloc());
		pipeline.addFirst("ssl",new SslHandler(engine));
		if(client){
			pipeline.addLast("codec",new HttpClientCodec());
		}else{
			pipeline.addLast("codec",new HttpServerCodec());
		}
		
	}

}
