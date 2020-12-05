package ua.itea;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

public class Carrier implements Runnable {
	private int transportationTime=5;
	private Exchanger<Cart> loaderExchanger;
	private Exchanger<Cart> unloaderExchanger;
	private Cart  cart;

	public Carrier(Exchanger<Cart> loaderExchanger, Exchanger<Cart> unloaderExchanger, Cart cart) {
		this.loaderExchanger = loaderExchanger;
		this.unloaderExchanger = unloaderExchanger;
		this.cart = cart;
		
		new Thread(this).start();
	}

	@Override
	public void run() {
		
		while(true) {
			try {
				cart = loaderExchanger.exchange(null);
				if(cart==null) {	// The End
					unloaderExchanger.exchange(cart);
					System.out.println("-- carier finished work" );
					break;
				}
					System.out.println("carier started to unload" );
					TimeUnit.SECONDS.sleep(transportationTime);
					System.out.println("carier finished" );
				unloaderExchanger.exchange(cart);
				
				cart = unloaderExchanger.exchange(null);
					System.out.println("carier started to load" );
					TimeUnit.SECONDS.sleep(transportationTime);
					System.out.println("carier finished" );
				loaderExchanger.exchange(cart);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
