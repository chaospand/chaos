package cn.chaos.nonNetty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Set;

import io.netty.channel.ServerChannel;

public class PlainNioServer {
	public void server(int port) throws IOException{
		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		serverChannel.configureBlocking(false);
		ServerSocket ss = serverChannel.socket();
		InetSocketAddress address = new InetSocketAddress(port);
		ss.bind(address);
		Selector selector = Selector.open();
		serverChannel.register(selector, SelectionKey.OP_ACCEPT);
		final ByteBuffer msg = ByteBuffer.wrap("Hi!\r\n".getBytes());
		for(;;){
			try {
				selector.select();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				break;
			}
			
			Set<SelectionKey> readyKeys = selector.selectedKeys();
			Iterator<SelectionKey> iterator = readyKeys.iterator();
			while(iterator.hasNext()){
				SelectionKey key = iterator.next();
				iterator.remove();
				try {
					if(key.isAcceptable()){
						ServerSocketChannel server = 
								(ServerSocketChannel) key.channel();
						SocketChannel client = server.accept();
						client.configureBlocking(false);
						client.register(selector, SelectionKey.OP_WRITE |
								SelectionKey.OP_READ,msg.duplicate());
					}
					if(key.isWritable()){
						SocketChannel client = 
								(SocketChannel) key.channel();
						ByteBuffer buffer = 
								(ByteBuffer) key.attachment();
						while(buffer.hasRemaining()){
							if(client.write(buffer) == 0){
								break;
							}
						}
						client.close();
					}
				} catch (Exception e) {
					key.cancel();
					
					try {
						key.channel().close();
					} catch (Exception e1) {
						
					}
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		}
		
	}
}
