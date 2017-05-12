package cn.chaos.nonNetty;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

public class PlainOioServer {
	
	public void server(int port) throws IOException{
		final ServerSocket socket = new ServerSocket(port);
		
		try {
			for(;;){
				final Socket clientSocket = socket.accept();
				System.out.println("Accepted connection from " + clientSocket);
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						OutputStream out;
							try {
								out = clientSocket.getOutputStream();
								out.write("Hi!\r\n".getBytes(Charset.forName("UTF-8")));
								out.flush();
								clientSocket.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								try {
									clientSocket.close();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
							
					}
				}).start();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
