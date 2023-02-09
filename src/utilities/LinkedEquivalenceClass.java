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
	 * @return the canonical variable
	 */
	public T canonical() {
		return _canonical;
	}
	
	/**
	 * returns if there is no canonical and no rest of the list
	 * @return if it is empty
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
	 * @return the size of the rest plus the canonical
	 */
	public int size() {
		if (_canonical == null) return _rest.size();
		return _rest.size()+1;
	}
	
	/**
	 * if the element belongs in the list and isn't in it add it to the front of the rest of the list
	 * @param element
	 * @return whether the element was added
	 */
	public boolean add(T element) {
		if (isEmpty()) {
			_canonical = element;
			return true;
		}
		if (!belongs(element) || _rest.contains(element) || _canonical.equals(element)) return false;
		_rest.addToFront(element);
		return true;
	}
	
	/**
	 * check if it belongs in the list 
	 * @param target
	 * @return if the target belongs and is contained
	 */
	public boolean contains(T target) {
		if (!belongs(target)) return false;
		return (_canonical.equals(target) || _rest.contains(target));
	}
	
	/**
	 * check if it is equivalent to the canonical
	 * @param target
	 * @return if the target belongs
	 */
	public boolean belongs(T target) {
		return (_comparator.compare(_canonical, target) == 0);
	}
	
	/**
	 * if the element belongs in the list remove it from the rest of the list
	 * @param target
	 * @return whether the target was removed
	 */
	public boolean remove(T target) {
		if (!belongs(target)) return false;
		return _rest.remove(target);
	}
	
	/**
	 * removes the canonical value if there is something to replace it with
	 * @return whether the canonical variable was removed
	 */
	public boolean removeCanonical() {
		if (_rest.isEmpty()) return false;
		_canonical = _rest.pop_front();
		return true;
	}
	
	/**
	 * if the element belongs in the list add it as the canonical and add the existing canonical to the list 
	 * @param element
	 * @return whether the canonical value was set
	 */
	public boolean demoteAndSetCanonical(T element) {
		if (isEmpty()) {
			_canonical = element;
			return true;
		}
		if (!belongs(element) || _canonical.equals(element)) return false;
		_rest.remove(element);
		T temp = _canonical;
		_canonical = element;
		add(temp);
		return true;
	}
	
	/**
	 * makes the whole list a string
	 */
	public String toString() {
		if (isEmpty()) return "";
		if (_rest.isEmpty()) return _canonical + "";
		return _canonical + " | " + _rest.toString();
	}
}
