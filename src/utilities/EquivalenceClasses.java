package utilities;

import java.util.Comparator;
import java.util.List;

public class EquivalenceClasses<T> {

	protected Comparator<T> _comparator;
	protected List<LinkedEquivalenceClass<T>> _classes;
	
	/**
	 * constructor passes in comparator
	 * @param comparator
	 */
	public EquivalenceClasses(Comparator<T> comparator) {
		_comparator = comparator;
		_classes = null;
	}
	
	/**
	 * 
	 * @param element
	 * @return
	 */
	public boolean add(T element) {
		return false;
	}
	
	/**
	 * 
	 * @param target
	 * @return
	 */
	public boolean contains(T target) {
		return false;
	}
	
	/**
	 * 
	 * @return
	 */
	public int size() {
		return 0;
	}
	
	/**
	 * 
	 * @return
	 */
	public int numClasses() {
		return 0;
	}

	/**
	 * 
	 * @param element
	 * @return
	 */
	protected int IndexOfClass(T element) {
		return 0;
	}
	
	/**
	 * Returns a string representation of the object.
	 */
	public String toString() {
		return null;
	}
	
}
