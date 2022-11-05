package cp213;

/**
 * A single linked list structure of <code>Node T</code> objects. These data
 * objects must be Comparable - i.e. they must provide the compareTo method.
 * Only the <code>T</code> value contained in the priority queue is visible
 * through the standard priority queue methods. Extends the
 * <code>SingleLink</code> class.
 *
 * @author your name, id, email here
 * @version 2022-10-26
 * @param <T> this SingleList data type.
 */
public class SingleList<T extends Comparable<T>> extends SingleLink<T> {

	/**
	 * Searches for the first occurrence of key in this SingleList. Private helper
	 * methods - used only by other ADT methods.
	 *
	 * @param key The value to look for.
	 * @return A pointer to the node previous to the node containing key.
	 */
	private SingleNode<T> linearSearch(final T key) {
		SingleNode<T> current = this.front;
		SingleNode<T> previous = current;
		while (!current.getDatum().equals(key) & !current.equals(null)) {
			previous = current;
			current = current.getNext();
		}
		if (current.equals(null)) {
			previous = null;
		}
		return previous;
	}

	/**
	 * Appends data to the end of this SingleList.
	 *
	 * @param datum The value to append.
	 */
	public void append(final T datum) {
		SingleNode<T> newData = new SingleNode<T>(datum, null);
		if (this.front.equals(null)) {
			this.front = this.rear = newData;
			this.length = 1;
		} else {
			this.rear.setNext(newData);
			this.rear = this.rear.getNext();
			this.length += 1;
		}
		return;
	}

	/**
	 * Removes duplicates from this SingleList. The list contains one and only one
	 * of each value formerly present in this SingleList. The first occurrence of
	 * each value is preserved.
	 */
	public void clean() {
		SingleNode<T> current = this.front;
		SingleNode<T> previous = current;
		while (!current.equals(null)) {
			if (this.linearSearch(current.getDatum()).getNext() == current) {
				previous = current; // makes previous into current
				current = current.getNext(); // makes current into current.getNext()
			} else {
				current = current.getNext(); // makes current into current.getNext()
				previous.setNext(current); // makes previous.next() into the new current
				this.length -= 1; // decrements length count
			}
		}
		return;
	}

	/**
	 * Combines contents of two lists into a third. Values are alternated from the
	 * origin lists into this SingleList. The origin lists are empty when finished.
	 * NOTE: data must not be moved, only nodes.
	 *
	 * @param left  The first list to combine with this SingleList.
	 * @param right The second list to combine with this SingleList.
	 */
	public void combine(final SingleList<T> left, final SingleList<T> right) {
		if (this.front.equals(null)) {
			if (!left.front.equals(null)) { // if left is not empty
				this.front = this.rear = left.front; // make the first element of this list the first element of left
				left.front = left.front.getNext();
				left.length -= 1; // decrement left length count
				this.length += 1;
			} else if (right.front.equals(null)) {
				this.front = this.rear = right.front; // else if right is not empty
				right.front = right.front.getNext(); // make the first element of this list the first element of right
				right.length -= 1; // decrement right length count
				this.length += 1;
			}
		}
		while (!left.front.equals(null) || !right.front.equals(null)) {
			if (!left.front.equals(null)) {
				this.rear.setNext(left.front);
				this.rear = this.rear.getNext();
				left.front = left.front.getNext();
				this.length += 1;
				left.length -= 1;
			}
			if (!left.front.equals(null)) { // if right is not empty
				this.rear.setNext(right.front); // append the right.front to this.rear
				this.rear = this.rear.getNext(); // make this.rear into this.rear.next()
				right.front = right.front.getNext(); // make right.front into right.front.next()
				this.length += 1; // increment this.length
				right.length -= 1; // decrement right.length
			}
		}
		return;
	}

	/**
	 * Determines if this SingleList contains key.
	 *
	 * @param key The key value to look for.
	 * @return true if key is in this SingleList, false otherwise.
	 */
	public boolean contains(final T key) {
		boolean result = false;
		SingleNode<T> current = this.front;
		while (!current.equals(null) & !result) {
			if (current.getDatum().equals(key)) {
				result = true;
			}
			current = current.getNext();
		}
		return result;
	}

	/**
	 * Finds the number of times key appears in list.
	 *
	 * @param key The value to look for.
	 * @return The number of times key appears in this SingleList.
	 */
	public int count(final T key) {
		int result = 0;
		SingleNode<T> current = this.front;
		while (!current.equals(null)) {
			if (current.getDatum().equals(key)) {
				result += 1;
			}
			current = current.getNext();
		}
		return result;
	}

	/**
	 * Finds and returns the value in list that matches key.
	 *
	 * @param key The value to search for.
	 * @return The value that matches key, null otherwise.
	 */
	public T find(final T key) {
		T result = null;
		SingleNode<T> current = this.front;
		while (!current.equals(null) & result.equals(null)) {
			if (current.getDatum().equals(key)) {
				result = current.getDatum();
			}
		}
		return result;
	}

	/**
	 * Get the nth item in this SingleList.
	 *
	 * @param n The index of the item to return.
	 * @return The nth item in this SingleList.
	 * @throws ArrayIndexOutOfBoundsException if n is not a valid index.
	 */
	public T get(final int n) throws ArrayIndexOutOfBoundsException {
		SingleNode<T> current = this.front;
		for (int i = 0; i < n; i++) {
			current = current.getNext();
		}
		T result = current.getDatum();
		return result;
	}

	/**
	 * Determines whether two lists are identical.
	 *
	 * @param source The list to compare against this SingleList.
	 * @return true if this SingleList contains the same values in the same order as
	 *         source, false otherwise.
	 */
	public boolean identical(final SingleList<T> source) {
		SingleNode<T> currentThis = this.front;
		SingleNode<T> currentSource = source.front;
		boolean result = true;
		while (!currentThis.equals(null) & !currentSource.equals(null) & result) {
			if (!currentThis.getDatum().equals(currentSource.getDatum())) {
				result = false;
			}
			currentThis = currentThis.getNext();
			currentSource = currentSource.getNext();
		}
		if (!currentThis.getDatum().equals(currentSource.getDatum())) {
			result = false;
		}
		return result;
	}

	/**
	 * Finds the first location of a value by key in this SingleList.
	 *
	 * @param key The value to search for.
	 * @return The index of key in this SingleList, -1 otherwise.
	 */
	public int index(final T key) {
		int result = -1;
		int indexLocation = 0;
		SingleNode<T> current = this.front;
		while (!current.equals(null) & result == -1) {
			if (current.getDatum().equals(key)) {
				result = indexLocation;
			}
			indexLocation += 1;
			current = current.getNext();
		}
		return result;
	}

	/**
	 * Inserts value into this SingleList at index i. If i greater than the length
	 * of this SingleList, append data to the end of this SingleList.
	 *
	 * @param i     The index to insert the new data at.
	 * @param datum The new value to insert into this SingleList.
	 */
	public void insert(int i, final T datum) {
		SingleNode<T> newNode = new SingleNode<T>(datum, null);
		if (i >= (this.length - 1)) {
			this.rear.setNext(newNode);
			this.rear = newNode;
			this.length += 1;
		} else if (i < 1) {
			newNode.setNext(this.front);
			this.front = newNode;
			this.length += 1;
		} else {
			SingleNode<T> current = this.front;
			SingleNode<T> previous = current;
			for (int j = 0; j < i; j++) {
				previous = current;
				current = current.getNext();
			}
			previous.setNext(newNode);
			newNode.setNext(current);
			this.length += 1;
		}
		return;
	}

	/**
	 * Creates an intersection of two other SingleLists into this SingleList. Copies
	 * data to this SingleList. left and right SingleLists are unchanged. Values
	 * from left are copied in order first, then values from right are copied in
	 * order.
	 *
	 * @param left  The first SingleList to create an intersection from.
	 * @param right The second SingleList to create an intersection from.
	 */
	public void intersection(final SingleList<T> left, final SingleList<T> right) {
		SingleNode<T> current = left.front;
		while (!current.equals(null)) {
			T newData = current.getDatum();
			SingleNode<T> newNode = new SingleNode<T>(newData, null);
			if (this.front.equals(null)) {
				this.front = this.rear = newNode;
			} else {
				this.rear.setNext(newNode);
				this.rear = this.rear.getNext();
			}
			this.length += 1;
			current = current.getNext();
		}
		current = right.front;
		while (!current.equals(null)) {
			T newData = current.getDatum();
			SingleNode<T> newNode = new SingleNode<T>(newData, null);
			this.rear.setNext(newNode);
			this.rear = this.rear.getNext();
			current = current.getNext();
		}
		return;
	}

	/**
	 * Finds the maximum value in this SingleList.
	 *
	 * @return The maximum value.
	 */
	public T max() {
		T result = this.front.getDatum();
		SingleNode<T> current = this.front;
		while (!current.equals(null)) {
			if (result.compareTo(current.getDatum()) < 0) {
				result = current.getDatum();
			}
			current = current.getNext();
		}
		return result;
	}

	/**
	 * Finds the minimum value in this SingleList.
	 *
	 * @return The minimum value.
	 */
	public T min() {
		T result = this.front.getDatum();
		SingleNode<T> current = this.front;
		while (!current.equals(null)) {
			if (result.compareTo(current.getDatum()) > 0) {
				result = current.getDatum();
			}
			current = current.getNext();
		}
		return result;
	}

	/**
	 * Inserts value into the front of this SingleList.
	 *
	 * @param datum The value to insert into the front of this SingleList.
	 */
	public void prepend(final T datum) {
		SingleNode<T> newNode = new SingleNode<T>(datum, this.front);
		this.front = newNode;
		this.length += 1;
		return;
	}

	/**
	 * Finds, removes, and returns the value in this SingleList that matches key.
	 *
	 * @param key The value to search for.
	 * @return The value matching key, null otherwise.
	 */
	public T remove(final T key) {
		T result = null;
		SingleNode<T> previous = this.linearSearch(key);
		if (previous.equals(null)) {
			result = null;
		} else {
			SingleNode<T> current = previous.getNext();
			result = current.getDatum();
			current = current.getNext();
			previous.setNext(current);
			if (current.equals(null)) {
				this.rear = previous;
			}
		}
		return result;
	}

	/**
	 * Removes the value at the front of this SingleList.
	 *
	 * @return The value at the front of this SingleList.
	 */
	public T removeFront() {
		T result = null;
		if (this.front.equals(null)) {
			result = null;
		} else {
			result = this.front.getDatum();
			this.front = this.front.getNext();
			this.length -= 1;
		}
		return result;
	}

	/**
	 * Finds and removes all values in this SingleList that match key.
	 *
	 * @param key The value to search for.
	 */
	public void removeMany(final T key) {
		SingleNode<T> current = this.front;
		SingleNode<T> previous = current;
		while (!current.equals(null)) {
			if (current.getDatum().equals(key)) {
				current = current.getNext(); 	// makes current into current.getNext()
				previous.setNext(current); 		// makes previous.next() into the new current
				this.length -= 1; 				// decrements length count
			} else {
				previous = current; 			// makes previous into current
				current = current.getNext(); 	// makes current into current.getNext()
			}
		}
		return;
	}

	/**
	 * Reverses the order of the values in this SingleList.
	 */
	public void reverse() {
		SingleNode<T> previous = null;
		SingleNode<T> current = this.front;
		SingleNode<T> next = null;;
		while (!current.equals(null)) {
			next = current.getNext();
			current.setNext(previous);
			previous = current;
			current = next;
		}
		return;
	}

	/**
	 * Splits the contents of this SingleList into the left and right SingleLists.
	 * Moves nodes only - does not move value or call the high-level methods insert
	 * or remove. this SingleList is empty when done. The first half of this
	 * SingleList is moved to left, and the last half of this SingleList is moved to
	 * right. If the resulting lengths are not the same, left should have one more
	 * item than right. Order is preserved.
	 *
	 * @param left  The first SingleList to move nodes to.
	 * @param right The second SingleList to move nodes to.
	 */
	public void split(final SingleList<T> left, final SingleList<T> right) {
		SingleNode<T> current = this.front;
		SingleNode<T> previous = current;
		for (int i = 0; i < (this.length / 2); i++) {
			previous = current;
			current = current.getNext();
		}
		left.front = this.front;
		left.rear = previous;
		left.rear.setNext(null);
		right.front = current;
		right.rear = this.rear;
		left.length = right.length = (this.length / 2);
		if (this.length % 2 == 1) {
			right.length += 1;
		}
		this.front = this.rear = null;
		this.length = 0;
		return;
	}

	/**
	 * Splits the contents of this SingleList into the left and right SingleLists.
	 * Moves nodes only - does not move value or call the high-level methods insert
	 * or remove. this SingleList is empty when done. Nodes are moved alternately
	 * from this SingleList to left and right. Order is preserved.
	 *
	 * @param left  The first SingleList to move nodes to.
	 * @param right The second SingleList to move nodes to.
	 */
	public void splitAlternate(final SingleList<T> left, final SingleList<T> right) {
		SingleNode<T> current = this.front;
		while (!current.equals(null)) {
			if (left.front.equals(null)) {
				left.front = current;
			}
			left.rear = current;
			left.length += 1;
			current = current.getNext();
			this.front = current;
			this.length -= 1;
			if (!current.equals(null)) {
				if (right.front.equals(null)) {
					right.front = current;
				}
				right.rear = current;
				right.length += 1;
				current = current.getNext();
				this.front = current;
				this.length -= 1;
			}
		}
		return;
	}

	/**
	 * Creates a union of two other SingleLists into this SingleList. Copies value
	 * to this list. left and right SingleLists are unchanged. Values from left are
	 * copied in order first, then values from right are copied in order.
	 *
	 * @param left  The first SingleList to create a union from.
	 * @param right The second SingleList to create a union from.
	 */
	public void union(final SingleList<T> left, final SingleList<T> right) {
		SingleNode<T> current = left.front;
		while (!current.equals(null)) {
			T newData = current.getDatum();
			SingleNode<T> newNode = new SingleNode<T>(newData, null);
			if (this.front.equals(null)) {
				this.front = this.rear = newNode;
			} else {
				this.rear.setNext(newNode);
				this.rear = this.rear.getNext();
			}
			this.length += 1;
			current = current.getNext();
		}
		current = right.front;
		while (!current.equals(null)) {
			T newData = current.getDatum();
			SingleNode<T> newNode = new SingleNode<T>(newData, null);
			this.rear.setNext(newNode);
			this.rear = this.rear.getNext();
			current = current.getNext();
		}
		return;
	}
}
