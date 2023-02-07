package utilities;

import java.util.Comparator;

public class LinkedEquivalenceClass<T> {
	T _canonical;
	Comparator<T> _comparator;
	LinkedList<T> _rest;
	
	public LinkedEquivalenceClass(Comparator<T> comparator) {
		_comparator = comparator;
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
		
	}
	
	public String toString() {
		
	}
}
