import java.util.Iterator;

public interface Set<E> {
	public boolean add(E e);
	public boolean remove(E e);
	public boolean contains(E e);
	public Iterator<E> iterator();
	
	public void addAll(Set<E> other);
	public void retainAll(Set<E> other);
	public void removeAll(Set<E> other);
}
