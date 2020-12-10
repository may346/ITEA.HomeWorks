package ua.itea;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class App {

	public static void main(String[] args) {
		Mine mine = new Mine(1000);
		ArrayList<Worker> alWorkers = new ArrayList<>(); 
		@SuppressWarnings("unused")
		Barraks barraks = new Barraks(alWorkers, mine);
		
		for(int i=0; i <5; i++) {
			alWorkers.add(new Worker(mine));
		}
		int iteration=0;
		while( mine.isNotEmpty() ) {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
			System.out.println( ++iteration + ") " + mine.getCapacity() );
			System.out.print( "Workers(" + alWorkers.size() + ") result: " );
			for(Worker w : alWorkers) {
				System.out.print( w.getResult()+ "; ");
			}
			System.out.println("");
		}
		System.out.println( "--- The End ---" );		
	}
}
