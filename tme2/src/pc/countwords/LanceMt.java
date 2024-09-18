package pc.countwords;

public class LanceMt implements Runnable {
	private int i;
	private MatriceEntiere res;
	private int n;
	public LanceMt(MatriceEntiere res, int i, int n) {
		this.res=res;
		this.i=i;
		this.n=n;
	}
	
	public void run() {
		for(int j=0; j<res.nbColonnes(); j++) {
			res.setElem(i, j,res. getElem(i, j)*n);
		}
	}
}
