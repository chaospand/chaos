package cn.chaos.threadShutDown.block;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockedMutex {
	private Lock lock = new ReentrantLock();

	public BlockedMutex() {
		lock.lock();
	}
	
	public void f(){
		try {
			lock.lockInterruptibly();
			System.out.println("lock acquired in f()");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("interrupted from lock acquuisition in f()");
		}
	}
	
	
}
