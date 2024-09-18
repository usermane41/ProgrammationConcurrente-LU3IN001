package pc.countwords;

public class LanceMt implements Runnable {
	private int i;
	private MatriceEntiere res;
	private MatriceEntiere mat;
	private int n;
	public LanceMt(MatriceEntiere res,MatriceEntiere mat, int i, int n) {
		this.res=res;
		this.i=i;
		this.n=n;
		this.mat=mat;
	}
	
	public void run() {
		for(int j=0; j<res.nbColonnes(); j++) {
			mat.setElem(i, j,res. getElem(i, j)*n);
		}
	}
}
