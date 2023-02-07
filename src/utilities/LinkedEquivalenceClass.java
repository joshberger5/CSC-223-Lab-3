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
		
	}
	
	public void clear() {
		
	}
	
	public void clearNonCanonical() {
		
	}
	
	public int size() {
		
	}
	
	public boolean add(T element) {
		
	}
	
	public boolean contains(T target) {
		
	}
	
	public boolean belongs(T target) {
		
	}
	
	public boolean remove(T target) {
		
	}
	
	public boolean removeCanonical() {
		
	}
	
	public boolean demoteAndSetCanonical(T element) {
		_canonical = element;
		return true;
	}
	
	public String toString() {
		
	}
}
