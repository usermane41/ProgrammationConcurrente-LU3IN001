
package pc;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class MatriceEntiere {
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

}/*




 
 
package pc;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.channels.NonWritableChannelException;
import java.util.Scanner;


public class MatriceEntiere {
	
	private int [][] matrice;
	
	public MatriceEntiere(int lig, int col) {
		matrice=new int [lig][col];
		for(int i=0; i<lig; i++) {
			for(int j=0; j<col; j++) {
				matrice[i][j]=0;
			}
		}
	}
	
	public int getElem(int lig, int col) {
		return matrice[lig][col];
	}
	
	public void setElem(int lig, int col, int val) {
		matrice[lig][col]=val;
	}
	
	public int nbLignes() {
		return matrice.length;
	}
	
	public int nbColonnes() {
		return matrice[0].length;
	}
	
	/*public static MatriceEntiere parseMatrix(File fichier) throws IOException{
		try (Scanner scanner = new Scanner(fichier)) {
			int lig = scanner.nextInt();
			int col = scanner.nextInt();
			MatriceEntiere res= new MatriceEntiere(Integer.parseInt(lig), Integer.parseInt(col));
			int li=0;
            while (scanner.hasNextLine()) {
                for(int i=0; i< col; i++) {
                	int Number = scanner.nextInt();
                	res.setElem(li, i, Number);
                }
                li++;
            }
            return res;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
	}
	public static MatriceEntiere parseMatrix(File fichier) throws IOException{
		try(BufferedReader in = new BufferedReader( new FileReader(fichier))) {
			int nblig =Integer.parseInt(in.readLine());
			int nbcol =Integer.parseInt(in.readLine());
			MatriceEntiere res = new MatriceEntiere(nblig, nbcol);
			for(int i = 0; i<nblig; i++) {
				String line = in.readLine();
				String[] num = line.split(" ");
				for(int j=0; j<nbcol; j++) {
					res.setElem(i, j, Integer.parseInt(num[j]));
				}
			}
			return res;
		} catch (IOException e) {
			
		}
		return null;
	}
	public String toString() {
		String res=this.nbLignes()+"\n"+this.nbColonnes()+"\n";
		for(int i=0; i<this.nbLignes(); i++) {
			for(int j=0; j<this.nbColonnes(); j++) {
				res+=this.getElem(i, j) + " ";
			}
			res+="\n";
		}
		return res;
	}
	public boolean equals(Object o) {
		if(o==null) {
			return false;
		}
		if(o instanceof MatriceEntiere) {
			for(int i=0; i<matrice.length; i++) {
				for(int j=0; j<matrice[i].length; j++) {
					if(matrice[i][j]!=((MatriceEntiere)o).matrice[i][j]) {
						return false;
					}
				}
			}
			return true;
		}
		return false;
	}
	
	public MatriceEntiere ajoute(MatriceEntiere m) throws TaillesNonConcordantesException{
		if((m.nbColonnes()!=this.nbColonnes())||(m.nbLignes()!=this.nbLignes())) {
			throw new TaillesNonConcordantesException("les taille des matrice ne concorde pas");
		}
		MatriceEntiere res=new MatriceEntiere(this.nbLignes(), this.nbColonnes());
		for(int i=0; i<this.nbLignes(); i++) {
			for(int j=0; j<this.nbColonnes(); j++) {
				res.setElem(i, j, this.getElem(i, j)+m.getElem(i, j));
			}
		}
		return res;
	}
	
	public MatriceEntiere produit(MatriceEntiere m)throws TaillesNonConcordantesException{
		if(this.nbColonnes()!=m.nbLignes()) {
			throw new TaillesNonConcordantesException("les taille des matrice ne concorde pas");
		}
		MatriceEntiere res =new MatriceEntiere(this.nbLignes(), m.nbColonnes());
		for(int i=0; i<this.nbLignes(); i++) {
			for(int j=0; j<m.nbColonnes(); j++) {
				for(int k=0; k<this.nbColonnes();k++) {
					res.setElem(i, j, res.getElem(i, j)+ (this.getElem(i, k)*m.getElem(k, j)));
				}
			}
		}
		return res;
	}
	
	
	
	public MatriceEntiere produitParScalaire(int sca) {
		MatriceEntiere res=new MatriceEntiere(this.nbLignes(), this.nbColonnes());
		for(int i=0; i<this.nbLignes(); i++) {
			for(int j=0; j<this.nbColonnes(); j++) {
				res.setElem(i, j, this.getElem(i, j)*sca);
			}
		}
		return res;
	}
	
	public MatriceEntiere transposee() {
		MatriceEntiere res =new MatriceEntiere(this.nbColonnes(), this.nbLignes());
		for(int i=0; i<this.nbLignes(); i++) {
			for(int j=0; j<this.nbColonnes(); j++) {
				res.setElem(j, i, this.getElem(i, j));
			}
		}
		return res;
	}
}*/