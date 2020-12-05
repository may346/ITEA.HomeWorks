package ua.itea;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

public class Unloader implements Runnable {
	private int unloadingSpeed = 2;
	private Exchanger<Cart> exchanger;
	private Cart  cart;

	public Unloader(Exchanger<Cart> exchanger, Cart cart) {
		this.exchanger = exchanger;
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
		while(true) {
			try {
				cart = exchanger.exchange(null);
				if(cart==null)	// The End
							break;				
				System.out.println("unloader start" );
				//TimeUnit.SECONDS.sleep( cart.getCapacity()/unloadingSpeed );
				unload();
				System.out.println("unloader end" );
				
				cart = exchanger.exchange(cart);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("-- unloader finished work" );
	}
}
