package pobj;

import java.util.ArrayList;
import java.util.List;

public class StockTest {
	public static void main(String[] args) {
		AleaStock s= new AleaStock(30);
		Chariot ch= new Chariot(1000,20);
		
		Chargeur c1 = new Chargeur(s,ch);
		Chargeur c2= new Chargeur(s,ch);
		Dechargeur d = new Dechargeur(s,ch);
		
		List<Thread> exe = new ArrayList<>();
		Thread n1 = new Thread(c1);
		Thread n2 = new Thread(c2);
		Thread n3 = new Thread(d);
		
		exe.add(n1);
		exe.add(n2);
		exe.add(n3);
		
		n1.start();
		n3.start();
		n2.start();

		
		for(Thread t: exe) {
			if(t != n3) {
				try {
					t.join();
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("Tous les chargeurs ont terminé.");
		
		try {
			n3.join();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Tous les chargeurs et le déchargeur ont terminé.");
		
	}
}
