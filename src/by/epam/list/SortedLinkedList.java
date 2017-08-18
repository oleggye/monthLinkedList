package by.epam.list;

public interface SortedLinkedList {

	public void add(String value);

	public String get(int index);

	public boolean remove(String value);

	public int getSize();

	public SortedLinkedList sort();
}
