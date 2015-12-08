package datastructures;

import java.util.Iterator;
import java.util.RandomAccess;

class VectorIterator<T> implements Iterator<T> {

	private Vector<T> container;
	private int current;

	VectorIterator(Vector<T> container) {
		this.container = container;
		this.current = 0;
	}
	
	@Override
	public boolean hasNext() {
		return current < container.length();
	}

	@Override
	public T next() {
		return container.get(current++);
	}
}

class ReverseIterator<T> implements Iterator<T> {
    private ListNode<T> current;
    
    public boolean hasNext() {
        return current == null;
    }
    
    public ReverseIterator(ListNode<T> last) {
        this.current = last;
    }
    
    public T next() {
        if (current == null)
            throw new RuntimeException("There's no more data for the iterator.");
        T data = current.getData();
        current = current.getPrevious();
        return data;
    }
}

public class Vector<T>
	implements Iterable<T>, RandomAccess
{
	private static final int MAX_INCREASE = 128;
	
	private Object[] data;
	private int count;
	
	public Vector() {
		this(10);
	}
	
	public Vector(int k) {
		data = new Object[k];
		count = 0;
	}

	public int length() {
		return count;
	}
	
	public void append(T elem) {
		insert(count, elem);
	}
	
	public void insert(int index, T elem) {
		if (index < 0 || index > count)
			throw new ArrayIndexOutOfBoundsException("Invalid index.");
		if (count == data.length)
			increaseStorage();
		System.arraycopy(data, index, data, index+1, count-index);
		data[index] = elem;
		count++;
	}

	private void increaseStorage() {
		int sz = data.length * 2;
		sz = sz > MAX_INCREASE ? MAX_INCREASE : sz;
		Object[] storage = new Object[sz];
		System.arraycopy(data, 0, storage, 0, data.length);
		data = storage;
	}
	
	public void remove(int index) {
		if (index < 0 || index >= count)
			throw new ArrayIndexOutOfBoundsException("Invalid index.");
		System.arraycopy(data, index+1, data, index, count-(index+1));
		count--;
	}
	
	@SuppressWarnings("unchecked")
	public T get(int index) {
		if (index < 0 || index >= count)
			throw new ArrayIndexOutOfBoundsException("Invalid index.");
		return (T)data[index];
	}

	@Override
	public Iterator<T> iterator() {
		return new VectorIterator<T>(this);
	}

	public void set(int index, T n) {
		if (index < 0 || index >= count)
			throw new ArrayIndexOutOfBoundsException("Invalid index.");
		data[index] = n;
	}
}
