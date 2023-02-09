package utilities;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

public class EquivalenceClassesTest {
	
	// this comparator means that the list consists of only integers divisible by 5 or only ones that are not
	private Comparator<Integer> setComparatorMod5Group() {
		return new Comparator<Integer>() {
			// they are equivalent if they are both multiples of 5
			public int compare(Integer x, Integer y) { 
				return x % 5 == y % 5 ? 0 : 1;
			}
		};
	}
	
	@Test
	void equivalenceClassesExistTest() {
		Comparator<Integer> compare = setComparatorMod5Group();
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(compare);
		
		assertNotNull(aClass);
		assertNotNull(aClass._comparator);
		assertNotNull(aClass._classes);	
	}
	
	@Test
	void containsInTest() {
		Comparator<Integer> compare = setComparatorMod5Group();
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(compare);
		
		for(int i=0; i<=50; i++) {
			aClass.add(i);
			assertTrue(aClass.contains(i));
		}
		
	}
	
	@Test
	void containsNotInTest() {
		Comparator<Integer> compare = setComparatorMod5Group();
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(compare);
		for(int i=0; i<=5; i++) {
			aClass.add(i);
		}
		
		assertFalse(aClass.contains(6));
	}
	
	@Test
	void containsEmptyTest() {
		Comparator<Integer> compare = setComparatorMod5Group();
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(compare);
		
		assertFalse(aClass.contains(0));
	}
	
	@Test
	void containsNullComparatorTest() {
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(null);
		
		assertFalse(aClass.contains(0));
	}
	
	@Test
	void containsNullValueTest() {
		Comparator<Integer> compare = setComparatorMod5Group();
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(compare);
		
		assertFalse(aClass.contains(null));
	}
	
	@Test
	void addTest() {
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(null);
	}
	
	@Test
	void sizeTest() {
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(null);
	}
	
	@Test
	void numClassesTest() {
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(null);
	}
	
	@Test
	void indexOfClassTest() {
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(null);
	}
	
}
