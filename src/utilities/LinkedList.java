package utilities;

public class LinkedList<T> {
	// the list has sentinel nodes head and tail as well as a size
	private Node _head;
	private Node _tail;
	private int _size;
	
	/**
	 * each node has data in it and points to the node before it
	 * @author Josh Berger
	 */
	private class Node {
		private T _data;
		private Node _next;
		
		/**
		  * default constructor creates a node with nothing in it and pointing to nothing
		  */
		public Node() {
			this(null, null);
		}
		
		/**
		 * constructor creates a node from the passed in values
		 * @param data
		 * @param next
		 */
		public Node(T data, Node next) {
			_data = data;
			_next = next;
		}
	}
	
	/**
	 * default constructor creates a list with only the head pointing to the tail and a size of 0
	 */
	public LinkedList() {
		_head = new Node(null, null);
		_tail = new Node(null, null);
		_head._next = _tail;
		_size = 0;
	}
	
	/**
	 * checks if there are no nodes in between the sentinel nodes
	 * @return whether the LinkedList is empty
	 */
	public boolean isEmpty() {
		return _head._next == _tail;
	}
	
	/**
	 * reverts to the head pointing to the tail and the size to 0
	 */
	public void clear() {
		_head._next = _tail;
		_size = 0;
	}
	
	/**
	 * returns the size
	 * @return size of LinkedList
	 */
	public int size() {
		return _size;
	}
	
	/**
	 * if the passed in element is valid adds it as a node to the front of the list adding 1 to the size
	 * @param element
	 */
	public void addToFront(T element) {
		if (element == null && !contains(element)) return;
		Node node = new Node(element, _head._next);
		_head._next = node;
		_size++;
	}
	
	/**
	 * checks if the passed in target is in the list
	 * @param target
	 * @return whether the target is in the LinkedList
	 */
	public boolean contains(T target) {
		return getIndex(target) > -1;
	}
	
	/**
	 * finds the index of the target and if not, returns -1
	 * @param target
	 * @return the index of the target or -1 if not found
	 */
	private int getIndex(T target) {
		if (target == null) return -1;
		return getIndex(target, _head._next, 0);
	}
	
	private int getIndex(T target, Node node, int index) {
		if (node == _tail || node == null) return -1;
		if (node._data.equals(target)) return index;
		return getIndex(target, node._next, index+1);
	}
	
	/**
	 * returns the matching node in the list to the passed in target
	 * @param target
	 * @return node equivalent to target from LinkedList
	 */
	private Node findNode(T target) {
		if (target == null) return null;
		return findNode(target, _head._next);
	}
	
	private Node findNode(T target, Node node) {
		if (node == _tail || node == null) return null;
		if (node._data.equals(target)) return node;
		return findNode(target, node._next);
	}
	
	/**
	 * returns the node before the one that was passed in
	 * @param target
	 * @return the prior node to target
	 */
	private Node previous(T target) {
		if (target == null) return null;
		return previous(target, _head);
	}
	
	private Node previous(T target, Node node) {
		if (node == _tail || node == null) return null;
		if (node._next._data.equals(target)) return node;
		return previous(target, node._next);
	}
	
	/**
	 * removes the passed in target node and decreases the size by 1
	 * @param target
	 * @return whether the target was removed
	 */
	public boolean remove(T target) {
		if (target != null) {
			Node p = previous(target);
			p._next = p._next._next;
			_size--;
			return true;
		}
		return false;
	}
	
	// 
	/**
	 * returns the node before the tail
	 * @return the node at the last index
	 */
	private Node last() {
		if (_head._next == _tail) return _head;
		return last(_head._next);
	}
	
	private Node last(Node n) {
		if (n._next == _tail) return n;
		return (last(n._next));
	}
	
	/**
	 * adds an element as a node before the tail
	 * @param element
	 */
	public void addToBack(T element) {
		if (element == null && !contains(element)) return;
		Node node = new Node(element, _tail);
		last()._next = node;
		_size++;
	}
	
	/**
	 * makes the node's data into a string
	 */
	public String toString() {
		return toString("", _head._next);
	}
	
	public String toString(String string, Node node) {
		if (node == _tail) return string;
		string = string + node._data + "";
		if (node._next == _tail) return string;
		string = string + " ";
		return toString(string, node._next);
	}
	
	/**
	 * reverses the list
	 */
	public void reverse() {
		if (_head._next == _tail || size() == 1) return;
		reverse(_head._next, _tail, last());
	}
	
	private void reverse(Node n, Node next, Node newFirst) {
		if (n == newFirst) {
			n._next = next;
			return;
		}
		Node temp = n._next;
		n._next = next;
		_head._next = temp;
		reverse(_head._next, n, newFirst);
	}
}
