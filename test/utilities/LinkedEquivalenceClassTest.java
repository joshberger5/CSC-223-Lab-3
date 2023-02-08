package utilities;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

public class LinkedEquivalenceClassTest {
	// the comparator means that the list is either divisible by 5 or not
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
		
		// makes sure everything is empty
		assertTrue(e.canonical() == null);
		assertTrue(e.isEmpty());
	}
	
	@Test
	void testDemoteAndSetCanonical() {
		Comparator<Integer> c = setComparatorDivBy5();
		LinkedEquivalenceClass e = new LinkedEquivalenceClass<Integer>(c);
		
		// adding an element to an empty list should make it the canonical
		e.add(5);
		assertTrue(e.canonical().equals(5));
		assertEquals("5", e.toString());
		
		// demoting and setting an element should replace the current canonical and add it to rest
		e.demoteAndSetCanonical(10);
		assertEquals("10 | 5", e.toString());
		
		// makes sure it works after clearing the list
		e.clear();
		e.demoteAndSetCanonical(5);
		assertTrue(e.canonical().equals(5));
	}
	
	@Test
	void testAdd() {
		Comparator<Integer> c = setComparatorDivBy5();
		LinkedEquivalenceClass e = new LinkedEquivalenceClass<Integer>(c);
		
		// already tested adding to an empty list making it the canonical in testDemoteAndSetCanonical
		// so I'll test adding a second element
		e.add(5);
		e.add(10);
		assertEquals("5 | 10", e.toString());
		
		// check that it prevents adding something to the list that equals the canonical
		e.add(5);
		assertEquals("5 | 10", e.toString());
	}
}
