
package pc.countwords;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;




public class MatriceEntiere{
    private int[][] mat;

    public MatriceEntiere(int lig, int col){
    	mat = new int[lig][col]; 
        for (int[] elem : mat) {
            for (int i = 0; i < elem.length; i++) {
                elem[i] = 0;
            }
        }
    }

    public int getElem(int lig, int col){
        return mat[lig][col];
    }

    public void setElem(int lig, int col, int val){
        mat[lig][col]= val;
    }


    public int nbLignes(){
        return mat.length;
    }
    public int nbColonnes(){
        return mat[0].length;
    }

    public static MatriceEntiere parseMatrix(File fichier) throws IOException{
        MatriceEntiere mat;
        try(BufferedReader br = new BufferedReader(new FileReader(fichier))){
                String ligne;
                ligne=br.readLine();
                int i = Integer.parseInt(ligne);
                ligne=br.readLine();
                int j = Integer.parseInt(ligne);

                mat = new MatriceEntiere(i, j);
                int l=0;
                while((ligne = br.readLine()) != null){
                    String[] lig = ligne.split(" ");
                    
                    for(int k=0; k< lig.length; k++){
                        int nbr= Integer.parseInt(lig[k]);
                        mat.setElem(l, k, nbr);
                    }
                    l++;
                }
        }
        return mat;
    }

    @Override
    public String toString(){
        String res ="";
        res+=nbLignes()+"\n";
        res += nbColonnes()+ "\n";
        for(int i=0; i<nbLignes();i++){
            for(int j=0; j<nbColonnes();j++){
                res += mat[i][j] + " ";
            }
            res+="\n";
        }
        return res;
    }

    @Override
   public boolean equals(Object o){
        if (this == o){
            return true;
        }
        if(! (o instanceof MatriceEntiere)){
            return false;
        }	
        MatriceEntiere m = (MatriceEntiere) o;
        if ((nbLignes() == m.nbLignes()) && (nbColonnes() == m.nbColonnes())) {
            for(int i=0; i<nbLignes();i++){
                for(int j=0; j<nbColonnes();j++){
                    if(getElem(i, j)!= m.getElem(i,j)) return false;
                	}
            	}
            return true;
       		}
        return false;
    }
    

    public MatriceEntiere ajoute(MatriceEntiere m)throws TaillesNonConcordantesException{
        MatriceEntiere res= new MatriceEntiere(nbLignes(),nbColonnes()); 
        if ((nbLignes() != m.nbLignes())&& (nbColonnes()!=m.nbColonnes())){
            throw new TaillesNonConcordantesException("les matrices ne font pas la meme taille");
        }
        for(int i=0; i<nbLignes();i++){
            for(int j=0; j<nbColonnes();j++){
                int nbr= getElem(i, j) + m.getElem(i, j);
                res.setElem(i, j, nbr);
            }
        } 
        return res;
    }

    public MatriceEntiere produit(MatriceEntiere m)throws TaillesNonConcordantesException{
        if ((nbColonnes() != m.nbLignes())){
            throw new TaillesNonConcordantesException("les matrices ne font pas la meme taille");
        }
        MatriceEntiere res = new MatriceEntiere(nbLignes(), m.nbColonnes());
        for(int i=0; i<nbLignes();i++){
            for(int j=0; j<m.nbColonnes();j++){
            	int nbr=0;
                for(int k=0; k<nbColonnes();k++){
                    nbr+= getElem(i, k) * m.getElem(k, j);
                    res.setElem(i, j, nbr);
                }
            }
        }
        return res;
    }
    
    public MatriceEntiere produitParScalaire(int sca) {
		MatriceEntiere res=new MatriceEntiere(nbLignes(), nbColonnes());
		for(int i=0; i<nbLignes(); i++) {
			for(int j=0; j<nbColonnes(); j++) {
				res.setElem(i, j, getElem(i, j)*sca);
			}
		}
		return res;
	}
    
    public MatriceEntiere produitParScalaireMT(int n) {
		MatriceEntiere res=new MatriceEntiere(nbLignes(), nbColonnes());
		Thread[] ttab = new Thread[res.nbLignes()];
		for(int i=0; i<nbLignes(); i++) {
			LanceMt m = new LanceMt(res,i,n);
			Thread t = new Thread(m);
			ttab[i]=t;
			t.start();
			
		}
		for(Thread tk : ttab) {
			try {
				tk.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return res;
	}
    
    public MatriceEntiere transposee(){
        MatriceEntiere res= new MatriceEntiere(nbColonnes(),nbLignes());
        for(int i=0; i<nbLignes();i++){
            for(int j=0; j<nbColonnes();j++){
                int nbr= getElem(i,j);
                res.setElem(j, i, nbr);
            }
        }

        return res;
    }
    
    
    public MatriceEntiere produitMt(MatriceEntiere m)throws TaillesNonConcordantesException{
        if ((nbColonnes() != m.nbLignes())){
            throw new TaillesNonConcordantesException("les matrices ne font pas la meme taille");
        }
        MatriceEntiere res = new MatriceEntiere(nbLignes(), m.nbColonnes());
        Thread[] ttab = new Thread[res.nbLignes()*res.nbColonnes()];
        int cpt=0;
        for(int i=0; i<nbLignes();i++){
            for(int j=0; j<m.nbColonnes();j++){
            	Lancecell cl = new Lancecell(res,i,j,m);
    			Thread t = new Thread(cl);
    			ttab[cpt] = t;
    			cpt++;
    			t.start();
            }
        }
        for(Thread tk : ttab) {
			try {
				tk.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
        return res;
    }
    

}