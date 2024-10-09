package pobj;

public class Dechargeur implements Runnable {
	private Chariot ch;
	private AleaStock cstock;
	
	public Dechargeur(AleaStock cstock,Chariot ch) {
		this.ch=ch;
		this.cstock=cstock;
	}

	@Override
	public void run() {
		while (true) {
			if(cstock.isEmpty() && ch.isEmpty()){
				break;
			}
			ch.decharge();
		}
		
	}
}
