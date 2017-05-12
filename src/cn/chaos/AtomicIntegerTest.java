package cn.chaos;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest implements Runnable{

	private AtomicInteger i = new AtomicInteger(0);
	public int getValue(){
		return i.get();
	}
	public void evenIncrement(){
		i.addAndGet(2);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			evenIncrement();
		}
	}
	
	public static void main(String[] args) {
		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				System.out.println("Aborting");
				System.exit(0);
				
			}
		}, 5000);
		ExecutorService exec = Executors.newCachedThreadPool();
		AtomicIntegerTest test = new AtomicIntegerTest();
		exec.execute(test);
		while(true){
			int val = test.getValue();
			if(val%2!=0){
				System.out.println(val);
				System.exit(0);
			}
		}
	}

}
