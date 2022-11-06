package cp213;

/**
 * A single linked priority queue structure of <code>Node T</code> objects.
 * These data objects must be Comparable - i.e. they must provide the compareTo
 * method. Only the <code>T</code> data contained in the priority queue is
 * visible through the standard priority queue methods. Extends the
 * <code>SingleLink</code> class.
 *
 * @author your name, id, email here
 * @version 2022-10-26
 * @param <T> the SinglePriorityQueue data type.
 */
public class SinglePriorityQueue<T extends Comparable<T>> extends SingleLink<T> {

	/**
	 * Combines the contents of the left and right SinglePriorityQueues into the
	 * current SinglePriorityQueue. Moves nodes only - does not move value or call
	 * the high-level methods insert or remove. left and right SinglePriorityQueues
	 * are empty when done. Nodes are moved alternately from left and right to this
	 * SinglePriorityQueue. When finished all nodes must be in priority order from
	 * front to rear.
	 *
	 * Do not use the SinglePriorityQueue insert and remove methods.
	 *
	 * Do not assume that both right and left priority queues are of the same
	 * length.
	 *
	 * @param left  The first SinglePriorityQueue to extract nodes from.
	 * @param right The second SinglePriorityQueue to extract nodes from.
	 */
	public void combine(final SinglePriorityQueue<T> left, final SinglePriorityQueue<T> right) {
		assert this.front == null : "May combine into an empty Priority Queue only";
		if (!left.isEmpty()) {
			this.front = this.rear = left.front;
			left.front = left.front.getNext();
			this.rear.setNext(null);
			this.length += 1;
			left.length -= 1;
		} else {
			this.front = right.front;
			this.rear = right.rear;
			this.length = right.length;
			right.front = right.rear = null;
			right.length = 0;
		}
		while (left.length > 0 || right.length > 0) {
			if (right.length > 0) {
				T rightRemove = right.front.getDatum();
				right.front = right.front.getNext();
				right.length -= 1;
				SingleNode<T> newNode = new SingleNode<T>(rightRemove, null);
				if (this.length == 0) {
					this.front = newNode;
					this.rear = this.front;
				} else {
					SingleNode<T> current = this.front;
					int lengthCount = 1;
					if (this.front.getDatum().compareTo(rightRemove) > 0) {
						newNode.setNext(this.front);
						this.front = newNode;
					} else {
						while (lengthCount < this.length && current.getNext().getDatum().compareTo(rightRemove) <= 0) {
							current = current.getNext();
							lengthCount += 1;
						}
						SingleNode<T> newNext = current.getNext();
						current.setNext(newNode);
						current = current.getNext();
						current.setNext(newNext);
					}
				}
				this.length += 1;
			}
			if (left.length > 0) {
				T leftRemove = left.front.getDatum();
				left.front = left.front.getNext();
				left.length -= 1;
				SingleNode<T> newNode = new SingleNode<T>(leftRemove, null);
				if (this.length == 0) {
					this.front = newNode;
					this.rear = this.front;
				} else {
					SingleNode<T> current = this.front;
					int lengthCount = 1;
					if (this.front.getDatum().compareTo(leftRemove) > 0) {
						newNode.setNext(this.front);
						this.front = newNode;
					} else {
						while (lengthCount < this.length && current.getNext().getDatum().compareTo(leftRemove) <= 0) {
							current = current.getNext();
							lengthCount += 1;
						}
						SingleNode<T> newNext = current.getNext();
						current.setNext(newNode);
						current = current.getNext();
						current.setNext(newNext);
					}
				}
				this.length += 1;
			}
		}
	}
	
	/**
	 * Removes duplicates from this SinglePriorityQueue. The Queue contains one 
	 * and only one of each value formerly present in this SinglePriorityQueue. 
	 * The first occurrence of each value is preserved.
	 */
	public void clean() {
		SingleNode<T> current = this.front;
		SingleNode<T> previous = current;
		current = current.getNext();
		T previousData = previous.getDatum();
		T currentData = current.getDatum();
		while (current != null) {
			if (!previousData.equals(currentData)) {
				previous = current; // makes previous into current
				current = current.getNext(); // makes current into current.getNext()
				previousData = previous.getDatum();
				currentData = current.getDatum();
			} else {
				current = current.getNext(); // makes current into current.getNext()
				previous.setNext(current); // makes previous.next() into the new current
				this.length -= 1; // decrements length count
				currentData = current.getDatum();
			}
		}
		return;
	}

	/**
	 * Adds value to the SinglePriorityQueue. Data is stored in priority order, with
	 * highest priority value at the front of the SinglePriorityQueue, and lowest at
	 * the rear. Priority is determined by simple comparison - lower values have
	 * higher priority. For example, 1 has a higher priority than 2 because 1 is a
	 * lower value than 2. (Think of the phrase, "We're number one!" as an
	 * indication of priority.)
	 *
	 * When inserting value to the priority queue, the queue must remain sorted.
	 * Hence you need to find the proper location of inserting value. use the head
	 * pointer to go through the queue. e.g., use SingleNode current =
	 * this.front;
	 *
	 * use current = current.getNext(); to traverse the queue.
	 *
	 * To get access to the value inside a node of queue use current.getValue().
	 *
	 * @param datum value to insert in sorted order in priority queue.
	 */
	public void insert(final T datum) {
		SingleNode<T> newNode = new SingleNode<T>(datum, null);
		if (this.length == 0) {
			this.front = newNode;
			this.rear = this.front;
		} else {
			SingleNode<T> current = this.front;
			int lengthCount = 1;
			if (this.front.getDatum().compareTo(datum) > 0) {
				newNode.setNext(this.front);
				this.front = newNode;
			} else {
				while (lengthCount < this.length && current.getNext().getDatum().compareTo(datum) <= 0) {
					current = current.getNext();
					lengthCount += 1;
				}
				SingleNode<T> newNext = current.getNext();
				current.setNext(newNode);
				current = current.getNext();
				current.setNext(newNext);
			}
		}
		this.length += 1;
		return;
	}

	/**
	 * Returns the highest priority value in the SinglePriorityQueue. Decrements the
	 * count.
	 *
	 * @return the highest priority value currently in the SinglePriorityQueue.
	 */
	public T remove() {
		T result = this.front.getDatum();
		this.front = this.front.getNext();
		this.length -= 1;

		return result;
	}

	/**
	 * Splits the contents of this SinglePriorityQueue into the higher and lower
	 * SinglePriorityQueue. Moves nodes only - does not move value or call the
	 * high-level methods insert or remove. this SinglePriorityQueue is empty when
	 * done. Nodes with priority value higher than key are moved to the
	 * SinglePriorityQueue higher. Nodes with a priority value lower than or equal
	 * to key are moved to the SinglePriorityQueue lower.
	 *
	 * Do not use the SinglePriorityQueue insert and remove methods.
	 *
	 * @param key    value to compare against node values in SinglePriorityQueue
	 * @param higher an initially empty SinglePriorityQueue queue that ends up with
	 *               all values with priority higher than key.
	 * @param lower  an initially empty SinglePriorityQueue queue that ends up with
	 *               all values with priority lower than or equal to key.
	 */
	public void splitByKey(final T key, final SinglePriorityQueue<T> higher, final SinglePriorityQueue<T> lower) {
		int initialLen = this.getLength();
		if (initialLen > 0) {
			SingleNode<T> current = this.front;
			SingleNode<T> previous = current;
			while (higher.length < initialLen && current.getDatum().compareTo(key) < 0) {
				higher.length += 1;
				previous = current;
				current = current.getNext();
			}
			higher.front = this.front;
			higher.rear = previous;
			higher.rear.setNext(null);
			if (!previous.equals(this.rear)) {
				lower.front = current;
				lower.rear = this.rear;
				lower.length = initialLen - higher.getLength();
			}
			this.front = this.rear = null;
			this.length = 0;
		}
		return;
	}
}
