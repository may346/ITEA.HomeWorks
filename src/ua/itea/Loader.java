package ua.itea;

import java.util.concurrent.TimeUnit;

public class Loader implements Runnable {
	private int loadingSpeed = 3;
	private Cart  cart;
	private Heap	heap;
	
	public Loader(Cart cart, Heap	heap) {
		this.cart = cart;
		this.heap = heap;
		
		new Thread(this).start();
	}
	public void load() throws InterruptedException {
		int delta;
		
		while(true) {
			delta = cart.putCargo( (heap.getCapacityMass()<loadingSpeed)?
									heap.getCapacityMass():loadingSpeed );
			if(delta==0) // cart is full;
				break;
			else {
				System.out.print("*" );
				TimeUnit.SECONDS.sleep( 1 );
				heap.takeCapacityMass(delta);
			}
		}
	}

	@Override
	public void run() {
		synchronized(cart) {			
			while( true ) {
				cart.startLoad();
				
				if(heap.isEmpty()) {
					cart.setStopWork(); 
					cart.finishOperation();
					break;
				}
				
				System.out.println("loader start: " + heap.getCapacityMass());
				try {
					load();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("loader end: " + heap.getCapacityMass());
				cart.finishOperation();
			}
		}
		System.out.println("-- loader finished work" );
	}
}
