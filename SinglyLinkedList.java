
public class SinglyLinkedList {

	private class Node {
		private int value;
		private Node next;

		public Node(int v, Node n) {
			this.value = v;
			this.next = n;
		}
	}

	private Node head;
	private int size;

	public SinglyLinkedList() {
		this.head = null;
		size = 0;
	}

	public int min() {
		if(this.isEmpty()) {
			throw new IllegalStateException("List is Empty");
		} else {
			int min = Integer.MAX_VALUE;
			Node index = head;
			int position = 0;
			while(position != size) {
				if(index.value < min) {
					min = index.value;
				}
				index = index.next;
				++position;
			}
			return min;
		}
	}

	public int max() {
		if(this.isEmpty()) {
			throw new IllegalStateException("List is Empty");
		}else {
			int max = Integer.MIN_VALUE;
			Node index = this.head;
			int position = 0;
			while(position != size) {
				if(index.value > max) {
					max = index.value;
				}
				index = index.next;
				++position;
			}
			return max;
		}
	}

	public int removeLast() {
		int returnVal;
		if(this.isEmpty()) {
			throw new IllegalStateException("List is Empty");
		} else if(this.size == 1){
			returnVal = this.head.value;
			this.head = null;
		} else {
			Node index = this.head;
			while(index.next.next != null) {
				index = index.next;
			}
			returnVal = index.next.value;
			index.next = null;
		}
		--size;
		return returnVal;
	}

	public void addFirst(int newValue) {
		Node newNode = new Node(newValue, head);
		this.head = newNode;
		++size;
	}

	public int removeFirst() {
		if(this.isEmpty()) {
			throw new IllegalStateException("List is Empty");
		}else {
			int temp = this.head.value;
			this.head = this.head.next;
			--size;
			return temp;
		}
	}

	public boolean isEmpty() {
		//return head == null;
		return size == 0;
	}

	public int size() {
		return size;
	}

	@Override
	public String toString() {
		if (this.isEmpty()) {
			return "List is Empty";
		} else {
			Node current = this.head;
			String result = "";
			while(current != null) {
				result += current.value + " ";
				current = current.next;
			}
			return result;
		}
	}

}
