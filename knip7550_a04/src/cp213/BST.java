package cp213;

import java.util.ArrayList;

/**
 * Implements a Binary Search Tree.
 *
 * @author Kelli Knipe
 * @author David Brown
 * @version 2022-11-19
 */
public class BST<T extends Comparable<T>> {

	// Attributes.
	/**
	 * Count of comparisons performed by tree.
	 */
	protected int comparisons = 0;
	/**
	 * Root node of the tree.
	 */
	protected TreeNode<T> root = null;
	/**
	 * Number of nodes in the tree.
	 */
	protected int size = 0;

	/**
	 * Auxiliary method for {@code equals}. Determines whether two subtrees are
	 * identical in items and height.
	 *
	 * @param source Node of this BST.
	 * @param target Node of that BST.
	 * @return true if source and target are identical in items and height.
	 */
	protected boolean equalsAux(final TreeNode<T> source, final TreeNode<T> target) {
		// your code here
		boolean result = (source.getCs().compareTo(target.getCs()) == 0);
		if (source.getLeft() != null && target.getLeft() != null && result) {
			result = this.equalsAux(source.getLeft(), target.getLeft());
		} else if (source.getLeft() == null && target.getLeft() == null && result) {
			result = true;
		} else {
			result = false;
		}
		if (source.getRight() != null && target.getRight() != null && result) {
			result = this.equalsAux(source.getRight(), target.getRight());
		} else if (source.getRight() == null && target.getRight() == null && result) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	/**
	 * Auxiliary method for insert. Inserts data into this BST.
	 *
	 * @param node The current node (TreeNode).
	 * @param cs   Data to be inserted into the tree.
	 * @return The inserted node.
	 */
	protected TreeNode<T> insertAux(TreeNode<T> node, final CountedStore<T> cs) {

		if (node == null) {
			// Base case - add a new node containing the data.
			node = new TreeNode<T>(cs);
			node.getCs().incrementCount();
			this.size++;
		} else {
			// Compare the node data against the insert data.
			final int result = node.getCs().compareTo(cs);

			if (result > 0) {
				// General case - check the left subtree.
				node.setLeft(this.insertAux(node.getLeft(), cs));
			} else if (result < 0) {
				// General case - check the right subtree.
				node.setRight(this.insertAux(node.getRight(), cs));
			} else {
				// Base case - data is already in the tree, increment its count
				node.getCs().incrementCount();
			}
		}
		node.updateHeight();
		return node;
	}

	/**
	 * Auxiliary method for valid. Determines if a subtree based on node is a valid
	 * subtree.
	 *
	 * @param node    The root of the subtree to test for validity.
	 * @param minNode The node of the minimum value in the current subtree.
	 * @param maxNode The node of the maximum value in the current subtree.
	 * @return true if the subtree base on node is valid, false otherwise.
	 */
	protected boolean isValidAux(final TreeNode<T> node, TreeNode<T> minNode, TreeNode<T> maxNode) {
		// your code here
		boolean result = true;
		boolean parentHeight = this.parentHeightCheck(node);
		if (node != null) {
			if (node.getLeft() != null && node.getCs().compareTo(node.getLeft().getCs()) > 0) {
				result = isValidAux(node.getLeft(), minNode, maxNode);
			} else if (node.getLeft() != null) {
				result = false;
			}
			if (node.getRight() != null && node.getCs().compareTo(node.getRight().getCs()) < 0 && result) {
				result = isValidAux(node.getRight(), minNode, maxNode);
			} else if (node.getLeft() != null) {
				result = false;
			}
		} else {
			result = false;
		}
		if (!parentHeight) {
			result = false;
		} else if (minNode != null && node.getCs().compareTo(minNode.getCs()) < 0) {
			result = false;
		} else if (maxNode != null && node.getCs().compareTo(maxNode.getCs()) > 0) {
			result = false;
		}
		return result;
	}

	/**
	 * Auxiliary method for isValidAux. Determines if a node's height is correct in
	 * relation to the height of its children.
	 *
	 * @param node    The root of the subtree to test for validity.
	 * @return true if the subtree height is correct, false otherwise
	 */
	protected boolean parentHeightCheck(final TreeNode<T> node) {
		boolean result = false;
		if (node.getLeft() != null && node.getHeight() == (node.getLeft().getHeight() + 1)) {
			result = true;
		}
		if (node.getRight() != null && node.getHeight() == (node.getRight().getHeight() + 1)) {
			result = true;
		}
		return result;
	}

	/**
	 * Returns the height of a given TreeNode.
	 *
	 * @param node The TreeNode to determine the height of.
	 * @return The height attribute of node, 0 if node is null.
	 */
	protected int nodeHeight(final TreeNode<T> node) {
		int height = 0;

		if (node != null) {
			height = node.getHeight();
		}
		return height;
	}
	
	/**
	 * Updates the height of each TreeNode in subtree node
	 *
	 * @param node The TreeNode that is the root of the subtree to be updated.
	 */
	protected void treeHeightUpdate(TreeNode<T> node) {
		if (node != null) {
			TreeNode<T> nodeLeft = node.getLeft();
			TreeNode<T> nodeRight = node.getRight();
			if (nodeLeft != null) {
				this.treeHeightUpdate(nodeLeft);
			}
			if (nodeRight != null) {
				this.treeHeightUpdate(nodeRight);
			}
			node.updateHeight();
		}
		return;
	}

	/**
	 * Determines if this BST contains key.
	 *
	 * @param key The key to search for.
	 * @return true if this contains key, false otherwise.
	 */
	public boolean contains(final CountedStore<T> key) {
		// if you're getting output errors in this function switch the comparison
		// operators in the else if statements
		boolean result = false;
		TreeNode<T> current = this.root;
		while (current != null && !result) {
			int comparison = current.getCs().compareTo(key);
			if (comparison == 0) {
				result = true;
			} else if (comparison < 0) {
				current = current.getRight();
			} else if (comparison > 0) {
				current = current.getLeft();
			}
		}
		return result;
	}

	/**
	 * Determines whether two trees are identical.
	 *
	 * @param target The tree to compare this BST against.
	 * @return true if this and target contain nodes that match in position, item,
	 *         count, and height, false otherwise.
	 */
	public boolean equals(final BST<T> target) {
		boolean isEqual = false;

		if (this.size == target.size) {
			isEqual = this.equalsAux(this.root, target.root);
		}
		return isEqual;
	}

	/**
	 * Get number of comparisons executed by the retrieve method.
	 *
	 * @return comparisons
	 */
	public int getComparisons() {
		return this.comparisons;
	}

	/**
	 * Returns the height of the root node of this tree.
	 *
	 * @return height of root node, 0 if the root node is null.
	 */
	public int getHeight() {
		int height = 0;

		if (this.root != null) {
			height = this.root.getHeight();
		}
		return height;
	}

	/**
	 * Returns the number of nodes in the tree.
	 *
	 * @return number of nodes in this tree.
	 */
	public int getSize() {
		return this.size;
	}

	/**
	 * Returns a list of the data in the current tree. The list contents are in
	 * order from smallest to largest.
	 *
	 * Not thread safe as it assumes contents of the tree are not changed by an
	 * external thread during the loop.
	 *
	 * @return The contents of this tree as a list of data.
	 */
	public ArrayList<CountedStore<T>> inOrder() {
		return this.root.inOrder();
	}

	/**
	 * Inserts data into this tree.
	 *
	 * @param cs Data to store.
	 */
	public void insert(final CountedStore<T> cs) {
		this.root = this.insertAux(this.root, cs);
		return;
	}

	/**
	 * Determines if this tree is empty.
	 *
	 * @return true if this tree is empty, false otherwise.
	 */
	public boolean isEmpty() {
		boolean result = true;
		if (size != 0 || this.root != null) {
			result = false;
		}
		return result;
	}

	/**
	 * Determines if this tree is a valid BST; i.e. a node's left child data is
	 * smaller than its data, and its right child data is greater than its data, and
	 * a node's height is equal to the maximum of the heights of its two children
	 * (empty child nodes have a height of 0), plus 1.
	 *
	 * @return true if this tree is a valid BST, false otherwise.
	 */
	public boolean isValid() {
		return this.isValidAux(this.root, null, null);
	}

	/**
	 * Returns a list of the data in the current tree. The list contents are in node
	 * level order starting from the root node. Helps determine the structure of the
	 * tree.
	 *
	 * Not thread safe as it assumes contents of the tree are not changed by an
	 * external thread during the loop.
	 *
	 * @return this tree data as a list of data.
	 */
	public ArrayList<CountedStore<T>> levelOrder() {
		return this.root.levelOrder();
	}

	/**
	 * Returns a list of the data in the current tree. The list contents are in node
	 * preorder.
	 *
	 * Not thread safe as it assumes contents of the tree are not changed by an
	 * external thread during the loop.
	 *
	 * @return The contents of this tree as a list of data.
	 */
	public ArrayList<CountedStore<T>> preOrder() {
		return this.root.preOrder();
	}

	/**
	 * Resets the comparison count to 0.
	 */
	public void resetComparisons() {
		this.comparisons = 0;
		return;
	}

	/**
	 * Retrieves a copy of data matching key (key should have item count of 0).
	 * Returning a complete CountedStore gives access to the data and its count.
	 *
	 * @param key The key to look for.
	 * @return data The complete CountedStore that matches key, null otherwise.
	 */
	public CountedStore<T> retrieve(final CountedStore<T> key) {
		// your code here
		CountedStore<T> result = null;
		TreeNode<T> current = this.root;
		while (current != null && result == null) {
			int comparison = current.getCs().compareTo(key);
			if (comparison == 0) {
				result = current.getCs();
			} else if (comparison < 0) {
				current = current.getRight();
			} else if (comparison > 0) {
				current = current.getLeft();
			}
		}
		return result;
	}
}
