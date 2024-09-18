package pc.countwords;

public class Lancecell implements Runnable{
	private int i,j;
	private MatriceEntiere res;
	private MatriceEntiere m1,m2;
	
	public Lancecell(MatriceEntiere res,MatriceEntiere m1, int i,int j,MatriceEntiere m2) {
		this.res=res;
		this.i=i;
		this.j=j;
		this.m2=m2;
		this.m1=m1;
	}
	
	public void run() {
		int sum=0;
        for(int k=0; k<m1.nbColonnes();k++){
        	sum+=m1.getElem(i, k) * m2.getElem(k, j);
        }
        res.setElem(i, j, sum);
	}
}
