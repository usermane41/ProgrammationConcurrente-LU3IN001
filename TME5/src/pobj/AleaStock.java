package pobj;

import java.util.ArrayList;
import java.util.List;

public class AleaStock {
	private int taille;
	private List<AleaObjet> stock;
	private boolean charge;
	
	public AleaStock(int taille) {
		this.taille=taille;
		stock = new ArrayList<>();
		for(int i=0; i<taille;i++) {
			stock.add(new AleaObjet(1,100));
		}
	}
	
	public synchronized boolean isEmpty() {
		return stock.size()==0;
	}
	
	public synchronized AleaObjet getObjet() {
		if(isEmpty()) {
			return null;
		}
		AleaObjet res= stock.get(stock.size()-1);
		stock.remove(res);
		return res; 
	}
	
	
	
}
