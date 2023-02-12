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
			// they are equivalent if they both are palindromes or both aren't
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
	
	// this comparator mean that the list consists of only Strings that are capitalized or only ones that are not
	private Comparator<String> setComparatorCapitalized() {
		return new Comparator<String>() {
			// they are equivalent if they are both capitalized or both not
			public int compare(String a, String b) { 
				return Character.isUpperCase(a.charAt(0)) == Character.isUpperCase(b.charAt(0)) ? 0 : 1;
			}
		};
	}
	
	// makes sure the constructor works and everything is empty
	@Test
	void testConstructor() {
		Comparator<Integer> f = setComparatorDivBy5();
		LinkedEquivalenceClass e = new LinkedEquivalenceClass<Integer>(f);
		
		assertEquals("", e.toString());
	}
	
	// makes sure that isEmpty returns true when the list is empty
	@Test
	void testIsEmptyWhenEmpty() {
		Comparator<String> c = setComparatorCapitalized();
		LinkedEquivalenceClass e = new LinkedEquivalenceClass<String>(c);
		
		assertTrue(e.isEmpty());
	}
	
	// makes sure that isEmpty returns false when the list has a canonical
	@Test
	void testIsEmptyWhenUnempty() {
		Comparator<String> c = setComparatorCapitalized();
		LinkedEquivalenceClass e = new LinkedEquivalenceClass<String>(c);
		
		e.add("Whatever");
		
		assertFalse(e.isEmpty());
	}
	
	// makes sure that add handles null input
	@Test
	void testAddNull() {
		Comparator<Integer> f = setComparatorDivBy5();
		LinkedEquivalenceClass e = new LinkedEquivalenceClass<Integer>(f);
		
		assertFalse(e.add(null));
	}	
	
	// makes sure that when the list is empty and you call add, it adds as the canonical
	// and returns true
	@Test
	void testAddFirst() {
		Comparator<Integer> f = setComparatorDivBy5();
		LinkedEquivalenceClass e = new LinkedEquivalenceClass<Integer>(f);
		
		assertTrue(e.add(5));
		assertEquals("5", e.toString());
	}
	
	// makes sure that adding something that belongs when there is just a canonical:
	// adds it to the rest of the list
	// and returns true
	@Test
	void testAddSecondWhenBelongs() {
		Comparator<Integer> f = setComparatorDivBy5();
		LinkedEquivalenceClass e = new LinkedEquivalenceClass<Integer>(f);
		
		e.add(5);
		
		assertTrue(e.add(10));
		assertEquals("5 | 10", e.toString());
	}
	
	// makes sure that adding something that doesn't belong when there is just a canonical
	// adds it to the rest of the list
	// and returns true
	@Test
	void testAddSecondWhenDoesntBelong() {
		Comparator<Integer> f = setComparatorDivBy5();
		LinkedEquivalenceClass e = new LinkedEquivalenceClass<Integer>(f);
			
		e.add(5);
		
		assertFalse(e.add(6));
		assertEquals("5", e.toString());
	}
	
	// makes sure that adding a repeat of the canonical doesn't work
	@Test
	void testAddCanonicalRepeat() {
		Comparator<Integer> f = setComparatorDivBy5();
		LinkedEquivalenceClass e = new LinkedEquivalenceClass<Integer>(f);
		
		e.add(5);
		e.add(50);
		e.add(40);
		e.add(30);
		e.add(20);
		e.add(10);
		
		assertFalse(e.add(5));
		assertEquals("5 | 10 20 30 40 50", e.toString());
	}
	
	// makes sure that adding a repeat to something in the rest of the list doesn't work
	@Test
	void testAddRepeat() {
		Comparator<Integer> f = setComparatorDivBy5();
		LinkedEquivalenceClass e = new LinkedEquivalenceClass<Integer>(f);
			
		e.add(5);
		e.add(50);
		e.add(40);
		e.add(30);
		e.add(20);
		e.add(10);
			
		assertFalse(e.add(50));
		assertEquals("5 | 10 20 30 40 50", e.toString());
	}
	
	// makes sure that canonical returns null when the list is empty
	@Test
	void testCanonicalWhenEmpty() {
		Comparator<Integer> f = setComparatorDivBy5();
		LinkedEquivalenceClass e = new LinkedEquivalenceClass<Integer>(f);
		
		assertEquals(null, e.canonical());
	}
	
	// makes sure that canonical returns the canonical and not other elements
	@Test
	void testCanonicalWhenUempty() {
		Comparator<Integer> f = setComparatorDivBy5();
		LinkedEquivalenceClass e = new LinkedEquivalenceClass<Integer>(f);
			
		e.add(5);
		e.add(10);
		e.add(20);
		e.add(30);
		
		assertEquals(5, e.canonical());
	}
	
	// makes sure that calling DemoteAndSetCanonical with an empty list returns false
	// since there's nothing to demote
	@Test
	void testDemoteAndSetCanonicalEmpty() {
		Comparator<Integer> f = setComparatorDivBy5();
		LinkedEquivalenceClass e = new LinkedEquivalenceClass<Integer>(f);
		
		assertFalse(e.demoteAndSetCanonical(5));
	}
	
	// makes sure that it works when there is just a canonical and the new one belongs
	@Test
	void testDemoteAndSetCanonicalJustCanonicalAndBelongs() {
		Comparator<Integer> f = setComparatorDivBy5();
		LinkedEquivalenceClass e = new LinkedEquivalenceClass<Integer>(f);
		
		e.add(10);
		
		assertTrue(e.demoteAndSetCanonical(5));
		assertEquals("5 | 10", e.toString());
	}
	
	// makes sure that it works when there is a canonical and stuff in the rest and the new one belongs
	@Test
	void testDemoteAndSetCanonicalMoreThanJustCanonicalAndBelongs() {
		Comparator<Integer> f = setComparatorDivBy5();
		LinkedEquivalenceClass e = new LinkedEquivalenceClass<Integer>(f);
			
		e.add(10);
		e.add(15);
		
		assertTrue(e.demoteAndSetCanonical(5));
		assertEquals("5 | 10 15", e.toString());
	}
	
	// makes sure that it returns false when it doesn't belong
	@Test
	void testDemoteAndSetCanonicalDoesntBelong() {
		Comparator<Integer> f = setComparatorDivBy5();
		LinkedEquivalenceClass e = new LinkedEquivalenceClass<Integer>(f);
			
		e.add(10);
		e.add(15);
		
		assertFalse(e.demoteAndSetCanonical(6));
		assertEquals("10 | 15", e.toString());
	}
	
	// makes sure that it handles null input
	@Test
	void testDemoteAndSetCanonicalNull() {
		Comparator<Integer> f = setComparatorDivBy5();
		LinkedEquivalenceClass e = new LinkedEquivalenceClass<Integer>(f);
			
		assertFalse(e.demoteAndSetCanonical(null));
	}
	
	// makes sure that clear non-canonical doesn't break when its empty
	@Test
	void testClearNonCanonicalWhenEmpty() {
		Comparator<String> p = setComparatorPalindrome();
		LinkedEquivalenceClass e = new LinkedEquivalenceClass<String>(p);
		
		e.clearNonCanonical();
		assertEquals("", e.toString());
	}
	
	// makes sure that clear non-canonical:
	// clears the rest of the list
	// doesn't clear the canonical
	@Test
	void testClearNonCanonicalWhenPopulated() {
		Comparator<String> p = setComparatorPalindrome();
		LinkedEquivalenceClass e = new LinkedEquivalenceClass<String>(p);
		
		e.add("deified");
		e.add("civic");
		e.add("refer");
		e.clearNonCanonical();
		
		assertEquals("deified", e.toString());
	}
	
	// makes sure that clear doesn't break when the list is empty
	@Test
	void testClearWhenEmpty() {
		Comparator<String> p = setComparatorPalindrome();
		LinkedEquivalenceClass e = new LinkedEquivalenceClass<String>(p);
		
		e.clear();
		assertEquals("", e.toString());
	}
	
	// makes sure that clear:
	// clears the rest of the list
	// clears the canonical
	@Test
	void testClearWhenPopulated() {
		Comparator<String> p = setComparatorPalindrome();
		LinkedEquivalenceClass e = new LinkedEquivalenceClass<String>(p);
			
		e.add("pop");
		e.add("testset");
		e.add("noon");
		e.clear();
			
		assertEquals("", e.toString());
	}
	
	// makes sure that checking the size when it is empty returns 0
	@Test
	void testSizeWhenEmpty() {
		Comparator<String> p = setComparatorPalindrome();
		LinkedEquivalenceClass e = new LinkedEquivalenceClass<String>(p);
		
		assertEquals(0, e.size());
	}
	
	// makes sure that checking the size when there is only a canonical returns 1
	@Test
	void testSizeForJustCanonical() {
		Comparator<String> p = setComparatorPalindrome();
		LinkedEquivalenceClass e = new LinkedEquivalenceClass<String>(p);
		
		e.add("aibohphobia");
		
		assertEquals(1, e.size());
	}
	
	// makes sure that checking the size works when there is more than a canonical
	@Test
	void testSizePopulated() {
		Comparator<String> p = setComparatorPalindrome();
		LinkedEquivalenceClass e = new LinkedEquivalenceClass<String>(p);
		
		e.add("aibohphobia");
		e.add("pop");
		e.add("redder");
		
		assertEquals(3, e.size());
	}
	
	// makes sure that size increases after demote and set canonical
	@Test
	void testSizeDemoteAndSetCanonical() {
		Comparator<String> p = setComparatorPalindrome();
		LinkedEquivalenceClass e = new LinkedEquivalenceClass<String>(p);
		
		e.add("aibohphobia");
		e.add("pop");
		e.add("redder");
		e.demoteAndSetCanonical("wow");
		
		assertEquals(4, e.size());
	}
	
	// makes sure that size decreases after removing the canonical
	@Test
	void testSizeRemoveCanonical() {
		Comparator<String> p = setComparatorPalindrome();
		LinkedEquivalenceClass e = new LinkedEquivalenceClass<String>(p);
		
		e.add("aibohphobia");
		e.add("pop");
		e.add("redder");
		e.demoteAndSetCanonical("wow");
		e.removeCanonical();
		
		assertEquals(3, e.size());
	}
	
	// makes sure that size decreases after removing a non-canonical
	@Test
	void testSizeRemove() {
		Comparator<String> p = setComparatorPalindrome();
		LinkedEquivalenceClass e = new LinkedEquivalenceClass<String>(p);
		
		e.add("aibohphobia");
		e.add("pop");
		e.add("redder");
		e.demoteAndSetCanonical("wow");
		e.removeCanonical();
		e.remove("pop");
		
		assertEquals(2, e.size());
	}
	
	// makes sure if there is nothing in the list then belongs should automatically return true
	@Test
	void testBelongsEmpty() {
		Comparator<String> p = setComparatorPalindrome();
		LinkedEquivalenceClass e = new LinkedEquivalenceClass<String>(p);
		
		assertTrue(e.belongs("stats"));
	}
	
	// makes sure belongs returns true for equivalent elements
	@Test
	void testBelongsEquivalent() {
		Comparator<String> p = setComparatorPalindrome();
		LinkedEquivalenceClass e = new LinkedEquivalenceClass<String>(p);
			
		e.add("stats");
		
		assertTrue(e.belongs("kayak"));
	}
	
	// makes sure belongs returns false for elements that aren't equivalent
	@Test
	void testBelongsNotEquivalent() {
		Comparator<String> p = setComparatorPalindrome();
		LinkedEquivalenceClass e = new LinkedEquivalenceClass<String>(p);
			
		e.add("stats");
		
		assertFalse(e.belongs("hello"));
	}
	
	// makes sure that belongs returns false for null input
	@Test
	void testBelongsNull() {
		Comparator<String> p = setComparatorPalindrome();
		LinkedEquivalenceClass e = new LinkedEquivalenceClass<String>(p);
		
		assertFalse(e.belongs(null));
	}
	
	// makes sure that contains returns false for null
	@Test
	void testContainsNull() {
		Comparator<String> p = setComparatorPalindrome();
		LinkedEquivalenceClass e = new LinkedEquivalenceClass<String>(p);
		
		assertFalse(e.contains(null));
	}
	
	// makes sure that contains returns false when the list is empty
	@Test
	void testContainsEmpty() {
		Comparator<String> p = setComparatorPalindrome();
		LinkedEquivalenceClass e = new LinkedEquivalenceClass<String>(p);
		
		assertFalse(e.contains("madam"));
	}
	
	// makes sure that contains returns true when the input matches the canonical
	@Test
	void testContainsCanonicalMatch() {
		Comparator<String> p = setComparatorPalindrome();
		LinkedEquivalenceClass e = new LinkedEquivalenceClass<String>(p);
		
		e.add("madam");
		
		assertTrue(e.contains("madam"));
	}
	
	// makes sure that contains returns true when the input matches a non-canonical
	@Test
	void testContainsMatch() {
		Comparator<String> p = setComparatorPalindrome();
		LinkedEquivalenceClass e = new LinkedEquivalenceClass<String>(p);
			
		e.add("madam");
		e.add("rotator");
			
		assertTrue(e.contains("rotator"));
	}
	
	// makes sure that contains returns false when the input:
	// doens't match the canonical or a non-canonical
	// but it does belong in the list
	@Test
	void testContainsDoesntMatchButBelongs() {
		Comparator<String> p = setComparatorPalindrome();
		LinkedEquivalenceClass e = new LinkedEquivalenceClass<String>(p);
			
		e.add("madam");
		e.add("rotator");
			
		assertFalse(e.contains("yay"));
	}
	
	// makes sure that contains returns false when the input:
	// doens't match the canonical or a non-canonical
	// and it doesn't belong in the list
	@Test
	void testContainsDoesntMatchDoesntBelong() {
		Comparator<String> p = setComparatorPalindrome();
		LinkedEquivalenceClass e = new LinkedEquivalenceClass<String>(p);
			
		e.add("madam");
		e.add("rotator");
			
		assertFalse(e.contains("hello"));
	}
	
	// makes sure that remove returns false for null input
	@Test
	void testRemoveNull() {
		Comparator<String> c = setComparatorCapitalized();
		LinkedEquivalenceClass e = new LinkedEquivalenceClass<String>(c);
		
		assertFalse(e.remove(null));
	}
	
	// makes sure that remove returns false when the list is empty
	@Test
	void testRemoveEmpty() {
		Comparator<String> c = setComparatorCapitalized();
		LinkedEquivalenceClass e = new LinkedEquivalenceClass<String>(c);

		assertFalse(e.remove("Hello"));
		assertEquals("", e.toString());
	}
	
	// makes sure that remove returns false when trying to remove canonical
	@Test
	void testRemovePassingInCanonical() {
		Comparator<String> c = setComparatorCapitalized();
		LinkedEquivalenceClass e = new LinkedEquivalenceClass<String>(c);
		
		e.add("Hello");
		
		assertFalse(e.remove("Hello"));
		assertEquals("Hello", e.toString());
	}
	
	// makes sure that remove returns false when trying to remove input that doesn't belong
	@Test
	void testRemoveDoesntBelong() {
		Comparator<String> c = setComparatorCapitalized();
		LinkedEquivalenceClass e = new LinkedEquivalenceClass<String>(c);
		
		e.add("Hello");

		assertFalse(e.remove("goodbye"));
		assertEquals("Hello", e.toString());
	}
	
	// makes sure that remove returns false when trying to remove input that doesn't belong
	@Test
	void testRemoveBelongs() {
		Comparator<String> c = setComparatorCapitalized();
		LinkedEquivalenceClass e = new LinkedEquivalenceClass<String>(c);
			
		e.add("Hello");

		assertFalse(e.remove("goodbye"));
		assertEquals("Hello", e.toString());
	}
	
	// makes sure that remove returns true when trying to remove a non-canonical
	@Test
	void testRemoveNonCanonical() {
		Comparator<String> c = setComparatorCapitalized();
		LinkedEquivalenceClass e = new LinkedEquivalenceClass<String>(c);
		
		e.add("Hello");
		e.add("Goodbye");
		
		assertTrue(e.remove("Goodbye"));
		assertEquals("Hello", e.toString());
	}
	
	// makes sure that remove canonical returns false when the list is empty
	@Test
	void testRemoveCanonicalWhenEmpty() {
		Comparator<String> c = setComparatorCapitalized();
		LinkedEquivalenceClass e = new LinkedEquivalenceClass<String>(c);
		
		assertFalse(e.removeCanonical());
	}
	
	// makes sure that remove canonical returns false when there is only the canonical
	@Test
	void testRemoveCanonicalWithJustCanonical() {
		Comparator<String> c = setComparatorCapitalized();
		LinkedEquivalenceClass e = new LinkedEquivalenceClass<String>(c);
			
		e.add("Water");
		
		assertFalse(e.removeCanonical());
	}
	
	// makes sure that remove canonical returns true when there is stuff to replace it
	@Test
	void testRemoveCanonicalWhenPopulated() {
		Comparator<String> c = setComparatorCapitalized();
		LinkedEquivalenceClass e = new LinkedEquivalenceClass<String>(c);
				
		e.add("Water");
		e.add("Fire");
			
		assertTrue(e.removeCanonical());
		assertEquals("Fire", e.toString());
	}
}
