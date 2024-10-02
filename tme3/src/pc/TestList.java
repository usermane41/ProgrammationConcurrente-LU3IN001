package pc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import pc.iter.SimpleList;
import pc.iter.SimpleListFine;
import pc.iter.SimpleListSync;
import pc.rec.SimpleListRec;
import pc.rec.SimpleListRecFine;
import pc.rec.SimpleListRecSync;

public class TestList {

	@Test
	/*public void testSimpleList() {
		IList<String> list = new SimpleList<>();

		runConcurrentTest(list, 10, 1000);
	}

	@Test
	public void testSimpleListRec() {
		IList<String> list = new SimpleListRec<>();

		runConcurrentTest(list, 10, 1000);
	}

	public void testSimpleListRecSync() {
		IList<String> list = new SimpleListRecSync<>();

		runConcurrentTest(list, 10, 1000);
	}
	
	public void testSimpleListSync() {
		IList<String> list = new SimpleListSync<>();

		runConcurrentTest(list, 10, 1000);
	}
	
	public void testSimpleListFine() {
		IList<String> list = new SimpleListFine<>();

		runConcurrentTest(list, 10, 1000);
	}*/

	public void testSimpleListRecFine() {
		IList<String> list = new SimpleListRecFine<>();

		runConcurrentTest(list, 10, 1000);
	}
	
	public static void testList(IList<String> list) {
		// Tests des versions itératives
		list.add("Hello");
		list.add("World");
		System.out.println("Taille : " + list.size());
		assertEquals(2, list.size());
		System.out.println("Contient 'World' : " + list.contains("World"));
		assertEquals(true, list.contains("World"));
		assertEquals(false, list.contains("Master"));
		
		list.clear();
		assertEquals(0, list.size());
		System.out.println("Taille après clear : " + list.size());
	}

	private void runConcurrentTest(IList<String> list, int N, int M) {
		System.out.println("Running test of "+list.getClass().getSimpleName());
		testList(list);

		long startTime = System.currentTimeMillis();

		List<Thread> threads = new ArrayList<>();
		List<String> lvalue = new ArrayList<>();
		
		for(int i=0;i<M;i++) {
			lvalue.add("abc"+i);
		}
		// Create threads to add elements to the list
		for (int i=0; i<N;i++) {
			Thread t = new Thread(new AddTask(list,lvalue));
			threads.add(t);
			t.start();
		}
		// Create threads to check contains for non-existent elements
		for (int i=0; i<N;i++) {
			Thread t = new Thread(new ContainsTask(list,lvalue));
			threads.add(t);
			t.start();
		}
		
		// Start all threads
		// Wait for all threads to finish
		for(Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// Check that the list size is N * M
		assertEquals("List size should be N * M", N * M, list.size());

		long endTime = System.currentTimeMillis();
		System.out.println("Test completed in " + (endTime - startTime) + " milliseconds");
	}

	// TODO support pour les threads
	static class AddTask implements Runnable {
		private IList<String> list;
		private List<String> value;
		public AddTask(IList<String> list, List<String> value) {
			this.list=list;
			this.value=value;
		}
		@Override
		public void run() {
			for(String s : value) {
				list.add(s);
			}
		}
	}
	
	static class ContainsTask implements Runnable {
		private IList<String> list;
		private List<String> value;
		public ContainsTask(IList<String> list, List<String> value) {
			this.list=list;
			this.value=value;
		}
		@Override
		public void run() {
			for(String s : value) {
				list.contains(s);
			}
		}
	}

}

