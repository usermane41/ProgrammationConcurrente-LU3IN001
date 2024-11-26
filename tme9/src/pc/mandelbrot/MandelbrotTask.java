package pc.mandelbrot;

import java.util.concurrent.RecursiveAction;

public class MandelbrotTask extends RecursiveAction {
	private final int debut;
	private final int fin;
	private final int[] imageBuffer;
	private BoundingBox boundingBox;
	public static final int THRESHOLD =5000;
	private int maxIterations;
	public MandelbrotTask (int debut, int fin,int[] imageBuffer,BoundingBox boundingBox,int max) {
		this.debut=debut;
		this.fin=fin;
		this.imageBuffer=imageBuffer;
		this.boundingBox=boundingBox;
		maxIterations=max;
	}
	@Override
	protected void compute() {
		if((fin-debut)*boundingBox.width<THRESHOLD) {
			for (int py = debut; py < fin; py++) {
				for (int px = 0; px < boundingBox.width; px++) {
					int color = MandelbrotCalculator.computePixelColor(boundingBox, maxIterations, px, py);

					// Set the pixel in the image buffer
					imageBuffer[py * boundingBox.width + px] = color;
				}
			}
		}
		else {
			int fin1= fin - (fin-debut)/2;
			MandelbrotTask t1= new MandelbrotTask(debut,fin1,imageBuffer,boundingBox,maxIterations);
			MandelbrotTask t2= new MandelbrotTask(fin1,fin,imageBuffer,boundingBox,maxIterations);
			invokeAll(t1,t2);
		}
	}

}
