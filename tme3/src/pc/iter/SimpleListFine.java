package pc.iter;

import pc.IList;
import pc.iter.SimpleList.Chainon;

public class SimpleListFine<T> implements IList<T>{
	private Chainon<T> head; // Premier élément de la liste

	private static class Chainon<T> {
		T data; // Donnée du chaînon
		Chainon<T> next; // Référence au chaînon suivant

		public Chainon(T data) {
			this.data = data;
			// NB : next est null par défaut
		}

	}

	public SimpleListFine() {
		head = null;
	}

	@Override
	public int size() {
		int size = 0;
		Chainon<T> cur = head;
		while (cur != null) {
			synchronized (cur) {
				size++;
				cur = cur.next;
			}
		}
		return size;
	}

	@Override
	public void add(T element) {
		if (head == null) {
			head = new Chainon<>(element);
			return;
		}
		Chainon<T> cur;

		synchronized (this) {
		        cur = head;
		}
		while(cur!= null){
			synchronized(cur) {
				if (cur.next == null) {
					cur.next = new Chainon<>(element);
					break;
				}
				cur=cur.next;
			}
		}
	}

	@Override
	public boolean contains(T element) {
	    Chainon<T> cur;

	    synchronized (this) {
	        cur = head;
	    }

	    while (cur != null) {
	        synchronized (cur) {
	            if (cur.data.equals(element)) {
	                return true;
	            }
	        }
	        cur = cur.next;
	    }

	    return false;
	}

	@Override
	public synchronized void clear() {
		head = null;
		// NB : grace au gc, les éléments de la liste sont supprimés
		// dans d'autres langages, il faudrait les supprimer un par un (e.g. C++)
	}

}
