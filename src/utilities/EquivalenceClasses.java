package utilities;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EquivalenceClasses<T> {

	protected Comparator<T> _comparator;
	protected List<LinkedEquivalenceClass<T>> _classes;
	
	/**
	 * constructor that retrieves a comparator
	 * @param comparator
	 */
	public EquivalenceClasses(Comparator<T> comparator) {
		_comparator = comparator;
		_classes = new ArrayList<LinkedEquivalenceClass<T>>();
	}
	
	/**
	 * adds an element to an appropriate equivalence list based on the comparator
	 * @param element
	 * @return
	 */
	public boolean add(T element) {
		// adds to existing equivalence class
		for(LinkedEquivalenceClass<T> aClass : _classes) {
			if(aClass.add(element)) {
				return true;
			}
		}
		// creates new equivalence class containing element
		LinkedEquivalenceClass<T> equivalence = new LinkedEquivalenceClass<T>(_comparator);
		if(!equivalence.add(element)) {
			return false;
		}
		return _classes.add(equivalence);
	}
	
	/**
	 * 
	 * @param target
	 * @return
	 */
	public boolean contains(T target) {
		return indexOfClass(target) > -1;
	}
	
	/**
	 * 
	 * @return
	 */
	public int size() {
		int size = 0;
		for(LinkedEquivalenceClass<T> aClass : _classes) {
			size += aClass.size();
		}
		return size;
	}
	
	/**
	 * 
	 * @return
	 */
	public int numClasses() {
		return _classes.size();
	}

	/**
	 * 
	 * @param element
	 * @return
	 */
	protected int indexOfClass(T element) {
		for(int i=0; i<_classes.size(); i++) {
			if (_classes.get(i).contains(element)) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Returns a string representation of the object.
	 */
	public String toString() {
		return null;
	}
	
}
