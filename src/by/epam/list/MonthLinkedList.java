package by.epam.list;

public class MonthLinkedList implements SortedLinkedList {

	private MonthNode head;
	private MonthNode tail;

	private int size;

	public MonthLinkedList() {
		head = null;
		tail = null;
		size = 0;
	}

	private Month checkAndgetMonthValue(String monthString) {
		if (monthString == null) {
			throw new IllegalArgumentException("monthString is null");
		}

		try {
			return Month.valueOf(monthString.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Wrong monthString value: " + monthString, e);
		}
	}

	@Override
	public void add(String monthString) {
		Month month = checkAndgetMonthValue(monthString);
		add(month);
	}

	private void add(Month month) {
		final MonthNode tempLast = tail;
		final MonthNode newMonthNode = new MonthNode(month, tempLast, null);

		tail = newMonthNode;

		if (tempLast == null) {
			head = newMonthNode;
		} else {
			tempLast.nextNode = newMonthNode;
		}
		size++;
	}

	@Override
	public String get(int index) {
		if (index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException("Wrong index:" + index);
		}
		Month month = getMonth(index);
		return month.name();
	}

	private Month getMonth(int index) {

		MonthNode temp;

		// if index is before the middle
		if (size / 2 > index) {
			temp = head;

			for (int i = 0; i < index; i++) {
				temp = temp.nextNode;
			}
			// after the middle
		} else {
			temp = tail;

			for (int i = size - 1; i > index; i--) {
				temp = temp.privNode;
			}
		}
		return temp.thisMonth;
	}

	@Override
	public boolean remove(String monthString) {
		Month month = checkAndgetMonthValue(monthString);
		return remove(month);
	}

	private boolean remove(Month month) {
		if (month == null) {
			throw new IllegalArgumentException("month is null");
		}
		boolean result = false;
		MonthNode temp = head;

		for (int i = 0; i < size; i++) {
			Month tempMonth = temp.thisMonth;
			if (month.equals(tempMonth)) {
				relink(temp);
				size--;
				result = true;
				break;
			} else {
				temp = temp.nextNode;
			}
		}
		return result;
	}

	private void relink(MonthNode removingNode) {

		if (removingNode.equals(head)) {
			MonthNode nextNode = head.nextNode;
			head = nextNode;
			if (nextNode != null) {
				nextNode.privNode = null;
			}
		} else {
			if (removingNode.equals(tail)) {
				MonthNode privNode = tail.privNode;
				tail = privNode;
				tail.nextNode = null;
			}

			else {
				MonthNode privNode = removingNode.privNode;
				MonthNode nextNode = removingNode.nextNode;

				privNode.nextNode = nextNode;
				nextNode.privNode = privNode;
			}
		}
	}

	private static class MonthNode {

		Month thisMonth;
		MonthNode privNode;
		MonthNode nextNode;

		public MonthNode(Month thisMonth, MonthNode privNode, MonthNode nextNode) {
			super();
			this.thisMonth = thisMonth;
			this.privNode = privNode;
			this.nextNode = nextNode;
		}

		@Override
		public String toString() {
			return "MonthNode [thisMonth=" + thisMonth + ", privNode=" + privNode + ", nextNode=" + nextNode + "]";
		}

	}

	@Override
	public String toString() {
		StringBuilder resultString = new StringBuilder();

		MonthNode temp = head;
		for (int i = 0; i < size; i++) {
			resultString.append(temp.thisMonth.toString());
			temp = temp.nextNode;
		}
		return resultString.toString();
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public SortedLinkedList sort() {

		Month temp;

		for (int i = 0; i < size - 1; i++) {
			MonthNode currentNode = head;
			boolean isNotChanged = true;

			for (int j = 0; j < size - 1; j++) {
				if (currentNode.thisMonth.compareTo(currentNode.nextNode.thisMonth) > 1) {
					isNotChanged = false;

					temp = currentNode.thisMonth;
					currentNode.thisMonth = currentNode.nextNode.thisMonth;
					currentNode.nextNode.thisMonth = temp;
				}
			}
			if (isNotChanged) {
				break;
			}
		}
		return this;
	}
}
