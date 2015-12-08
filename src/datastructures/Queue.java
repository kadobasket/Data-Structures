package datastructures;

import java.util.Iterator;

public class Queue<T> {
	private LinkedList<T> queue;
	
	public Queue() {
		queue = new LinkedList<>();
	}
	
	public void push(T data) {
		queue.append(data);
	}
	
	public T pop()
	{
		T data = peek();
		queue.removeFirst();
		return data;
	}
	
	public T peek()
	{
		if (queue.isEmpty())
			return null;
		Iterator<T> iter = queue.iterator();
		return iter.next();
	}
	
	public boolean isEmpty() {
		return queue.isEmpty();
	}
}
