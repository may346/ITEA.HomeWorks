package ua.itea;

import java.util.concurrent.TimeUnit;

public class Unloader implements Runnable {
	private int unloadingSpeed = 2;
	private Cart  cart;

	public Unloader(Cart cart) {
		this.cart = cart;
		
		new Thread(this).start();
	}

	public void unload() throws InterruptedException {
		int delta;
		
		while(true) {
			delta = cart.takeCargo( unloadingSpeed );
			if(delta==0) // cart is empty;
					break;
			else {
				System.out.print("*" );
				TimeUnit.SECONDS.sleep( 1 );
			}
		}
	}
	
	@Override
	public void run() {
		synchronized(cart) {			
			while( true ) {
				cart.startUnload();
				if( cart.isStopWork() ) {	// The End
					break;
				}				
				System.out.print("unloader start:" );
				try {
					unload();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("unloader end" );
				cart.finishOperation();
			}
		}
		System.out.println("-- unloader finished work" );
	}
}
