package cp213;

/**
 * Implements an AVL (Adelson-Velsky Landis) tree. Extends BST.
 *
 * @author your name here
 * @author David Brown
 * @version 2022-05-12
 */
public class AVL<T extends Comparable<T>> extends BST<T> {

	/**
	 * Returns the balance item of node. If greater than 1, then left heavy, if less
	 * than -1, then right heavy. If in the range -1 to 1 inclusive, the node is
	 * balanced. Used to determine whether to rotate a node upon insertion.
	 *
	 * @param node The TreeNode to analyze for balance.
	 * @return A balance number.
	 */
	private int balance(final TreeNode<T> node) {
		int leftSide = 0;
		int rightSide = 0;
		TreeNode<T> leftNode = node.getLeft();
		TreeNode<T> rightNode = node.getRight();
		if (leftNode != null) {
			leftSide = leftNode.getHeight();
		}
		if (rightNode != null) {
			rightSide = rightNode.getHeight();
		}
		return (leftSide - rightSide);
	}

	/**
	 * Performs a left rotation around node.
	 *
	 * @param node The subtree to rotate.
	 * @return The new root of the subtree.
	 */
	private TreeNode<T> rotateLeft(final TreeNode<T> node) {
		TreeNode<T> newRoot = node.getRight();
		TreeNode<T> oldLeft = null;
		if (newRoot != null) {
			oldLeft = newRoot.getLeft();
		}
		newRoot.setLeft(node);
		node.setRight(oldLeft);
		super.treeHeightUpdate(newRoot);
		return newRoot;
	}

	/**
	 * Performs a right rotation around node.
	 *
	 * @param node The subtree to rotate.
	 * @return The new root of the subtree.
	 */
	private TreeNode<T> rotateRight(final TreeNode<T> node) {
		TreeNode<T> newRoot = node.getLeft();
		TreeNode<T> oldRight = null;
		if (newRoot != null) {
			oldRight = newRoot.getRight();
		}
		newRoot.setRight(node);
		node.setLeft(oldRight);
		super.treeHeightUpdate(newRoot);
		return newRoot;
	}

	/**
	 * Auxiliary method for insert. Inserts data into this AVL.
	 *
	 * @param node The current node (TreeNode).
	 * @param cs   Data to be inserted into the node.
	 * @return The inserted node.
	 */
	@Override
	protected TreeNode<T> insertAux(TreeNode<T> node, final CountedStore<T> cs) {
		TreeNode<T> newNode = new TreeNode<T>(cs);
		TreeNode<T> leftNode = node.getLeft();
		TreeNode<T> rightNode = node.getRight();
		if (node.getCs().compareTo(cs) == 0) {
			node.getCs().incrementCount();
		} else if (node.getCs().compareTo(cs) > 0) {
			if (leftNode != null) {
				this.insertAux(leftNode, cs);
			} else {
				node.setLeft(newNode);
				super.treeHeightUpdate(node);
			}
		} else if (node.getCs().compareTo(cs) < 0) {
			if (rightNode != null) {
				this.insertAux(rightNode, cs);
			} else {
				node.setRight(newNode);
				super.treeHeightUpdate(node);
			}
		}
		return newNode;
	}
	
	protected void rebalance(TreeNode<T> node) {
		
	}

	/**
	 * Auxiliary method for valid. Determines if a subtree based on node is a valid
	 * subtree. An AVL must meet the BST validation conditions, and additionally be
	 * balanced in all its subtrees - i.e. the difference in height between any two
	 * children must be no greater than 1.
	 *
	 * @param node The root of the subtree to test for validity.
	 * @return true if the subtree base on node is valid, false otherwise.
	 */
	@Override
	protected boolean isValidAux(final TreeNode<T> node, TreeNode<T> minNode, TreeNode<T> maxNode) {
		boolean result = true;
		TreeNode<T> leftNode = node.getLeft();
		TreeNode<T> rightNode = node.getRight();
		if (leftNode != null || rightNode != null) {
			if (leftNode != null) {
				result = this.isValidAux(leftNode, minNode, maxNode);
			}
			if (rightNode != null && result) {
				result = this.isValidAux(rightNode, minNode, maxNode);
			}
		}
		if (minNode != null && node.getCs().compareTo(minNode.getCs()) < 0) {
			result = false;
		}
		if (maxNode != null && node.getCs().compareTo(maxNode.getCs()) > 0) {
			result = false;
		}
		if (this.balance(node) > 1 || this.balance(node) < (-1)) {
			result = false;
		}
		return false;
	}

	/**
	 * Determines whether two AVLs are identical.
	 *
	 * @param target The AVL to compare this AVL against.
	 * @return true if this AVL and target contain nodes that match in position,
	 *         item, count, and height, false otherwise.
	 */
	public boolean equals(final AVL<T> target) {
		return super.equals(target);
	}

}
