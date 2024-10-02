package pc.philo;
import java.util.concurrent.locks.*;
public class Fork{
	private ReentrantLock l = new ReentrantLock();
	public void acquire () {
	l.lock();	
    }
	
	
	public void release () {
		l.unlock();
	}
}
