package cn.chaos.syn;

public class SameSynObject {

	private Object 
		o1 = new Object(),
		o2 = new Object(),
		o3 = new Object();
	
	public void f(){
		synchronized (o1) {
			for(int i=0;i<5;i++){
				System.out.println("f()");
				Thread.yield();
			}
		}
	}
	
	public void g(){
		synchronized (o2) {
			for(int i=0;i<5;i++){
				System.out.println("g()");
				Thread.yield();
			}
		}
	}
	
	public void h(){
		synchronized (o3) {
			for(int i=0;i<5;i++){
				System.out.println("h()");
				Thread.yield();
				wrong();
			}
		}
	}
	
	public void wrong(){
		synchronized (this) {
			for(int i=0;i<5;i++){
				System.out.println("wrong()");
				Thread.yield();
				h();
			}
		}
	}
}
