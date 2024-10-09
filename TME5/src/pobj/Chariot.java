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
	
	public synchronized void charge(AleaObjet o)  {
		while(!ischargeturn || o.getPoids()+poids>(poidsMax) || ch.size()>=nbMax ) {
			try {
				wait();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		poids+=o.getPoids();
		ch.add(o);
		if((poids>=(poidsMax)) || (ch.size()>=nbMax)) {
			ischargeturn=false;
			notifyAll();
		}
	}
	
	public synchronized void decharge() {
		while(ischargeturn) {
			try {
				wait();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
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
