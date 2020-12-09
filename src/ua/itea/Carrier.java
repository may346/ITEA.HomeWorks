package ua.itea;

import java.util.concurrent.TimeUnit;

public class Carrier implements Runnable {
	private int transportationTime=5;
	private Cart  cart;

	public Carrier(Cart cart) {
		this.cart = cart;
		
		new Thread(this).start();
	}

	@Override
	public void run() {
		synchronized(cart) {			
			while( true ) {
				cart.startTransition();
				if( cart.isStopWork() ) {	// The End
					break;
				}
				if( cart.isNotEmpty() ) {	// go to Unloader
					System.out.print("carier started to unloader ->->- " );
				}
				else{	// go to Loader
					System.out.print("carier started to loader -<-<- " );
				}
				try {
					TimeUnit.SECONDS.sleep(transportationTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(" carier finished" );				
				cart.finishOperation();
			}			
		}
		System.out.println("-- carier finished work" );		
	}

}
