
public interface Queue<T> {
	
	public void enqueue(T e);
	public T dequeue();
	public T first();
	public Boolean isEmpty();
	public int size();
}
