package ua.itea;

public class Mine {
	private int capacity;
	
	public Mine(int capacity) {
		this.capacity = capacity;
	}

	public int getCapacity() {
		return capacity;
	}
	
	public synchronized int take(int value) {
		if(value>capacity) value = capacity;
		capacity -= value;
		return value;
	}
	
	public boolean isNotEmpty() {
		return capacity!=0;
	}

}
