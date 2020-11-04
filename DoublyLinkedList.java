
public class DoublyLinkedList<T> {

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
	private Node header, trailer;

	public DoublyLinkedList() {
		size = 0;
		header = new Node(null, null, null); //intialise sentinels
		trailer = new Node(null, header, null);
		header.next = trailer;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return header.next == trailer;
		//		return trailer.prev == header;
		//		return size == 0;
	}

	private void insertBetween(T newVal, Node nodePrev, Node nodeNext) {
		Node newNode = new Node(newVal, nodePrev, nodeNext);
		nodePrev.next = newNode;
		nodeNext.prev = newNode;
		++size;
	}

	public void insertAtHead(T newVal) {
		insertBetween(newVal, header, header.next);
	}

	public void insertAtTail(T newVal) {
		insertBetween(newVal, trailer.prev, trailer);
	}
	
	private T removeBetween(Node nodePrev, Node nodeNext) {
		if(isEmpty()) {
			throw new IllegalStateException("Cannot remove from empty list");
		}
		T returnVal = nodeNext.value;
		nodePrev.next = nodeNext;
		nodeNext.prev = nodePrev;
		--size;
		return returnVal;
	}
	
	public T removeHead() {
		return removeBetween(header, header.next.next);
	}
	
	public T removeTail() {
		return removeBetween(trailer.prev.prev, trailer);
	}
	
	@Override
	public String toString() {
		if(isEmpty()) {
			return "List is Empty";
		}
		Node current = header.next;
		String result = "";
		while(current != trailer) {
			result += current.value + " ";
			current = current.next;
		}
		return result;
	}
	
	public String reverseToString() {
		if(isEmpty()) {
			return "List is Empty";
		}
		Node current = trailer.prev;
		String result = "";
		while(current != header) {
			result += current.value + " ";
			current = current.prev;
		}
		return result;
	}
}
