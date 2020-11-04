import java.util.Iterator;

// TODO update BinarySearchTree's generic type to extend Comparable
public class BinarySearchTree<E extends Comparable<E>> implements Set<E> {
	private class Node {
		private E value;
		private Node left;
		private Node right;
		
		public Node(E v) {
			value = v;
			left = null;
			right = null;
		}
		
		public String toString() {
			return value.toString();
		}
	}
	
	private Node root;
	private int size;
	
	public BinarySearchTree() {
		root = null;
		size = 0;
	}

	/* The node returned by find() will be:
	 * - null if the tree is empty
	 * - the node where e already exists in the tree
	 * - the parent node of where e should be in the tree
	 */
	private Node find(Node node, E target) {
		// Case 1: tree is empty
		if(root == null) {
			return null;
		}
		// Case 2: successful search
		if(node.value.equals(target)) {
			return node;
		}
		// Case 3: target is in left subtree
		if(node.value.compareTo(target) > 0 && node.left != null) {
			return find(node.left, target);
		}
		// Case 4: target is in right subtree
		if(node.value.compareTo(target) < 0 && node.right != null) {
			return find(node.right, target);
		}
		// Case 5: unsuccessful search
		// (Technically this can also handle Case 2 (successful search)). 
		return node;
	}
	
	@Override
	public boolean add(E e) {
		Node node = find(root, e);
		
		// Case 1: tree is empty, place e at the root
		if(node == null) {
			root = new Node(e);
		}
		// Case 2: e already exists in the tree, do nothing
		else if(node.value.equals(e)) {
			return false;
		}
		// Case 3: e is right child of node returned by find()
		else if(node.value.compareTo(e) < 0) {
			node.right = new Node(e);
		}
		// Case 4: e is left child of node returned by find()
		else {
			node.left = new Node(e);
		}
		// If we didn't return false in case 2, then we've
		// added a new node to the tree. Increment size and 
		// return true.
		size++;
		return true;
	}

	private Node remove(Node node, E e) {
		// Cases when searching for node to remove
		// Case 1: tree is empty, cannot remove e
		if(node == null) {
			return null;
		}
		// Case 2: value to remove is in right subtree
		else if(node.value.compareTo(e) < 0) {
			node.right = remove(node.right, e);
		}
		// Case 3: value to remove is in left subtree
		else if(node.value.compareTo(e) > 0) {
			node.left = remove(node.left, e);
		}
		// Case 4: node is the node to remove
		else {
			size--;
			// Cases for removing node
			// Case 1: node is an external node
			if(node.left == null && node.right == null) {
				return null;
			}
			// Case 2: node's left subtree exists
			else if(node.left != null && node.right == null) {
				return node.left;
			}
			// Case 3: node's right subtree exists
			else if(node.right != null && node.left == null) {
				return node.right;
			}
			// Case 4: both left and right subtrees exist
			else {
				Node pred = node.left;
				while(pred.right != null) {
					pred = pred.right;
				}
				node.value = pred.value;
				node.left = remove(node.left, pred.value);
			}
		}
		return node;
	}
	
	@Override
	public boolean remove(E e) {
		int oldSize = size;
		root = remove(root, e);
		return oldSize > size;
	}

	@Override
	public boolean contains(E e) {
		Node node = find(root, e);
		return node != null && node.value.equals(e);
	}
	
	/*
	 * Perform pre-order traversal.
	 * Number of spaces added to StringBuilder before a node's value
	 * is proportional to the depth of the node.
	 */
	private void toString(StringBuilder sb, Node node, int depth) {
		if(node != null) {
			for(int i = 0; i < depth; ++i) {
				sb.append("  ");
			}
			sb.append(node.value.toString() + "\n");
			toString(sb, node.left, depth+1);
			toString(sb, node.right, depth+1);
		}
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		toString(sb, root, 0);
		return sb.toString();
	}
	
	// We will ignore these methods in order to focus
	// on the main operations BSTs are used for.
	@Override
	public Iterator<E> iterator() { return null; }

	@Override
	public void addAll(Set<E> other) {}

	@Override
	public void retainAll(Set<E> other) {}

	@Override
	public void removeAll(Set<E> other) {}

}
