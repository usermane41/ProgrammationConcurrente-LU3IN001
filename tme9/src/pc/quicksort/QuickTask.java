package pc.quicksort;

import java.util.concurrent.RecursiveAction;

public class QuickTask extends RecursiveAction {
	private final int[] array;
	private final int low;
	private final int high;
	public QuickTask(int[] array, int low, int high) {
		this.array = array;
		this.low = low;
		this.high = high;
	}
	
	@Override
	protected void compute() {
		if(high-low <1000) {
			QuickSort.quickSort( array,  low,  high);
		}
		else {
			if (low < high) {
				int pi = QuickSort.partition(array, low, high);
					
				QuickTask q1= new QuickTask(array, low, pi);
				QuickTask q2 =new QuickTask(array, pi + 1, high);
				invokeAll(q1,q2);
			}
		}
	}

}
