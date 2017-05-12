package cn.chaos.syn;

public class SameSynObjectTest {

	public static void main(String[] args) {
		//testDifferentLock();
		testDeadLock();
	}
	
	public static void testDifferentLock(){
		final SameSynObject o = new SameSynObject();
		new Thread(){
			public void run() {
				o.f();
			};
		}.start();
		new Thread(){
			public void run() {
				o.g();
			};
		}.start();
		o.h();
	}
	
	public static void testDeadLock(){
		final SameSynObject o = new SameSynObject();
		new Thread(){
			public void run() {
				o.wrong();
			};
		}.start();
		new Thread(){
			public void run() {
				o.h();
			};
		}.start();
	}
	
}
