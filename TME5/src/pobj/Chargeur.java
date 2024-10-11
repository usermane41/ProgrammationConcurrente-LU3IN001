package pobj;

public class Chargeur implements Runnable {
	private AleaStock cstock;
	private Chariot ch;
	public Chargeur(AleaStock stock, Chariot ch) {
		cstock = stock;
		this.ch=ch;
	}
	
	public void run() {
		while(true){
			AleaObjet o=cstock.getObjet();
			if(o==null) break;
			ch.charge(o,cstock);
		}
		
	}
	
}
