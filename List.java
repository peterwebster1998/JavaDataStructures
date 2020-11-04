
public interface List<E> {

	public E get(int i) throws IndexOutOfBoundsException;
	public void set(int i, E e) throws IndexOutOfBoundsException;
	public void add(int i, E e) throws IndexOutOfBoundsException;
	public E remove(int i) throws IndexOutOfBoundsException;
	public boolean isEmpty();
	public int size();
}
