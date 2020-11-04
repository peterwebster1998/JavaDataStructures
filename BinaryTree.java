import java.util.ArrayList;
import java.util.Iterator;

public class BinaryTree<E> implements Iterable<E>{

	// TODO: complete Node class (implement Position)
	private class Node implements Position<E> {
		private E value;
		private Node parent, left, right;

		public Node(E v, Node p) {
			this.value = v;
			this.parent = p;
			left = null;
			right = null;
		}

		public String toString() {
			return value.toString();
		}

		@Override
		public E getValue() {
			return value;
		}
	}

	private Node root;
	private int size;

	public BinaryTree() {
		size = 0;
		root = null;		
	}

	// Since the Position interface could potentially be
	// use for other data structures as well, we have to
	// make sure the position given to this tree is
	// actually an instance of our Node class and not
	// a Node from another data structure class or
	// another type of Position.
	private Node validatePosition(Position<E> pos) {
		// Note how we have to be specific about saying
		// BinaryTree.Node instead of just Node.
		// This should look reminiscent of the equals() method.
		if (pos == null || !(pos instanceof BinaryTree.Node)) {
			throw new IllegalStateException("Not a position");
		}
		return (BinaryTree.Node) pos;
	}

	public Position<E> addRoot(E value) {
		//if we already have root
		if(root != null) {
			throw new IllegalStateException("Root already exists");
		}
		root = new Node(value, null);
		size++;		
		return root;
	}

	public Position<E> addLeft(E value, Position<E> pos) {
		Node node = validatePosition(pos);
		if(hasLeft(pos)) {
			throw new IllegalStateException("Left child already exists");
		}
		node.left = new Node(value, node);
		size++;
		return node.left;
	}

	public Position<E> addRight(E value, Position<E> pos) {
		Node node = validatePosition(pos);
		if(hasRight(pos)) {
			throw new IllegalStateException("Right child already exists");
		}
		node.right = new Node(value, node);
		size++;
		return node.right;
	}
	
	public int size() {
		return size;
	}

	public boolean isExternal(Position<E> pos) {
		Node node = validatePosition(pos);
		return node.left == null && node.right == null;
	}
	
	public boolean isInternal(Position<E> pos) {
		return !isExternal(pos);
	}

	public boolean isRoot(Position<E> pos) {
		Node node = validatePosition(pos);
		return node == root;
	}

	public boolean hasLeft(Position<E> pos) {
		Node node = validatePosition(pos);
		return node.left != null;
	}

	public boolean hasRight(Position<E> pos) {
		Node node = validatePosition(pos);
		return node.right != null;
	}

	public Position<E> getRoot() {
		return root;
	}

	public Position<E> getParent(Position<E> p) {
		Node n = validatePosition(p);
		return n.parent;
	}

	public Position<E> getLeft(Position<E> p) {
		Node n = validatePosition(p);
		return n.left;
	}

	public Position<E> getRight(Position<E> p) {
		Node n = validatePosition(p);
		return n.right;
	}
	
	private void snapshotPreorder(ArrayList<E> snapshot, Node node) {
		if(node != null) {
		snapshot.add(node.getValue());
		snapshotPreorder(snapshot, node.left);
		snapshotPreorder(snapshot, node.right);
		}
	}

	@Override
	public Iterator<E> iterator() {
		ArrayList<E> snapshot = new ArrayList<>();
		
		snapshotPreorder(snapshot, root);
		
		return snapshot.iterator();
	}

}
