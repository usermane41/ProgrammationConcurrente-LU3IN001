package pc.countwords;

import java.io.IOException;
import java.util.Arrays;


public class Lance implements Runnable{
	private String nom;
	private int i;
	int [] wordCount;
	public Lance (String nom,int [] wordCount, int i) {
		this.nom=nom;
		this.wordCount= wordCount;
		this.i = i;
	}
	
	public void run(){
		{
			try {
				wordCount[i] = WordCount.countWords(nom);
			} catch (IOException e) {
				System.err.println("Error reading file: " + nom);
				e.printStackTrace();
			}
		}
		System.out.println("Word count "+i +":" + Arrays.toString(wordCount));
		
	}
}
