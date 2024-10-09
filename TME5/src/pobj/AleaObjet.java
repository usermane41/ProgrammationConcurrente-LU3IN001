package pobj;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class AleaObjet {
	private int max;
	private int min;
	private int poids;
	
	public AleaObjet(int min, int max) {
		this.min=min;
		this.max=max;
		poids=ThreadLocalRandom.current().nextInt(min,max);
	}
	
	public synchronized int getPoids() {
		return poids;
	}
	
	public String toString() {
		return "Poids: " + poids;
	}
	
}
