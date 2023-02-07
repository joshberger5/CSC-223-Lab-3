package utilities;

import java.util.Comparator;

public class LinkedEquivalenceClass<T> {
	private T _canonical;
	private Comparator<T> _comparator;
	private LinkedList<T> _rest;
	
	/**
	 * instantiate the variables
	 * @param comparator
	 */
	public LinkedEquivalenceClass(Comparator<T> comparator) {
		_comparator = comparator;
		_canonical = null;
		_rest = new LinkedList<T>();
	}
	
	/**
	 * returns the canonical variable
	 * @return
	 */
	public T canonical() {
		return _canonical;
	}
	
	/**
	 * returns if there is no canonical and no rest of the list
	 * @return
	 */
	public boolean isEmpty() {
		return (_canonical == null && _rest.isEmpty());
	}
	
	/** 
	 * gets rid of both the canonical and the rest of the list
	 */
	public void clear() {
		_canonical = null;
		_rest.clear();
	}
	
	/**
	 * gets rid of rest of the list not canonical
	 */
	public void clearNonCanonical() {
		_rest.clear();
	}
	
	/**
	 * returns the size of the rest of the list and the canonical
	 * @return
	 */
	public int size() {
		if (_canonical == null) return _rest.size();
		return _rest.size()+1;
	}
	
	/**
	 * if the element belongs in the list add it to the front of the rest of the list
	 * @param element
	 * @return
	 */
	public boolean add(T element) {
		if (!belongs(element)) return false;
		_rest.addToFront(element);
		return true;
	}
	
	/**
	 * check if it belongs in the list 
	 * @param target
	 * @return
	 */
	public boolean contains(T target) {
		if (!belongs(target)) return false;
		return (_canonical.equals(target) || _rest.contains(target));
	}
	
	/**
	 * check if it is equivalent to the canonical
	 * @param target
	 * @return
	 */
	public boolean belongs(T target) {
		return (_comparator.compare(_canonical, target) == 0);
	}
	
	/**
	 * 
	 * @param target
	 * @return
	 */
	public boolean remove(T target) {
		if (!belongs(target)) return false;
		return _rest.remove(target);
	}
	
	/**
	 * removes the canonical value if there is one
	 * @return
	 */
	public boolean removeCanonical() {
		if (_canonical == null) return false;
		_canonical = null;
		return true;
	}
	
	/**
	 * sets a canonical value if there is one
	 * @param element
	 * @return
	 */
	public boolean demoteAndSetCanonical(T element) {
		if (_canonical != null) return false;
		_canonical = element;
		return true;
	}
	
	/**
	 * makes the whole list a string
	 */
	public String toString() {
		return _canonical + " " + _rest.toString();
	}
}
