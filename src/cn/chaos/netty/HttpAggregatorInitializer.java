package cn.chaos.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpContentCompressor;
import io.netty.handler.codec.http.HttpContentDecompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.codec.http.HttpServerCodec;

public class HttpAggregatorInitializer extends ChannelInitializer<Channel>{

	private final boolean client;
	
	public HttpAggregatorInitializer(boolean client) {
		this.client = client;
	}

	@Override
	protected void initChannel(Channel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		//HttpResponseDecoder
		if(client){
			pipeline.addLast("codec",new HttpClientCodec());
			pipeline.addLast("decompressor",new HttpContentDecompressor());
		}else{
			pipeline.addLast("codec",new HttpServerCodec());
			pipeline.addLast("compressor",new HttpContentCompressor());
		}
		pipeline.addLast("aggegator",new HttpObjectAggregator(512*1024));
		
	}

	
	
}
