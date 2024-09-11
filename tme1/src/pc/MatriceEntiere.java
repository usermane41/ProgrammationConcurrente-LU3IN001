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
        for(int i=1; i<nbLignes();i++){
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
        if( o instanceof MatriceEntiere m){
            if (nbLignes() != m.nbLignes()) return false;
            if (nbColonnes() != m.nbColonnes()) return false;

            for(int i=0; i<nbLignes();i++){
                for(int j=0; j<nbColonnes();j++){
                    if(getElem(i, j)!= m.getElem(i,j)) return false;
                }
            } 
        }
        return true;
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
                for(int k=0; k<nbColonnes();k++){
                    int nbr= getElem(i, k) * m.getElem(k, j);
                    res.setElem(i, j, nbr);
                }
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

}