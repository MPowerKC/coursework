


public class BinarySearchTree<E extends Comparable<E>> {
	class Node {
		E value;
		Node leftChild = null;
		Node rightChild = null;
		Node(E value) {
			this.value = value;
		}
		@Override
		public boolean equals(Object obj) {
			if ((obj instanceof BinarySearchTree.Node) == false)
				return false;
			@SuppressWarnings("unchecked")
			Node other = (BinarySearchTree<E>.Node)obj;
			return other.value.compareTo(value) == 0 &&
					other.leftChild == leftChild && other.rightChild == rightChild;
		}
	}
	
	protected Node root = null;
	
	protected void visit(Node n) {
		System.out.println(n.value);
	}
	
	public boolean contains(E val) {
		return contains(root, val);
	}
	
	protected boolean contains(Node n, E val) {
		if (n == null) return false;
		
		if (n.value.equals(val)) {
			return true;
		} else if (n.value.compareTo(val) > 0) {
			return contains(n.leftChild, val);
		} else {
			return contains(n.rightChild, val);
		}
	}
	
	public boolean add(E val) {
		if (root == null) {
			root = new Node(val);
			return true;
		}
		return add(root, val);
	}
	
	protected boolean add(Node n, E val) {
		if (n == null) {
			return false;
		}
		int cmp = val.compareTo(n.value);
		if (cmp == 0) {
			return false; // this ensures that the same value does not appear more than once
		} else if (cmp < 0) {
			if (n.leftChild == null) {
				n.leftChild = new Node(val);
				return true;
			} else {
				return add(n.leftChild, val);
			} 	
		} else {
			if (n.rightChild == null) {
				n.rightChild = new Node(val);
				return true;
			} else {
				return add(n.rightChild, val);
			} 	
		}
	}	
	
	public boolean remove(E val) {
		return remove(root, null, val);
	}
	
	protected boolean remove(Node n, Node parent, E val) {
		if (n == null) return false;

		if (val.compareTo(n.value) == -1) {
				return remove(n.leftChild, n, val);
		} else if (val.compareTo(n.value) == 1) {
				return remove(n.rightChild, n, val);
		} else {
			if (n.leftChild != null && n.rightChild != null){
				n.value = maxValue(n.leftChild);
				remove(n.leftChild, n, n.value);
			} else if (parent == null) {
				root = n.leftChild != null ? n.leftChild : n.rightChild;
			} else if (parent.leftChild == n){
				parent.leftChild = n.leftChild != null ? n.leftChild : n.rightChild;
			} else {
				parent.rightChild = n.leftChild != null ? n.leftChild : n.rightChild;
			}
			return true;
		}
	}

	protected E maxValue(Node n) {
		if (n.rightChild == null) {
			return n.value;
	    } else {
	       return maxValue(n.rightChild);
	    }
	}

	
	/*********************************************
	 * 
	 * IMPLEMENT THE METHODS BELOW!
	 *
	 *********************************************/
	
	
	// Method #1.
	public Node findNode(E val) {
		return findNode(root, val);
	}
	
	protected Node findNode(Node node, E val) {
		if (node == null || val == null) 
			return null;
		else if (node.value.equals(val)) 
			return node;
		else if (node.value.compareTo(val) > 0) 
			return findNode(node.leftChild, val);
		else {
			return findNode(node.rightChild, val);			
		}		
	}
	
	// Method #2.
	protected int depth(E val) {
		return depth(root, val, 0);
	}
	
	protected int depth(Node node, E val, int currentDepth) {
		if (node == null || val == null) return -1;
		else if (node.value.equals(val))
			return currentDepth;
		else if (node.value.compareTo(val) > 0)
			return depth(node.leftChild, val, ++currentDepth);
		else
			return depth(node.rightChild, val, ++currentDepth);
	}
	
	// Method #3.
	protected int height(E val) {
		return height(findNode(val), -1);
	}
	
	protected int height(Node node, int relativeHeight) {
		if (node == null) return relativeHeight;
		int leftHeight = height(node.leftChild, relativeHeight+1);
		int rightHeight = height(node.rightChild, relativeHeight+1);
		return Math.max(leftHeight, rightHeight);
	}


	// Method #4.
	protected boolean isBalanced(Node n) {
		if (n == null) return false;
		
		Node node = findNode(n.value);
		if (node == null) return false;
		
		int heightDifference = Math.abs(height(node.leftChild, -1) - height(node.rightChild, -1));
		return heightDifference == 0 || heightDifference == 1;
	}
	
	// Method #5. .
	public boolean isBalanced() {
		return isBalancedTree(root);
	}
	
	protected boolean isBalancedTree(Node node) {
		if (node == null) return true;
		if (!isBalanced(node)) return false;
		return isBalancedTree(node.leftChild) &&
			   isBalancedTree(node.rightChild);
	}

}
