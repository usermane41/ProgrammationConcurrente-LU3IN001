package carlvbn.raytracing.rendering;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
public class ThreadPool {
	private ArrayBlockingQueue<Runnable> bq ;
	private List<Thread> lt;
	public ThreadPool(int size,int N) {
		bq = new ArrayBlockingQueue<Runnable>(size);
		lt= new ArrayList<>();
		for(int i=0; i<N;i++) {
			Thread t = new Thread(new Worker());
			lt.add(t);
			t.start();
		}
	}
	public void submit(Runnable r) throws InterruptedException {
		bq.add(r);
	}
	
	private class Worker implements Runnable {

		@Override
		public void run() {
			try {
				while(!Thread.interrupted()) {
					Runnable r=bq.take();
					r.run();
				}
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public void shutdown() {
		for(Thread t : lt) {
			t.interrupt();
			try {
				t.join();
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
		bq.clear();
		lt.clear();
	}
}
