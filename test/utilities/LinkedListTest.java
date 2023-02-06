package utilities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class LinkedListTest {
	@Test
	void testConstructor() {
		LinkedList<Integer> l = new LinkedList<Integer>();
		
		assertEquals(0, l.size());
		assertTrue(l.isEmpty());
	}
	
	@Test
	void testAddToFront() {
		LinkedList<Integer> l = new LinkedList<Integer>();
		
		// checks to see if it adds an element to the front of an empty list
		l.addToFront(5);
		assertEquals("5", l.toString());
		
		// checks to see if it adds null
		l.addToFront(null);
		assertEquals("5", l.toString());
		
		// checks to see if it adds an element to the front of an unempty list
		l.addToFront(6);
		assertEquals("6 5", l.toString());
		
		// checks to see if adding to the front works after clearing the list
		l.clear();
		l.addToFront(6);
		assertEquals("6", l.toString());
	}
	
	@Test
	void testAddToBack() {
		LinkedList<Integer> l = new LinkedList<Integer>();
		
		// checks to see if it adds an element to the back of an empty list
		l.addToBack(5);
		assertEquals("5", l.toString());
		
		// checks to see if it adds null
		l.addToBack(null);
		assertEquals("5", l.toString());
		
		// checks to see if it adds an element to the back of an unempty list
		l.addToBack(6);
		assertEquals("5 6", l.toString());
		
		// checks to see if adding to the back works after clearing the list
		l.clear();
		l.addToBack(6);
		assertEquals("6", l.toString());
	}
	
	@Test
	void testContains() {
		LinkedList<Integer> l = new LinkedList<Integer>();
		
		// checks if it says it contains an element in the list
		l.addToFront(5);
		assertTrue(l.contains(5));
		
		// check if it says it doesn't contain an element not in the list
		assertFalse(l.contains(6));
		
		// check if it handles null
		assertFalse(l.contains(null));
	}
	
	@Test
	void testRemove() {
		LinkedList<Integer> l = new LinkedList<Integer>();
		
		l.addToFront(4);
		l.addToFront(3);
		l.addToFront(2);
		l.addToFront(1);
		
		l.remove(4);
		assertEquals("1 2 3", l.toString());
	}
}
