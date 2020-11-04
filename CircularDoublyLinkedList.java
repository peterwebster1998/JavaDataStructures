public class CircularDoublyLinkedList<T> {
	private class Node{
		private T value;
		private Node prev, next;

		public Node(T v, Node p, Node n) {
			this.value = v;
			this.prev = p;
			this.next = n;
		}
	}

	private int size;
	private Node cursor;

	public CircularDoublyLinkedList() {
		cursor = new Node(null, cursor, cursor);
		size = 0;
	}

	public void addAfterCursor(T newVal) {
		if(size == 0) {
			Node newNode = new Node(newVal, cursor.prev, cursor.next);
			cursor = newNode;
			cursor.next = cursor;
			cursor.prev = cursor;
		}else {
			Node newNode = new Node(newVal, cursor, cursor.next);
			cursor.next.prev = newNode;
			cursor.next = newNode;
		}
		++size;
	}

	public T deleteCursor() {
		if(isEmpty()) {
			throw new IllegalStateException("Cannot remove from empty list");
		}
		T temp = getValue();
		cursor.prev.next = cursor.next;
		cursor.next.prev = cursor.prev;
		if(cursor.next == cursor) {
			cursor.value = null;
		}
		cursor = cursor.next;
		--size;
		return temp;
	}

	public void advanceCursor(int n) {
		if(n<0) {
			return;
		}
		if(isEmpty()) {
			System.out.println("List is Empty");
			return;
		}
		int cnt = 0;
		while(cnt != n) {
			Node newNode = new Node(cursor.value, cursor.prev, cursor.next);
			cursor.prev.next = newNode;
			cursor.next.prev = newNode;
			cursor = cursor.next;
			++cnt;
		}
	}

	public T getValue() {
		if(isEmpty()) {
			throw new IllegalStateException("List is Empty");
		}
		return cursor.value;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public String toString() {
		if(isEmpty()) {
			return "List is Empty";
		}
		String result = "";
		Node current = cursor;
		do {
			result += current.value + " ";
			current = current.next;
		} while(current != cursor);
		return result;		
	}
}
