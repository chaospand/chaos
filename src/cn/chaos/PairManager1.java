package cn.chaos;

public class PairManager1 extends PairManager {

	@Override
	public synchronized void increment() {
		// TODO Auto-generated method stub
		p.incrementX();
		p.incrementY();
		store(getPair());
	}

}
