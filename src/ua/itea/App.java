package ua.itea;

public class App {
	public static void main(String[] args) {
		Cart cart = new Cart(6);
		Heap heap = new Heap(100);
		
		new Loader(cart, heap);
		new Carrier(cart);
		new Unloader(cart);		
	}
}
