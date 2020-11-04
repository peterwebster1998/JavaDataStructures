
public class DLLStack<T> implements Stack<T> {
	
	private DoublyLinkedList<T> stack;

	public DLLStack() {
		stack = new DoublyLinkedList<>();
	}
	
	@Override
	public void push(T e) {
		// TODO Auto-generated method stub
		stack.insertAtHead(e);
	}

	@Override
	public T pop() {
		// TODO Auto-generated method stub
		if(isEmpty()) {
			return null;
		}
		return stack.removeHead();
	}

	@Override
	public T top() {
		if(isEmpty()) {
			return null;
		}
		// TODO Auto-generated method stub
		T temp = stack.removeHead();
		stack.insertAtHead(temp);
		return temp;
	}

	@Override
	public Boolean isEmpty() {
		// TODO Auto-generated method stub
		return stack.isEmpty();
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return stack.size();
	}

}
