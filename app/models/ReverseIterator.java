package models;

public interface ReverseIterator<E> {
	
	public boolean hasPrev();
	
	public E prev();
	
	public void remove();

}
