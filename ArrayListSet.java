import java.util.ArrayList;
import java.util.Iterator;

public class ArrayListSet<E> implements Set<E>, Iterable<E> {

	private ArrayList<E> set;
	
	public ArrayListSet() {
		set = new ArrayList<>();
	}
	
	@Override
	public boolean add(E e) {
		if(this.contains(e)) {
			return false;
		}
		set.add(e);
		return true;
	}

	@Override
	public boolean remove(E e) {
		return set.remove(e);
	}

	@Override
	public boolean contains(E e) {
		return set.contains(e);
	}

	@Override
	public Iterator<E> iterator() {
		return set.iterator();
	}

	@Override
	public void addAll(Set<E> other) {
		// Hint: use other.iterator()
		Iterator<E> iter = other.iterator();
		while(iter.hasNext()) {
			this.add(iter.next());
		}
	}

	@Override
	public void retainAll(Set<E> other) {
		// Hint: use other.iterator()
		Iterator<E> iter = other.iterator();
		while(iter.hasNext()) {
			E elem = iter.next();
			if(!contains(elem)) {
				remove(elem);
			}
		}
	}

	@Override
	public void removeAll(Set<E> other) {
		// Hint: use other.iterator()
		Iterator<E> iter = other.iterator();
		while(iter.hasNext()) {
			E elem = iter.next();
			if(contains(elem)) {
				remove(elem);
			}
		}
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (E elem : set) {
			sb.append(elem.toString() + " ");
		}
		return sb.toString();
	}

}
