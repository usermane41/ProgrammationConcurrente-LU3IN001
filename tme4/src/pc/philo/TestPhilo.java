package pc.philo;

public class TestPhilo {

	public static void main (String [] args) {
		final int NB_PHIL = 5;
		Thread [] tPhil = new Thread[NB_PHIL];
		Fork [] tChop = new Fork[NB_PHIL];
		
		
		for(int i=0; i<NB_PHIL; i++) {
			tChop[i]= new Fork();
		}

		for(int i=0; i<NB_PHIL; i++) {
			Thread t;
			if(i==NB_PHIL-1) {
				t=new Thread(new Philosopher(tChop[0],tChop[i]));
			}else {
				t=new Thread(new Philosopher(tChop[i],tChop[(i+1)]));
			}	
			tPhil[i]= t;
			t.start();
		}
		
		try {
			Thread.sleep(100);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		for(int i=0; i<NB_PHIL;i++) { 
			tPhil[i].interrupt();
		}
		
		for(Thread t : tPhil) {
			try {
				t.join();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
		System.out.println("Fin du programme");

	}
}