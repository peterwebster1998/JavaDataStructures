
public class SLLStack<T> implements Stack<T> {
	
	private SinglyLinkedList<T> stack;

	public SLLStack() {
		stack = new SinglyLinkedList<>();
	}

	@Override
	public void push(T e) {
		// TODO Auto-generated method stub
		stack.addFirst(e);
	}

	@Override
	public T pop() {
		// TODO Auto-generated method stub
		if(isEmpty()) {
			return null;
		}
		return stack.removeFirst();
	}

	@Override
	public T top() {
		// TODO Auto-generated method stub
		if(isEmpty()) {
			return null;
		}
		T temp = stack.removeFirst();
		stack.addFirst(temp);
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
