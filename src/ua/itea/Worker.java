package ua.itea;

public class Worker extends Thread {
	private int velocity = 3;
	private int result = 0;
	private Mine mine;
	
	public Worker(Mine mine) {
		this.mine = mine;
		
		start();
	}
	public int getResult() {
		return result;
	}

	@Override
	public void run() {
		while(true){
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			int newGold = mine.take(velocity); 
			if( newGold==0 ) break;
			result += newGold;
		}
	}
}
