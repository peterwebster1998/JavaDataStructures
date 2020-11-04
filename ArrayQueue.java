
public class ArrayQueue<T> implements Queue<T>{

	private T[] queue;
	private int backIndex;
	private int size;
	
	public ArrayQueue(int capacity) {
		queue = (T[]) new Object[capacity];
		size = 0;
		backIndex = -1;
	}
	
	@Override
	public void enqueue(T e) {
		// TODO Auto-generated method stub
		if(size == queue.length) {
			throw new IllegalStateException("Queue is full");
		}
		backIndex++;
		queue[backIndex] = e;
		++size;		
	}

	@Override
	public T dequeue() {
		// TODO Auto-generated method stub
		if(isEmpty()) {
			return null;
		}
		T temp = queue[0];
		for(int i = 0;i < backIndex; ++i) {
			queue[i] = queue[i+1];
		}
		backIndex--;
		size--;
		return temp;
	}

	@Override
	public T first() {
		// TODO Auto-generated method stub
		if(isEmpty()) {
			return null;
		}
		return queue[0];
	}

	@Override
	public Boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

}
