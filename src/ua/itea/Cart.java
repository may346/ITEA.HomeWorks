package ua.itea;

public class Cart {
	private int capacityMass;
	private int cargoMass;
	
	private boolean stopWork=false;	
	private boolean readyToLoad=true;
	private boolean readyToUnload=false;
	
	public synchronized void startLoad() {
		  while (!readyToLoad) {
		        try {
		            wait();
		        } catch (InterruptedException e) {
		        }
		  }
		  // start to load - clear flag
		  readyToLoad = false;	
	}
	public synchronized void startUnload() {
		  while (!readyToUnload&&!stopWork) {
		        try {
		            wait();
		        } catch (InterruptedException e) {
		        }
		  }
		  // start to unload - clear flag
		  readyToUnload = false;	
	}
	public synchronized void startTransition() {
		  while (readyToUnload||readyToLoad) {
		        try {
		            wait();
		        } catch (InterruptedException e) {
		        }
		  }
		  if(isNotEmpty()) readyToUnload = true;
		  else	readyToLoad = true;
	}
	public synchronized void finishOperation() {
		notifyAll();
	}
	
	public Cart(final int capacityMass) {
		this.capacityMass = capacityMass;
	}
	public int getCapacity() {
		return capacityMass;
	}
	public int getCargo() {
		return cargoMass;
	}
	public boolean isNotEmpty() {
		return cargoMass!=0;
	}
	public boolean isStopWork() {
		return stopWork;
	}
	public void setStopWork(){
		stopWork = true;
	} 
	public synchronized boolean isReadyToLoad() {
		return readyToLoad;
	}
	public void setReadyToLoad(boolean readyToLoad) {
		this.readyToLoad = readyToLoad;
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
