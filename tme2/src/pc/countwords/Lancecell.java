package pc.countwords;

public class Lancecell implements Runnable{
	private int i,j;
	private MatriceEntiere res;
	private MatriceEntiere m2;
	public Lancecell(MatriceEntiere res, int i,int j,MatriceEntiere m2) {
		this.res=res;
		this.i=i;
		this.j=j;
		this.m2=m2;
	}
	
	public void run() {
        for(int k=0; k<res.nbColonnes();k++){
            res.setElem(i, j, res.getElem(i, k) * m2.getElem(k, j));
        }
	}
}
