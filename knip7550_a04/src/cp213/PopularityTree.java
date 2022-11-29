package cp213;

/**
 * Implements a Popularity Tree. Extends BST.
 *
 * @author Kelli Knipe
 * @author David Brown
 * @version 2022-11-19
 */
public class PopularityTree<T extends Comparable<T>> extends BST<T> {

	/**
	 * Auxiliary method for valid. May force node rotation if the retrieval count of
	 * the located node item is incremented.
	 *
	 * @param node The node to examine for key.
	 * @param key  The item to search for. Count is updated to count in matching
	 *             node item if key is found.
	 * @return The updated node.
	 */
	private TreeNode<T> retrieveAux(TreeNode<T> node, final CountedStore<T> key) {
		if (node != null) {
			final int result = node.getCs().compareTo(key);
			this.comparisons++;
			if (result > 0) {
				node.setLeft(this.retrieveAux(node.getLeft(), key));
				if (node.getLeft() != null && node.getCs().getCount() < node.getLeft().getCs().getCount()) {
					node = this.rotateRight(node);
				}
			} else if (result < 0) {
				node.setRight(this.retrieveAux(node.getRight(), key));
				if (node.getRight() != null && node.getCs().getCount() < node.getRight().getCs().getCount()) {
					node = this.rotateLeft(node);
				}
			} else {
				node.getCs().incrementCount();
				key.setCount(node.getCs().getCount());
			}
		}
		node.updateHeight();
		return node;
	}

	/**
	 * Performs a left rotation around node.
	 *
	 * @param parent The subtree to rotate.
	 * @return The new root of the subtree.
	 */
	private TreeNode<T> rotateLeft(final TreeNode<T> parent) {
		final TreeNode<T> newNode = parent.getRight();
		parent.setRight(newNode.getLeft());
		newNode.setLeft(parent);
		parent.updateHeight();
		newNode.updateHeight();
		return newNode;
	}

	/**
	 * Performs a right rotation around {@code node}.
	 *
	 * @param parent The subtree to rotate.
	 * @return The new root of the subtree.
	 */
	private TreeNode<T> rotateRight(final TreeNode<T> parent) {
		final TreeNode<T> newNode = parent.getLeft();
		parent.setLeft(newNode.getRight());
		newNode.setRight(parent);
		parent.updateHeight();
		newNode.updateHeight();
		return newNode;
	}

	/**
	 * Replaces BST insertAux - does not increment count on repeated insertion.
	 * Counts are incremented only on retrieve.
	 */
	@Override
	protected TreeNode<T> insertAux(TreeNode<T> node, final CountedStore<T> data) {
		if (node == null) {
			// Base case - add a new node containing the data.
			node = new TreeNode<T>(data);
			this.size++;
		} else {
			// Compare the node data against the insert data.
			final int result = node.getCs().compareTo(data);

			if (result > 0) {
				// General case - check the left subtree.
				node.setLeft(this.insertAux(node.getLeft(), data));
			} else if (result < 0) {
				// General case - check the right subtree.
				node.setRight(this.insertAux(node.getRight(), data));
			}
		}
		node.updateHeight();
		return node;
	}

	/**
	 * Auxiliary method for valid. Determines if a subtree based on node is a valid
	 * subtree. An Popularity Tree must meet the BST validation conditions, and
	 * additionally the counts of any node data must be greater than or equal to the
	 * counts of its children.
	 *
	 * @param node The root of the subtree to test for validity.
	 * @return true if the subtree base on node is valid, false otherwise.
	 */
	@Override
	protected boolean isValidAux(final TreeNode<T> node, TreeNode<T> minNode, TreeNode<T> maxNode) {
		boolean result = false;
		if (node == null) {
			result = true;
		} else if (Math.max(this.nodeHeight(node.getLeft()), this.nodeHeight(node.getRight())) != node.getHeight() - 1) {
			result = false;
		} else if (node.getLeft() != null && node.getLeft().getCs().compareTo(node.getCs()) >= 0
				|| node.getRight() != null && node.getRight().getCs().compareTo(node.getCs()) <= 0) {
			result = false;
		} else if (node.getLeft() != null && node.getCs().getCount() < node.getLeft().getCs().getCount()
				|| node.getRight() != null && node.getCs().getCount() < node.getRight().getCs().getCount()) {
		} else {
			result = this.isValidAux(node.getLeft(), minNode, maxNode) && this.isValidAux(node.getRight(), minNode, maxNode);
		}
		return result;
	}

	/**
	 * Determines whether two PopularityTrees are identical.
	 *
	 * @param target The PopularityTree to compare this PopularityTree against.
	 * @return true if this PopularityTree and target contain nodes that match in
	 *         position, item, count, and height, false otherwise.
	 */
	public boolean equals(final PopularityTree<T> target) {
		return super.equals(target);
	}

	/**
	 * Very similar to the BST retrieve, but increments the data count here instead
	 * of in the insertion.
	 *
	 * @param key The key to search for.
	 */
	@Override
	public CountedStore<T> retrieve(CountedStore<T> key) {
		this.root = this.retrieveAux(this.root, key);
		if (key.getCount() == 0) {
			key = null;
		} else {
			key = new CountedStore<T>(key);
		}
		return key;
	}

}
