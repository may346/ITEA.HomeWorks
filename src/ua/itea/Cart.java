package ua.itea;

public class Cart {
	private int capacityMass;
	private int cargoMass;
	
	public Cart(final int capacityMass) {
		this.capacityMass = capacityMass;
	}
//	public boolean isNotEmpty() {
//		return capacityMass!=0;
//	}
	public int getCapacity() {
		return capacityMass;
	}
	public int getCargo() {
		return cargoMass;
	}
	
	public int takeCargo(int requestMass) {
		if( requestMass>cargoMass ) 
			requestMass = cargoMass;
		cargoMass -= requestMass;
		return requestMass;
	}	
	public int putCargo(int putMass) {
		int emptySpace = capacityMass - cargoMass;
		if(putMass>emptySpace)
			putMass = emptySpace;		
		cargoMass += putMass;
		return putMass;
	}
	
	@Override
	public String toString() {
		return "Cargo:" + getCargo() + "/" + getCapacity();
	}
  
  
}
