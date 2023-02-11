/**
* Creates tests for LinkedList
*
* <p>Bugs: none known
*
* @author Josh Berger and Jake Shore
* @date 2/13/2023
*/
package utilities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class LinkedListTest {
	@Test
	void testConstructor() {
		LinkedList<Integer> l = new LinkedList<Integer>();
		
		assertEquals("", l.toString());
	}
	
	// makes sure that trying to add null to the front doesn't work
	@Test
	void testAddToFrontNull() {
		LinkedList<Integer> l = new LinkedList<Integer>();
		
		l.addToFront(null);
		
		assertEquals("", l.toString());
	}
	
	// makes sure that adding to the front of an empty list works
	@Test
	void testAddToFrontOfEmptyList() {
		LinkedList<Integer> l = new LinkedList<Integer>();
		
		l.addToFront(5);
		
		assertEquals("5", l.toString());
	}
	
	// makes sure that adding to the front of an unempty list works
	@Test
	void testAddToFrontOfUnemptyList() {
		LinkedList<Integer> l = new LinkedList<Integer>();
		
		l.addToFront(6);
		l.addToFront(5);
		
		assertEquals("5 6", l.toString());
	}
	
	//makes sure that adding to the front works after calling clear
	@Test
	void testAddToFrontAfterClear() {
		LinkedList<Integer> l = new LinkedList<Integer>();
		
		l.addToFront(6);
		l.addToFront(5);
		
		l.clear();
		
		l.addToFront(4);
		
		assertEquals("4", l.toString());
	}
	
	// makes sure that trying to add null to the back doesn't work
	@Test
	void testAddToBackNull() {
		LinkedList<Integer> l = new LinkedList<Integer>();
		
		l.addToBack(null);
		
		assertEquals("", l.toString());
	}
	
	// makes sure that adding to the back of an empty list works
	@Test
	void testAddToBackOfEmptyList() {
		LinkedList<Integer> l = new LinkedList<Integer>();
		
		l.addToBack(5);
		
		assertEquals("5", l.toString());
	}
		
	// makes sure that adding to the back of an unempty list works
	@Test
	void testAddToBackOfUnemptyList() {
		LinkedList<Integer> l = new LinkedList<Integer>();
		
		l.addToBack(5);
		l.addToBack(6);
		
		assertEquals("5 6", l.toString());
	}
		
	//makes sure that adding to the back works after calling clear
	@Test
	void testAddToBackAfterClear() {
		LinkedList<Integer> l = new LinkedList<Integer>();
		
		l.addToBack(5);
		l.addToBack(6);
		
		l.clear();
		
		l.addToBack(4);
		
		assertEquals("4", l.toString());
	}
	
	// makes sure that contains returns false when passed in null
	@Test
	void testContainsWithNull() {
		LinkedList<Integer> l = new LinkedList<Integer>();
		
		assertFalse(l.contains(null));
	}
	
	// makes sure that contains returns true when passed in something that is in the list
	@Test
	void testContainsWithSomethingInList() {
		LinkedList<Integer> l = new LinkedList<Integer>();
		
		l.addToFront(5);
		
		assertTrue(l.contains(5));
	}
	
	// makes sure that contains returns false when passed in something that isn't in the list
	// when the list has something in it
	@Test
	void testContainsWithSomethingNotInList() {
		LinkedList<Integer> l = new LinkedList<Integer>();
		
		l.addToFront(5);
		
		assertFalse(l.contains(6));
	}
	
	// makes sure that contains returns false when passed in something that isn't in the list
	// when the list has nothing in it
	@Test
	void testContainsWithEmptyList() {
		LinkedList<Integer> l = new LinkedList<Integer>();
		
		assertFalse(l.contains(6));
	}
	
	// makes sure that remove handles null input
	@Test
	void testRemoveNull() {
		LinkedList<Integer> l = new LinkedList<Integer>();
		
		assertFalse(l.remove(null));
	}
	
	// makes sure that remove works with only one element in the list
	// after both add to back and add to front
	@Test
	void testRemoveSingleElement() {
		LinkedList<Integer> l = new LinkedList<Integer>();
		
		l.addToFront(5);
		
		assertTrue(l.remove(5));
		assertEquals("", l.toString());
		
		l.addToBack(5);
		
		assertTrue(l.remove(5));
		assertEquals("", l.toString());
	}
	
	// makes sure that remove works on the last element of a multi-element list
	@Test
	void testRemoveLast() {
		LinkedList<Integer> l = new LinkedList<Integer>();
		
		l.addToFront(2);
		l.addToBack(3);
		l.addToFront(1);
		l.addToBack(4);
		
		assertTrue(l.remove(4));
		assertEquals("1 2 3", l.toString());
	}
	
	// makes sure that remove works on the first element of a multi-element list
	@Test
	void testRemoveFirst() {
		LinkedList<Integer> l = new LinkedList<Integer>();
			
		l.addToFront(2);
		l.addToBack(3);
		l.addToFront(1);
		l.addToBack(4);
			
		assertTrue(l.remove(1));
		assertEquals("2 3 4", l.toString());
	}
	
	// makes sure that remove works on a middle element of a multi-element list
	@Test
	void testRemoveMiddle() {
		LinkedList<Integer> l = new LinkedList<Integer>();
				
		l.addToFront(2);
		l.addToBack(3);
		l.addToFront(1);
		l.addToBack(4);
				
		assertTrue(l.remove(2));
		assertEquals("1 3 4", l.toString());
	}
	
	// makes sure that size returns 0 for an empty list
	@Test
	void testSizeEmptyList() {
		LinkedList<Integer> l = new LinkedList<Integer>();
		
		assertEquals(0, l.size());
	}
	
	// makes sure that size increases after add to back
	@Test
	void testSizeAfterAddToBack() {
		LinkedList<Integer> l = new LinkedList<Integer>();
		
		l.addToBack(5);
		l.addToBack(6);
		
		assertEquals(2, l.size());
	}
	
	// makes sure that size increases after add to front
	@Test
	void testSizeAfterAddToFront() {
		LinkedList<Integer> l = new LinkedList<Integer>();
		
		l.addToFront(5);
		l.addToFront(6);
		
		assertEquals(2, l.size());
	}
	
	// makes sure that size decreases after remove
	@Test
	void testSizeAfterRemove() {
		LinkedList<Integer> l = new LinkedList<Integer>();
		
		l.addToFront(1);
		l.addToFront(2);
		
		l.addToBack(3);
		l.addToBack(4);
		
		l.remove(1);
		l.remove(2);
		l.remove(3);
		l.remove(4);
		
		assertEquals(0, l.size());
	}
	
	// makes sure that reverse doesn't break anything when the list is empty
	@Test
	void testReverseEmptyList() {
		LinkedList<Integer> l = new LinkedList<Integer>();
		
		l.reverse();
		
		assertEquals("", l.toString());
	}
	
	// makes sure that reverse doesn't break anything when the list has one element
	@Test
	void testReverseSingleList() {
		LinkedList<Integer> l = new LinkedList<Integer>();
			
		l.addToFront(5);
		
		l.reverse();
			
		assertEquals("5", l.toString());
	}
	
	// makes sure that a populated list is able to be reversed
	@Test
	void testReverseUnemptyList() {
		LinkedList<Integer> l = new LinkedList<Integer>();
		
		l.addToFront(2);
		l.addToFront(1);
		l.addToBack(3);
		l.addToBack(4);
		
		l.reverse();
		
		assertEquals("4 3 2 1", l.toString());
	}
}
