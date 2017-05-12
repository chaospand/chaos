package cn.chaos.threadShutDown.block;

import java.io.IOException;
import java.io.InputStream;

public class IOBlocked implements Runnable {
	private InputStream in;
	
	public IOBlocked(InputStream in) {
		this.in = in;
	}


	@Override
	public void run() {
		try {
			System.out.println("Waiting for read():");
			in.read();
		} catch (IOException e) {
			if(Thread.currentThread().isInterrupted()){
				System.out.println("Interrupted from blocked I/O");
			}else{
				throw new RuntimeException();
			}
		}
		System.out.println("exiting IOBlocked.run()");
	}

}
