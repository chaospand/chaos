package cn.chaos.mytest;

public class DoOneThingProxy implements DoOneThing {

	private DoOneThing doOneThing;
	
	
	
	public DoOneThingProxy(DoOneThing doOneThing) {
		this.doOneThing = doOneThing;
	}



	@Override
	public void xx() {
		System.out.println("before");
		doOneThing.xx();
		System.out.println("after");
	}

}
