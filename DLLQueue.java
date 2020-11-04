
public class DLLQueue<T> implements Queue<T> {

	private DoublyLinkedList<T> queue;

	public DLLQueue() {
		queue = new DoublyLinkedList<>();
	}
	
	@Override
	public void enqueue(T e) {
		// TODO Auto-generated method stub
		queue.insertAtTail(e);
	}

	@Override
	public T dequeue() {
		// TODO Auto-generated method stub
		if(isEmpty()) {
			return null;
		}
		return queue.removeHead();
	}
	

	@Override
	public T first() {
		// TODO Auto-generated method stub
		if(isEmpty()) {
			return null;
		}
		// TODO Auto-generated method stub
		T temp = queue.removeHead();
		queue.insertAtHead(temp);
		return temp;
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
