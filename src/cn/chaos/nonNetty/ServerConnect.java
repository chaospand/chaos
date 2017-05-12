package cn.chaos.nonNetty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class ServerConnect {
	
	private static final int BUF_SIZE = 1024;
	private static final int PORT = 8080;
	private static final int TIMEOUT = 3000;
	
	public static void main(String[] args) {
		selctor();
	}
	
	public static void handleAccept(SelectionKey key) throws IOException {
		ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
		SocketChannel sc = ssc.accept();
		sc.configureBlocking(false);
		sc.register(key.selector(),SelectionKey.OP_READ,ByteBuffer.allocate(BUF_SIZE));
		
	}
	
	
	public static void handleRead(SelectionKey key) throws IOException{
		SocketChannel sc = (SocketChannel) key.channel();
		ByteBuffer buf = (ByteBuffer) key.attachment();
		long bytesRead = sc.read(buf);
		while(bytesRead>0){
			buf.flip();
			while(buf.hasRemaining()){
				System.out.print((char)buf.get());
			}
			System.out.println();
			buf.clear();
			bytesRead = sc.read(buf);
		}
		if(bytesRead==-1){
			sc.close();
		}
	}
	
	public static void handleWrite(SelectionKey key) throws IOException{
		ByteBuffer buf = (ByteBuffer)key.attachment();
		buf.flip();
		SocketChannel sc = (SocketChannel) key.channel();
		while(buf.hasRemaining()){
			sc.write(buf);
		}
		buf.compact();
	}
	
	public static void selctor() {
		Selector selector =  null;
		ServerSocketChannel ssc = null;
		try {
			selector = Selector.open();
			ssc = ServerSocketChannel.open();
			ssc.bind(new InetSocketAddress(PORT));
			ssc.configureBlocking(false);
			ssc.register(selector,SelectionKey.OP_ACCEPT);
			
			while(true){
				if(selector.select(TIMEOUT)==0){
					System.out.println("==");
					continue;
				}
				Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
				while(iterator.hasNext()){
					SelectionKey key = iterator.next();
					if(key.isAcceptable()){
						handleAccept(key);
					}
					if(key.isReadable()){
						handleRead(key);
					}
					if(key.isWritable() && key.isValid()){
						handleWrite(key);
					}
					if(key.isConnectable()){
						System.out.println("isConnectable = true");
					}
					iterator.remove();
				}
				
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}finally {
			try {
				if(selector!=null){
					selector.close();
				}
				if(ssc!=null){
					ssc.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
