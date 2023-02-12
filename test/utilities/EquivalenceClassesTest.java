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
	void testEquivalenceClassesExist() {
		Comparator<Integer> compare = setComparatorMod5Group();
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(compare);

		assertNotNull(aClass);
		assertNotNull(aClass._comparator);
		assertNotNull(aClass._classes);
	}

	@Test
	void testContainsIn() {
		Comparator<Integer> compare = setComparatorMod5Group();
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(compare);

		for (int i = 0; i <= 50; i++) {
			aClass.add(i);
			assertTrue(aClass.contains(i));
		}
	}

	@Test
	void testContainsNotIn() {
		Comparator<Integer> compare = setComparatorMod5Group();
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(compare);
		for (int i = 0; i <= 5; i++) {
			aClass.add(i);
		}

		assertFalse(aClass.contains(6));
	}

	@Test
	void testContainsEmpty() {
		Comparator<Integer> compare = setComparatorMod5Group();
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(compare);

		assertFalse(aClass.contains(0));
	}

	@Test
	void testContainsNullComparator() {
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(null);

		assertFalse(aClass.contains(0));
	}

	@Test
	void testContainsNullValue() {
		Comparator<Integer> compare = setComparatorMod5Group();
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(compare);

		assertFalse(aClass.contains(null));
	}

	@Test
	void testAddOne() {
		Comparator<Integer> compare = setComparatorMod5Group();
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(compare);

		assertTrue(aClass.add(0));
		assertTrue(aClass.contains(0));
	}

	@Test
	void testAddSame() {
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
	void testAddNewGroup() {
		Comparator<Integer> compare = setComparatorMod5Group();
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(compare);

		assertTrue(aClass.add(1)); // group 1
		assertEquals(1, aClass.numClasses());
		assertTrue(aClass.add(2)); // group 2
		assertEquals(2, aClass.numClasses());
	}

	@Test
	void testAddSameGroup() {
		Comparator<Integer> compare = setComparatorMod5Group();
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(compare);

		assertTrue(aClass.add(1)); // group 1
		assertEquals(1, aClass.numClasses());
		assertTrue(aClass.add(6)); // group 1
		assertEquals(1, aClass.numClasses());
	}

	@Test
	void testAddNullComparator() {
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(null);

		assertFalse(aClass.add(1));
	}

	@Test
	void testAddNullValue() {
		Comparator<Integer> compare = setComparatorMod5Group();
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(compare);

		assertFalse(aClass.add(null));
	}

	@Test
	void testSizeOne() {
		Comparator<Integer> compare = setComparatorMod5Group();
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(compare);

		aClass.add(0);
		assertEquals(1, aClass.size());
	}

	@Test
	void testSizeMany() {
		Comparator<Integer> compare = setComparatorMod5Group();
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(compare);

		for (int i = 0; i <= 50; i++) {
			aClass.add(i);
			assertEquals(i + 1, aClass.size());
		}
	}

	@Test
	void testSizeOneClass() {
		Comparator<Integer> compare = setComparatorMod5Group();
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(compare);

		for (int i = 0; i <= 50; i += 5) {
			aClass.add(i);
			assertEquals(i / 5 + 1, aClass.size());
		}
	}

	@Test
	void testSizeEmpty() {
		Comparator<Integer> compare = setComparatorMod5Group();
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(compare);

		assertEquals(0, aClass.size());
	}

	@Test
	void testSizeNullComparator() {
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(null);

		aClass.add(1);
		aClass.add(2);
		assertEquals(0, aClass.size());
	}

	@Test
	void testSizeNullValue() {
		Comparator<Integer> compare = setComparatorMod5Group();
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(compare);
		aClass.add(null);

		assertEquals(0, aClass.size());
	}

	@Test
	void testNumClassesOne() {
		Comparator<Integer> compare = setComparatorMod5Group();
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(compare);

		aClass.add(0);
		assertEquals(1, aClass.numClasses());
	}

	@Test
	void testNumClassesMany() {
		Comparator<Integer> compare = setComparatorMod5Group();
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(compare);

		for (int i = 0; i <= 50; i++) {
			aClass.add(i);
			assertEquals(i >= 5 ? 5 : i + 1, aClass.numClasses());
		}
	}

	@Test
	void testNumClassesOneClass() {
		Comparator<Integer> compare = setComparatorMod5Group();
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(compare);

		for (int i = 0; i <= 50; i += 5) {
			aClass.add(i);
			assertEquals(1, aClass.numClasses());
		}
	}

	@Test
	void testNumClassesEmpty() {
		Comparator<Integer> compare = setComparatorMod5Group();
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(compare);

		assertEquals(0, aClass.numClasses());
	}

	@Test
	void testNumClassesNullComparator() {
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(null);

		aClass.add(1);
		aClass.add(2);
		assertEquals(0, aClass.numClasses());
	}

	@Test
	void testNumClassesNullValue() {
		Comparator<Integer> compare = setComparatorMod5Group();
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(compare);
		aClass.add(null);

		assertEquals(0, aClass.numClasses());
	}

	@Test
	void testIndexOfClassOneClass() {
		Comparator<Integer> compare = setComparatorMod5Group();
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(compare);

		for (int i = 0; i <= 50; i += 5) {
			aClass.add(i);
			assertEquals(0, aClass.indexOfClass(i));
		}
	}

	@Test
	void testIndexOfClassManyClasses() {
		Comparator<Integer> compare = setComparatorMod5Group();
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(compare);

		for (int i = 0; i <= 50; i++) {
			aClass.add(i);
			assertEquals(i % 5, aClass.indexOfClass(i));
		}
	}

	@Test
	void testIndexOfClassNotIn() {
		Comparator<Integer> compare = setComparatorMod5Group();
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(compare);

		aClass.add(0);
		assertEquals(-1, aClass.indexOfClass(1));
	}

	@Test
	void testIndexOfClassEmpty() {
		Comparator<Integer> compare = setComparatorMod5Group();
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(compare);

		assertEquals(-1, aClass.indexOfClass(0));
	}

	@Test
	void testIndexOfClassNullComparator() {
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(null);

		assertEquals(-1, aClass.indexOfClass(0));
	}

	@Test
	void testIndexOfClassNullValue() {
		Comparator<Integer> compare = setComparatorMod5Group();
		EquivalenceClasses<Integer> aClass = new EquivalenceClasses<Integer>(compare);
		aClass.add(null);

		assertEquals(-1, aClass.indexOfClass(null));
	}

}
