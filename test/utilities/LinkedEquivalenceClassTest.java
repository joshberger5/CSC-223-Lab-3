package utilities;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

public class LinkedEquivalenceClassTest {
	Comparator<Integer> setComparatorDivBy5() {
		return new Comparator<Integer>() {
			// they are equivalent if they are both multiples of 5
			public int compare(Integer x, Integer y) { 
				return x % 5 == 0 && y % 5 == 0 ? 0 : 1;
			}
		};
	}
	
	@Test
	void testConstructor() {
		Comparator<Integer> c = setComparatorDivBy5();
		
		LinkedEquivalenceClass e = new LinkedEquivalenceClass<Integer>(c);
		
		assertTrue(e.canonical() == null);
		assertTrue(e.isEmpty());
	}
	
	@Test
	void demoteAndSetCanonicalTest() {
		Comparator<Integer> c = setComparatorDivBy5();
		
		LinkedEquivalenceClass e = new LinkedEquivalenceClass<Integer>(c);
		
		Integer five = 5;
		e.add(5);
		assertTrue(e.canonical() == five);
	}
}
