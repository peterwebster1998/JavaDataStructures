import java.util.Iterator;

public class AVLTree<E extends Comparable<E>> implements Set<E> {

	// TODO Augment Node class to have a height instance variable
	private class Node {
		private E value;
		private Node left;
		private Node right;
		
		private int height;

		public Node(E v) {
			value = v;
			left = null;
			right = null;
			
			height = 0;
		}

		public String toString() {
			return value.toString();
		}
	}

	private Node root;
	private int size;

	public AVLTree() {
		root = null;
		size = 0;
	}

	/*
	 * The node returned by find() will be: - null if the tree is empty - the node
	 * where e already exists in the tree - the parent node of where e should be in
	 * the tree
	 */
	private Node find(Node node, E target) {
		// Case 1: tree is empty
		if (node == null) {
			return null;
		}
		// Case 2: successful search
		if (node.value.equals(target)) {
			return node;
		}
		// Case 3: target is in left subtree
		if (node.value.compareTo(target) > 0 && node.left != null) {
			return find(node.left, target);
		}
		// Case 4: target is in right subtree
		if (node.value.compareTo(target) < 0 && node.right != null) {
			return find(node.right, target);
		}
		// Case 5: unsuccessful search
		// Technically this can also handle Case 2 (successful search).
		return node;
	}

	/*
	 * return the new root of the tree after left rotation 
	 *            x              y 
	 *           / \            / \ 
	 *          T1  y   =>     x  T3
	 *             / \        / \  
	 *            T2 T3      T1 T2
	 */
	private Node leftRotate(Node x) {
		Node y = x.right;
		Node t2 = y.left;
		
		y.left = x;
		x.right = t2;
		
		// Be sure to recalculate heights
		recalculateHeight(x);
		recalculateHeight(y);
		return y;
	}

	/*
	 * return the new root of the tree after left rotation 
	 *             x              y 
	 *            / \            / \ 
	 *           T1  y     <=   x  T3
	 *              / \        / \   
	 *             T2 T3      T1 T2
	 */
	private Node rightRotate(Node y) {
		Node x = y.left;
		Node t2 = x.right;
		
		y.left = t2;
		x.right = y;
		
		// Be sure to recalculate heights
		recalculateHeight(y);
		recalculateHeight(x);
		return x;
	}

	/*
	 * Rebalance the tree rooted at z if the height of the left
	 * subtree - height of the right subtree is greater than 1.
	 * Returns the new root of the subtree after rebalancing.
	 */
	private Node rebalanceLeft(Node z) {
		if(heightOfNode(z.left) - heightOfNode(z.right) > 1) {
			Node y = z.left;
			//Left-Left
			if(heightOfNode(y.left) >= heightOfNode(y.right)) {
				z = rightRotate(z);
			}
			//Left-Right
			else {
				z.left = leftRotate(y);
				z = rightRotate(z);
			}
		}
		return z;
	}
	
	/*
	 * Rebalance the tree rooted at z if the height of the right
	 * subtree - height of the left subtree is greater than 1.
	 * Returns the new root of the subtree after rebalancing.
	 */
	private Node rebalanceRight(Node z) {
		if(heightOfNode(z.right) - heightOfNode(z.left) > 1) {
			Node y = z.right;
			//Right-Right
			if(heightOfNode(y.right) >= heightOfNode(y.left)) {
				z = leftRotate(z);
			}
			//Right-Left
			else {
				z.right = rightRotate(y);
				z = leftRotate(z);
			}
		}
		return z;
	}

	/*
	 * heightOfNode returns the height of the given node if
	 * the node is not null. If the node is null, return -1.
	 */
	private int heightOfNode(Node n) {
//		if(n == null) {
//			return -1;
//		}
//		return n.height;
		return n == null ? -1 : n.height;
	}

	/*
	 * Determines the height of the given node by adding 1 to
	 * the larger of the heights of the two child nodes.
	 */
	private void recalculateHeight(Node n) {
		n.height = Math.max(heightOfNode(n.left), heightOfNode(n.right)) + 1;
	}

	private Node add(Node node, E e) {
		// Case 1: subtree at node is empty
		if(node == null) {
			size++;
			node = new Node(e);
			return node;
		}
		// Case 2: e already exists in the tree
		if(node.value.equals(e)) {
			return node;
		}
		// Case 3: e should be added in left subtree
		else if(node.value.compareTo(e) > 0) {
			node.left = add(node.left, e);
			recalculateHeight(node);
			node = rebalanceLeft(node);
		}
		// Case 4: e should be added in right subtree
		else {
			node.right = add(node.right, e);
			recalculateHeight(node);
			node = rebalanceRight(node);
		}
		return node;
	}
	
	@Override
	public boolean add(E e) {
		int oldSize = size;
		root = add(root, e);
		return oldSize < size;
	}

	private Node remove(Node node, E e) {
		// Cases when searching for node to remove
		// Case 1: subtree is at node i empty, cannot remove e
		if (node == null) {
			return null;
		}
		// Case 2: value to remove is in right subtree
		else if (node.value.compareTo(e) < 0) {
			node.right = remove(node.right, e);
			// Recalculate height of this node
			recalculateHeight(node);
			// Rebalance (left or right?)
			node = rebalanceLeft(node);
		}
		// Case 3: value to remove is in left subtree
		else if (node.value.compareTo(e) > 0) {
			node.left = remove(node.left, e);
			// Recalculate height of this node
			recalculateHeight(node);
			// Rebalance (left or right?)
			node = rebalanceRight(node);
		}
		// Case 4: node is the node to remove
		else {
			size--;
			// Cases for removing node
			// Case 1: node is an external node
			if (node.right == null && node.left == null) {
				return null;
			}
			// Case 2: node's left subtree exists
			if (node.right == null) {
				return node.left;
			}
			// Case 3: node's right subtree exists
			if (node.left == null) {
				return node.right;
			}
			// Case 4: both left and right subtrees exist
			Node pred = node.left;
			while (pred.right != null) {
				pred = pred.right;
			}
			node.value = pred.value;
			node.left = remove(node.left, pred.value);
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
	 * Perform pre-order traversal. Number of tabs added to StringBuilder before a
	 * node's value is proportional to the depth of the node.
	 */
	private void toStringHelper(StringBuilder sb, Node node, int depth) {
		if (node != null) {
			for (int i = 0; i < depth; i++) {
				sb.append("  ");
			}
			sb.append(node.value + "\n"); // preorder
			toStringHelper(sb, node.left, depth + 1);
			toStringHelper(sb, node.right, depth + 1);
		}
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		toStringHelper(sb, root, 0);
		return sb.toString();
	}

	// We will ignore these methods in order to focus
	// on the main operations AVL trees are used for.
	@Override
	public Iterator<E> iterator() {
		return null;
	}

	@Override
	public void addAll(Set<E> other) {
	}

	@Override
	public void retainAll(Set<E> other) {
	}

	@Override
	public void removeAll(Set<E> other) {
	}

}
