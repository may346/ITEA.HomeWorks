package ua.itea;

import java.util.concurrent.Exchanger;

public class App {

	public static void main(String[] args) {
		Exchanger<Cart> loaderCarrier = new Exchanger<>();
		Exchanger<Cart> unloaderCarrier = new Exchanger<>();
		Cart cart = new Cart(6);
		
		new Loader(loaderCarrier, cart);
		new Carrier(loaderCarrier, unloaderCarrier, null);
		new Unloader(unloaderCarrier, null);
	}

}
