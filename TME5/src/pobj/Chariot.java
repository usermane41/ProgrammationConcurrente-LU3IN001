package pobj;

import java.util.ArrayList;
import java.util.List;

public class Chariot {
	private List<AleaObjet> ch;
	private int poidsMax;
	private int nbMax;
	private int poids;
	private boolean ischargeturn =true;
	
	
	public Chariot(int pMax,int nbmax) {
		ch= new ArrayList<>();
		poidsMax=pMax;
		nbMax=nbmax;
		poids=0;
	}
	
	public synchronized void charge(AleaObjet o,AleaStock cstock)  {
		while(!ischargeturn && o.getPoids()+poids>(poidsMax) && ch.size()==nbMax) {
			try {
				System.out.println("-Charge va attendre.");
				wait();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		poids+=o.getPoids();
		System.out.println("Travaille chargeur");
		ch.add(o);
		if((ch.size()==nbMax) || cstock.isEmpty() ) {
			ischargeturn=false;
			notifyAll();
		}
	}
	
	public synchronized void decharge() {
		while(ischargeturn) {
			try {
				System.out.println("-Decharge va attendre.");
				wait();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("En train de Decharge");
		AleaObjet o = ch.remove(ch.size()-1);
		poids-=o.getPoids();
		if(ch.size()==0) {
			ischargeturn =true;
			notifyAll();
		}
	}
	
	public synchronized boolean isEmpty() {
	    return ch.size()==0;
	}

	
}
