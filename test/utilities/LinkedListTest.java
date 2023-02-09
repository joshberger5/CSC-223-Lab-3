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
		
		// add some stuff to the list
		l.addToFront(3);
		l.addToFront(2);
		l.addToFront(1);
		l.addToBack(4);
		
		// check if it removes the last element
		l.remove(4);
		assertEquals("1 2 3", l.toString());
		l.addToBack(4);
		
		// check if it removes the first element
		l.remove(1);
		assertEquals("2 3 4", l.toString());
		l.addToFront(1);
		
		// checks to see if it handles null
		assertFalse(l.remove(null));
		assertEquals("1 2 3 4", l.toString());
		
		// checks to see if it handles an element not in the list
		assertFalse(l.remove(5));
		assertEquals("1 2 3 4", l.toString());
		
	}
	
	@Test
	void testSize() {
		LinkedList<Integer> l = new LinkedList<Integer>();
		
		// check that it returns that it has a size of 0 initially
		assertEquals(0, l.size());
		
		// add some stuff to the list
		l.addToFront(3);
		l.addToFront(2);
		l.addToFront(1);
		l.addToBack(4);
		
		// checks that the size was properly updated after both add functions
		assertEquals(4, l.size());
		
		// check that it works after remove
		l.remove(3);
		assertEquals(3, l.size());
		
		// checks that it works after clear
		l.clear();
		assertEquals(0, l.size());
	}
	
	@Test
	void testReverse() {
		LinkedList<Integer> l = new LinkedList<Integer>();
		
		l.addToFront(3);
		l.addToFront(2);
		l.addToFront(1);
		l.addToBack(4);
		
		l.reverse();
		assertEquals("4 3 2 1", l.toString());
	}
}
