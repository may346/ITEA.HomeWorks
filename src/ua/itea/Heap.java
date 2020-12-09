package ua.itea;

public class Heap {
	private int capacityMass; // = 100;
	public Heap() {
		capacityMass = 0; 
	}
	public Heap(int capacityMass) {
		this.capacityMass = capacityMass;
	}

	public int getCapacityMass() {
		return capacityMass;
	}
	public void setCapacityMass(int capacityMass) {
		this.capacityMass = capacityMass;
	}
	public void takeCapacityMass(int delta) {
		this.capacityMass -= delta;
	}
	public boolean isEmpty() {
		return capacityMass<=0;
	}
}
