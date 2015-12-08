package datastructures;

import java.util.Iterator;

public class ListIterator<T> implements Iterator<T> {
    private ListNode<T> current;
    
    public ListIterator(ListNode<T> first) {
        this.current = first;
    }
    
    @Override
    public boolean hasNext() {
        return current != null;
    }
    
    @Override
    public T next() {
        if (current == null)
            throw new RuntimeException("There's no more data for the iterator.");
        T data = current.getData();
        current = current.getNext();
        return data;
    }

    public ListNode<T> getNode() {
    	return current;
    }
}

