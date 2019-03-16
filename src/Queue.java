import java.util.ArrayList;
import java.util.Iterator;

/*
 * Michael Poust
 * mbp3@pct.edu
 * 11/15/2016
 * 
 * This is the Queue class that is provided from the book's source. Used in the 
 * BST method of returning the keys in getKeys()
 */

public class Queue<E> implements Iterable {
	
	ArrayList<E> list;
	
	public Queue(){
		list = new ArrayList<E>();
	}
	
	public int size(){
		return list.size();
	}

	public boolean isEmpty(){
		return size() == 0;
	}
	
	public void enqueue(E elt){
		list.add(0, elt);
	}
	
	public E dequeue(){
		if(size() == 0)
			return null;
		E temp = list.remove(size() - 1);
		return temp;
	}
	
	public E peek(){
		if(size() == 0)
			return null;
		E temp = list.get(size() - 1);
		return temp;
	}

	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return list.iterator();
	}
}