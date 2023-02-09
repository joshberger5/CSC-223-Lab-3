package utilities;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

public class EquivalenceClassesTest {
	
	private Comparator<Integer> setComparatorMod5Group() {
		return new Comparator<Integer>() {
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
	void addOneTest() {
		Comparator<Integer> compare = setComparatorMod5Group();
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(compare);
		
		assertTrue(aClass.add(0));
		assertTrue(aClass.contains(0));
	}
	
	@Test
	void addSameTest() {
		Comparator<Integer> compare = setComparatorMod5Group();
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(compare);
		
		assertTrue(aClass.add(0));
		assertEquals(1, aClass.numClasses());
		assertTrue(aClass.add(1));
		assertEquals(2, aClass.numClasses());
		assertFalse(aClass.add(1));
		assertEquals(2, aClass.numClasses());
	}
	
	@Test
	void addNewGroupTest() {
		Comparator<Integer> compare = setComparatorMod5Group();
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(compare);
		
		assertTrue(aClass.add(1)); // group 1
		assertEquals(1, aClass.numClasses());
		assertTrue(aClass.add(2)); // group 2
		assertEquals(2, aClass.numClasses());
	}
	
	@Test
	void addSameGroupTest() {
		Comparator<Integer> compare = setComparatorMod5Group();
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(compare);
		
		assertTrue(aClass.add(1)); // group 1
		assertEquals(1, aClass.numClasses());
		assertTrue(aClass.add(6)); // group 1
		assertEquals(1, aClass.numClasses());
	}
	
	@Test
	void addNullComparatorTest() {
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(null);
		
		assertFalse(aClass.add(1));
	}
	
	@Test
	void addNullValueTest() {
		Comparator<Integer> compare = setComparatorMod5Group();
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(compare);
		
		assertFalse(aClass.add(null));
	}
	
	@Test
	void sizeOneTest() {
		Comparator<Integer> compare = setComparatorMod5Group();
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(compare);
		
		aClass.add(0);
		assertEquals(1, aClass.size());
	}
	
	@Test
	void sizeManyTest() {
		Comparator<Integer> compare = setComparatorMod5Group();
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(compare);
		
		for(int i=0; i<=50; i++) {
			aClass.add(i);
			assertEquals(i+1, aClass.size());
		}
	}
	
	@Test
	void sizeOneClassTest() {
		Comparator<Integer> compare = setComparatorMod5Group();
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(compare);
		
		for(int i=0; i<=50; i+=5) {
			aClass.add(i);
			assertEquals(i/5 + 1, aClass.size());
		}
	}
	
	@Test
	void sizeEmptyTest() {
		Comparator<Integer> compare = setComparatorMod5Group();
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(compare);
		
		assertEquals(0, aClass.size());
	}
	
	@Test
	void sizeNullComparatorTest() {
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(null);
		
		aClass.add(1);
		aClass.add(2);
		assertEquals(0, aClass.size());
	}
	
	@Test
	void sizeNullValueTest() {
		Comparator<Integer> compare = setComparatorMod5Group();
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(compare);
		aClass.add(null);
		
		assertEquals(0, aClass.size());
	}
	
	@Test
	void numClassesOneTest() {
		Comparator<Integer> compare = setComparatorMod5Group();
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(compare);
		
		aClass.add(0);
		assertEquals(1, aClass.numClasses());
	}
	
	@Test
	void numClassesManyTest() {
		Comparator<Integer> compare = setComparatorMod5Group();
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(compare);
		
		for(int i=0; i<=50; i++) {
			aClass.add(i);
			assertEquals(i>=5 ? 5 : i+1, aClass.numClasses());
		}
	}
	
	@Test
	void numClassesOneClassTest() {
		Comparator<Integer> compare = setComparatorMod5Group();
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(compare);
		
		for(int i=0; i<=50; i+=5) {
			aClass.add(i);
			assertEquals(1, aClass.numClasses());
		}
	}
	
	@Test
	void numClassesEmptyTest() {
		Comparator<Integer> compare = setComparatorMod5Group();
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(compare);
		
		assertEquals(0, aClass.numClasses());
	}
	
	@Test
	void numClassesNullComparatorTest() {
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(null);
		
		aClass.add(1);
		aClass.add(2);
		assertEquals(0, aClass.numClasses());
	}
	
	@Test
	void numClassesNullValueTest() {
		Comparator<Integer> compare = setComparatorMod5Group();
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(compare);
		aClass.add(null);
		
		assertEquals(0, aClass.numClasses());
	}
	
	@Test
	void indexOfClassOneClassTest() {
		Comparator<Integer> compare = setComparatorMod5Group();
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(compare);
		
		for(int i=0; i<=50; i+=5) {
			aClass.add(i);
			assertEquals(0, aClass.indexOfClass(i));
		}
	}
	
	@Test
	void indexOfClassManyClassesTest() {
		Comparator<Integer> compare = setComparatorMod5Group();
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(compare);
		
		for(int i=0; i<=50; i++) {
			aClass.add(i);
			assertEquals(i%5, aClass.indexOfClass(i));
		}
	}
	
	@Test
	void indexOfClassNotInTest() {
		Comparator<Integer> compare = setComparatorMod5Group();
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(compare);
		
		aClass.add(0);
		assertEquals(-1, aClass.indexOfClass(1));
	}
	
	@Test
	void indexOfClassEmptyTest() {
		Comparator<Integer> compare = setComparatorMod5Group();
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(compare);
		
		assertEquals(-1, aClass.indexOfClass(0));
	}
	
	@Test
	void indexOfClassNullComparatorTest() {
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(null);
		
		assertEquals(-1, aClass.indexOfClass(0));
	}
	
	@Test
	void indexOfClassNullValueTest() {
		Comparator<Integer> compare = setComparatorMod5Group();
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(compare);
		aClass.add(null);
		
		assertEquals(-1, aClass.indexOfClass(null));
	}
	
}
