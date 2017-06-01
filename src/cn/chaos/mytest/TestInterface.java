package cn.chaos.mytest;

public class TestInterface{
	
	
	
	public static void main(String[] args) {
		DoOneThing doOneThing = new Eat();
		DoOneThingProxy proxy = new DoOneThingProxy(doOneThing);
		proxy.xx();
	}
	
}
