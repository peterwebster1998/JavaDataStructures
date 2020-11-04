
public class ArrayStack<T> implements Stack<T> {
	
	private T[] stack;
	private int topIndex;
	private int size;
	
	public ArrayStack(int capacity) {
		stack = (T[]) new Object[capacity];
		size = 0;
		topIndex = -1;
	}

	@Override
	public void push(T e) {
		// TODO Auto-generated method stub
		if(size == stack.length) {
			throw new IllegalStateException("Stack is full");
		}
		topIndex++;
		stack[topIndex] = e;
		++size;		
	}

	@Override
	public T pop() {
		// TODO Auto-generated method stub
		if(isEmpty()) {
			return null;
		}
		T temp = stack[topIndex];
		stack[topIndex] = null;
		topIndex--;
		size--;
		return temp;
	}

	@Override
	public T top() {
		// TODO Auto-generated method stub
		if(isEmpty()) {
			return null;
		}
		return stack[topIndex];
	}

	@Override
	public Boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
		//return topIndex == -1;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}
	
}
