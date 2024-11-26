package pc.quicksort;

public class QuickMain {
	public static void main (String[] args) {
		long startTime = System.currentTimeMillis();
		int[] tab = QuickSort.generateRandomArray(1000000);
		QuickSort.quickSortp(tab, 0, tab.length-1);
		System.out.println("Execution time: " + (System.currentTimeMillis() - startTime) + "ms");
	}
}
