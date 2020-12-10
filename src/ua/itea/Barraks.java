package ua.itea;

import java.util.ArrayList;

public class Barraks extends Thread {
	private int velocity = 10;
	ArrayList<Worker>  alWorkers;
	private Mine mine;
	
	public Barraks(ArrayList<Worker> alWorkers, Mine mine) {
		this.alWorkers = alWorkers;
		this.mine	= mine;
		
		new Thread(this).start();
	}
	
	@Override
	public void run() {
		while( mine.isNotEmpty() ) {
			try {
				sleep(velocity*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			alWorkers.add(new Worker(mine));
		}
	}
}
