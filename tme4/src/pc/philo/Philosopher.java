package pc.philo;

public class Philosopher implements Runnable {
	private Fork left;
	private Fork right;

	public Philosopher(Fork left, Fork right) {
		this.left = left;
		this.right = right;
	}

	public void run() {
		while(!Thread.interrupted()) {
			think();
			left.acquire();
			right.acquire();
			eat();
			own();
			left.release();
			right.release();
		}
		// System.out.println(Thread.currentThread().getName() + " has one fork");
	}

	private void eat() {
		System.out.println(Thread.currentThread().getName() + " is eating");
	}

	private void think() {
		System.out.println(Thread.currentThread().getName() + " is thinking");
	}
	private void own() {
		System.out.println(Thread.currentThread().getName() + " possede une baguette");
	}
}
