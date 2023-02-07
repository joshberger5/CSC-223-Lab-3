package utilities;

import java.util.Comparator;
import java.util.List;

public class EquivalenceClasses<T> {

	protected Comparator<T> _comparator;
	protected List<LinkedEquivalenceClass<T>> _classes;
	
	public EquivalenceClasses(Comparator<T> comparatorNum) {
		
	}
	
	public boolean add(T element) {
		return false;
	}
	
	public boolean contains(T target) {
		return false;
	}
	
	public int size() {
		return 0;
	}
	
	public int numClasses() {
		return 0;
	}

	protected int IndexOfClass(T element) {
		return 0;
	}

	public String toString() {
		return null;
	}
	
}
