package cn.chaos.threadShutDown.block;

public class Blocked2 implements Runnable {
	BlockedMutex blocked = new BlockedMutex();
	
	@Override
	public void run() {
		System.out.println("waiting for f() in BlockedMutex");
		blocked.f();
		System.out.println("broken out of blocked call");
	}

}
