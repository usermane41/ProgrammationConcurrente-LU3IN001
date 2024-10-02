package pc.rec;

import pc.IList;

public class SimpleListRecFine<T> implements IList<T> {
	private Chainon<T> head; // Premier élément de la liste

	private static class Chainon<T> {
		T data; // Donnée du chaînon
		Chainon<T> next; // Référence au chaînon suivant

		public Chainon(T data) {
			this.data = data;
			// NB : next est null par défaut
		}

		public int size() {
			synchronized(this) {
				if (next == null) {
					return 1;
				}
			}
			return 1 + next.size();
		}

		public boolean contains(T element) {
		    synchronized (this) {
		        if (data.equals(element)) {
		            return true;
		        } else if (next == null) {
		            return false;
		        }
		    }
		    return next.contains(element);
		}
		
		public void add(T element) {
			synchronized(this) {
				if (next == null) {
					next = new Chainon<>(element);
				}else {
					next.add(element);
				}
			}	
		}

	}

	public void clear() {
        head = null;
    }

	public int size() {
		Chainon<T> cur; 
		synchronized (this) { 
			if (head == null) {
				return 0;
			} else {
				cur=head;
			}
		}
		return cur.size();
	}

	public boolean contains(T element) {
	    Chainon<T> cur;
	    synchronized (this) {
	        if (head == null) {
	            return false;
	        } else {
	            cur = head;
	        }
	    }
	    return cur.contains(element);
	}


	public void add(T element) {
		Chainon<T> cur;
		synchronized(this) {
			if (head == null) {
				head = new Chainon<>(element);
				return;
			} else {
				cur =head;
			}
		}
		cur.add(element);
	}

}
