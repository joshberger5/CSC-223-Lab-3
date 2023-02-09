package utilities;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

public class LinkedEquivalenceClassTest {
	// this comparator means that the list consists of only integers divisible by 5 or only ones that are not
	private Comparator<Integer> setComparatorDivBy5() {
		return new Comparator<Integer>() {
			// they are equivalent if they are both multiples of 5
			public int compare(Integer x, Integer y) { 
				return x % 5 == 0 && y % 5 == 0 ? 0 : 1;
			}
		};
	}
	
	// this comparator mean that the list consists of only Strings that are palindromes or only ones that are not
	private Comparator<String> setComparatorPalindrome() {
		return new Comparator<String>() {
			// they are equivalent if they are both multiples of 5
			public int compare(String a, String b) { 
				return isPalindrome(a, 0) == isPalindrome(b, 0) ? 0 : 1;
			}
			
			// checks if the string is a palindrome
			private boolean isPalindrome(String s, int index) {
				if (index >= s.length()) return true;
				if (s.charAt(index) != s.charAt(s.length()-index-1)) return false;
				return isPalindrome(s, index+1);
			}
		};
	}
	
	
	@Test
	void testConstructor() {
		Comparator<Integer> c = setComparatorDivBy5();
		LinkedEquivalenceClass e = new LinkedEquivalenceClass<Integer>(c);
		
		// makes sure everything is empty
		assertEquals(null, e.canonical());
		assertTrue(e.isEmpty());
		assertEquals(0, e.size());
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
		
		// adding a second element to rest
		e.add(15);
		assertEquals("5 | 15 10", e.toString());
		
		// check that it prevents adding something to the list that equals the canonical
		e.add(5);
		assertEquals("5 | 15 10", e.toString());
		
		// check that it prevents adding something to the list that isn't equalivalent
		e.add(6);
		assertEquals("5 | 15 10", e.toString());
	}
	
	@Test 
	void testClearNonCanonical() {
		Comparator<String> p = setComparatorPalindrome();
		LinkedEquivalenceClass e = new LinkedEquivalenceClass<String>(p);
		
		// clear the rest of the list when it isn't populated yet
		e.add("deified");
		e.clearNonCanonical();
		assertEquals("deified", e.toString());
		
		// clear the rest of the list when it is populated
		e.add("civic");
		e.add("refer");
		e.clearNonCanonical();
		assertEquals("deified", e.toString());
	}
	
	@Test
	void testClear() {
		Comparator<String> p = setComparatorPalindrome();
		LinkedEquivalenceClass e = new LinkedEquivalenceClass<String>(p);
		
		// clear everything when there isn't anything there
		e.clear();
		assertEquals("", e.toString());
		
		// clear everything when there is stuff there
		e.add("pop");
		e.add("testset");
		e.add("noon");
		e.clear();
		assertEquals("", e.toString());
	}
	
	@Test 
	void testSize() {
		Comparator<String> p = setComparatorPalindrome();
		LinkedEquivalenceClass e = new LinkedEquivalenceClass<String>(p);
		
		// already tested size when its empty so I'll skip it here
		// checks the size is 1 when there is only a canonical element
		e.add("aibohphobia");
		assertEquals(1, e.size());
		
		// checks the size goes up to 3 when you add 2 to the rest of the list
		e.add("poop");
		e.add("redder");
		assertEquals(3, e.size());
		
		// checks the size goes up 1 after demoteAndSetCanonical
		e.demoteAndSetCanonical("wow");
		assertEquals(4, e.size());
		
		// checks the size goes down 1 after removing the canonical
		e.removeCanonical();
		assertEquals(3, e.size());
		
		// checks the size goes down 1 after removing an element
		e.remove("poop");
		assertEquals(2, e.size());
	}
	
	@Test
	void testBelongs() {
		Comparator<String> p = setComparatorPalindrome();
		LinkedEquivalenceClass e = new LinkedEquivalenceClass<String>(p);
		
		// if there is nothing in the list, then anything should belong
		assertTrue(e.belongs("aibohphobia"));
		e.add("aibohphobia");
		
		// makes sure that it returns true when checking the belonging of an equivalent element
		assertTrue(e.belongs("kayak"));
		
		// makes sure that it returns false when checking the belonging of a non-equivalent element
		assertFalse(e.belongs("hello"));
		
		// makes sure it returns false for null
		assertFalse(e.belongs(null));
	}
}
