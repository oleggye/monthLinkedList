package by.epam.list;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import by.epam.list.MonthLinkedList;
import by.epam.list.SortedLinkedList;

public class MonthLinkedListTest {

	private SortedLinkedList linkedList;

	@Before
	public void setUp() throws Exception {
		linkedList = new MonthLinkedList();
	}

	@After
	public void tearDown() throws Exception {
		linkedList = null;
	}

	@Test
	public void testZeroSize() {
		int size = linkedList.getSize();

		Assertions.assertThat(size).isEqualTo(0);
	}

	@Test
	public void testAddOne() {

		int expectedSize = 1;

		linkedList.add("April");

		Assertions.assertThat(linkedList.getSize()).isEqualTo(expectedSize);
	}

	@Test
	public void testAddTwo() {

		int expectedSize = 2;

		linkedList.add("April");
		linkedList.add("August");

		Assertions.assertThat(linkedList.getSize()).isEqualTo(expectedSize);
	}

	@Test
	public void testAddThree() {

		int expectedSize = 3;

		linkedList.add("April");
		linkedList.add("August");
		linkedList.add("September");

		Assertions.assertThat(linkedList.getSize()).isEqualTo(expectedSize);
	}

	@Test
	public void testAddOneAndGetByIndexOne() {

		int expectedSize = 1;
		String expectedMonth = "April";

		linkedList.add(expectedMonth);
		String getedMonth = linkedList.get(0);

		Assertions.assertThat(linkedList.getSize()).isEqualTo(expectedSize);
		Assertions.assertThat(getedMonth).isNotNull();
		Assertions.assertThat(getedMonth).isEqualToIgnoringCase(expectedMonth);
	}

	@Test
	public void testAddOneAndRemove() {

		int expectedSize = 0;
		String month = "April";

		linkedList.add(month);
		linkedList.remove(month);

		Assertions.assertThat(linkedList.getSize()).isEqualTo(expectedSize);
	}

	@Test
	public void testAddTwoAndRemoveFirst() {
		String removingMonth = "April";
		String remainingMonth = "August";

		linkedList.add(removingMonth);
		linkedList.add(remainingMonth);

		Assertions.assertThat(linkedList.getSize()).isEqualTo(2);
		linkedList.remove(removingMonth);
		Assertions.assertThat(linkedList.getSize()).isEqualTo(1);

		String recivedMonth = linkedList.get(0);
		Assertions.assertThat(recivedMonth).isNotNull();
		Assertions.assertThat(recivedMonth).isEqualToIgnoringCase(remainingMonth);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRemoveNullParam() {
		linkedList.remove(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddNullParam() {
		linkedList.add(null);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetByNegativeOneIndex() {
		linkedList.get(-1);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetByNegativeTwoIndex() {
		linkedList.get(-2);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetByZeroIndexWhenIsEmptyReturnNull() {
		String month = linkedList.get(0);
		System.out.println(month);
	}

	@Test
	public void testSortTwo() {

		int expectedSize = 2;

		String month2 = "December";
		String month1 = "January";

		linkedList.add(month2);
		linkedList.add(month1);

		Assertions.assertThat(linkedList.getSize()).isEqualTo(expectedSize);

		linkedList = linkedList.sort();

		Assertions.assertThat(linkedList.getSize()).isEqualTo(expectedSize);

	}

	@Test
	public void testSortThree() {

		int expectedSize = 3;

		String month3 = "December";
		String month1 = "January";
		String month2 = "February";

		linkedList.add(month3);
		linkedList.add(month1);
		linkedList.add(month2);

		Assertions.assertThat(linkedList.getSize()).isEqualTo(expectedSize);

		linkedList = linkedList.sort();

		Assertions.assertThat(linkedList.getSize()).isEqualTo(expectedSize);
	}
}