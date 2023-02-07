package utilities;

import java.util.Comparator;

public class LinkedEquivalenceClass<T> {
	private T _canonical;
	private Comparator<T> _comparator;
	private LinkedList<T> _rest;
	
	public LinkedEquivalenceClass(Comparator<T> comparator) {
		_comparator = comparator;
		_canonical = null;
		_rest = new LinkedList<T>();
	}
	
	public T canonical() {
		return _canonical;
	}
	
	public boolean isEmpty() {
		return (_canonical == null && _rest.isEmpty());
	}
	
	public void clear() {
		_canonical = null;
		_rest.clear();
	}
	
	public void clearNonCanonical() {
		_rest.clear();
	}
	
	public int size() {
		if (_canonical == null) return _rest.size();
		return _rest.size()+1;
	}
	
	public boolean add(T element) {
		if (!belongs(element)) return false;
		_rest.addToFront(element);
		return true;
	}
	
	public boolean contains(T target) {
		if (!belongs(target)) return false;
		return (_canonical.equals(target) || _rest.contains(target));
	}
	
	public boolean belongs(T target) {
		return (_comparator.compare(_canonical, target) == 0);
	}
	
	public boolean remove(T target) {
		if (!belongs(target)) return false;
		return _rest.remove(target);
	}
	
	public boolean removeCanonical() {
		if (_canonical == null) return false;
		_canonical = null;
		return true;
	}
	
	public boolean demoteAndSetCanonical(T element) {
		if (_canonical != null) return false;
		_canonical = element;
		return true;
	}
	
	public String toString() {
		return _canonical + " " + _rest.toString();
	}
}
