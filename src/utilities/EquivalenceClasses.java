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
	 * creates a new equivalence class with the element
	 * @param element
	 * @return whether the class was created
	 */
	private boolean createNewEquivalenceClass(T element) {
		LinkedEquivalenceClass<T> equivalence = new LinkedEquivalenceClass<T>(_comparator);
		equivalence.add(element);
		return _classes.add(equivalence);
	}

	/**
	 * Adds element to preexisting equivalence class
	 * @param element
	 * @return whether the element was added
	 */
	private boolean addToEquivalenceClass(T element) {
		for(LinkedEquivalenceClass<T> aClass : _classes) {
			if(aClass.add(element)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Determines whether one of the equivalence lists contained the target element
	 * @param target
	 * @return whether the target element was contained
	 */
	public boolean contains(T target) {
		return indexOfClass(target) > -1;
	}
	
	/**
	 * adds an element to an appropriate equivalence list based on the comparator
	 * @param element
	 * @return whether the element was added
	 */
	public boolean add(T element) {
		if (element == null || _comparator == null) return false;
		return addToEquivalenceClass(element) ? true :
			   contains(element) ? false : createNewEquivalenceClass(element);
	}
	
	/**
	 * finds size of the EquivalenceClasses
	 * @return total size of all equivalence classes
	 */
	public int size() {
		int size = 0;
		for(LinkedEquivalenceClass<T> aClass : _classes) {
			size += aClass.size();
		}
		return size;
	}
	
	/**
	 * finds the number of equivalences classes contained
	 * @return the number of equivalences classes
	 */
	public int numClasses() {
		return _classes.size();
	}

	/**
	 * finds the index of the class that has element
	 * @param element
	 * @return index of class containing element or -1 if not found
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
		StringBuilder output = new StringBuilder();
		output.append("Classes:\n");
		for(LinkedEquivalenceClass<T> aClass : _classes) {
			output.append(aClass + "\n");
		}
		return output.toString();
	}
	
}
