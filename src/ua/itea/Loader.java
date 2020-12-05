package ua.itea;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

public class Loader implements Runnable {
	private int loadingSpeed = 3;
	private Exchanger<Cart> exchanger;
	private Cart  cart;
	private int capacityMass = 100;
	
	public Loader(Exchanger<Cart> exchanger, Cart cart) {
		this.exchanger = exchanger;
		this.cart = cart;
		
		new Thread(this).start();
	}
	public void load() throws InterruptedException {
		int delta;
		
		while(true) {
			delta = cart.putCargo( (capacityMass<loadingSpeed)?capacityMass:loadingSpeed );
			if(delta==0) // cart is full;
				break;
			else {
				System.out.print("*" );
				TimeUnit.SECONDS.sleep( 1 );
				capacityMass -= delta;
			}
		}
	}

	@Override
	public void run() {
		
		while( true ) {
			try {
				if(capacityMass==0) {
					exchanger.exchange(null);
					break;
				}
				System.out.println("loader start: " + capacityMass);
				//TimeUnit.SECONDS.sleep( cart.getCapacity()/loadingSpeed );
				load();
				System.out.println("loader end: " + capacityMass);
				
				cart = exchanger.exchange(cart);
				if(cart == null ) { 
					cart = exchanger.exchange(cart);
				}				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("-- loader finished work" );
	}
}
