
public class SLLQueue<T> implements Queue<T> {

	private SinglyLinkedList<T> queue;

	public SLLQueue() {
		queue = new SinglyLinkedList<>();
	}
	
	@Override
	public void enqueue(T e) {
		// TODO Auto-generated method stub
		queue.addFirst(e);
	}

	@Override
	public T dequeue() {
		// TODO Auto-generated method stub
		if(queue.isEmpty()) {
			return null;
		}
		return queue.removeLast();
	}

	@Override
	public T first() {
		// TODO Auto-generated method stub
		return queue.getLast();
	}

	@Override
	public Boolean isEmpty() {
		// TODO Auto-generated method stub
		return queue.isEmpty();
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return queue.size();
	}

}
