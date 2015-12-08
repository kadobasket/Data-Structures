package datastructures;

public class Stack<T> {
	private LinkedList<T> stack;
	private int count;
	
	public void print() {
		System.out.print("[ ");
		for (T t : stack)
			System.out.print(t + " ");
		System.out.println("]");
	}
	
	public Stack() {
		stack = new LinkedList<>();
		count = 0;
	}
	
	public void push(T data) {
		stack.append(data);
		count++;
	}
	
	public T pop()
	{
		T data = peek();
		stack.removeLast();
		count--;
		return data;
	}
	
	public T peek()
	{
		if (stack.isEmpty())
			return null;
		ReverseIterator<T> iter = stack.reverseIterator();
		return iter.next();
	}
	
	public boolean isEmpty() {
		return stack.isEmpty();
	}
	
	public int size() {
		return count;
	}
}
