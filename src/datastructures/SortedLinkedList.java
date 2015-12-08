package datastructures;

import java.util.Iterator;

import designpatterns.Visitor;

public class SortedLinkedList<T extends Comparable<T>>
		implements Iterable<T>
{
    private ListNode<T> head;
    private ListNode<T> tail;
    
    private ListNode<T> newNode(T data) {
        return new ListNode<>(data);
    }
    
    private void addNode(ListNode<T> previous, ListNode<T> node, ListNode<T> next) {
        node.setNext(next);
        node.setPrevious(previous);
        
        if (previous != null)
            previous.setNext(node);
        else
            head = node;
        
        if (next != null)
            next.setPrevious(node);
        else
            tail = node;
    }
    
    private void insertBefore(ListNode<T> next, ListNode<T> node) {
        if (next == null)
            throw new RuntimeException("Cannot insert before null.");
        addNode(next.getPrevious(), node, next);
    }

    public void insert(T data) {
    	if (data == null)
    		throw new NullPointerException("Cannot insert null values on sorted list.");
    	ListNode<T> node = newNode(data);
        ListNode<T> next = searchInsertPoint(data);
        if (next == null) {
        	addNode(tail, node, null);
        } else {
        	insertBefore(next, node);
        }
    }
    
    private ListNode<T> searchInsertPoint(T data) {
    	ListIterator<T> iter = (ListIterator<T>) iterator();
    	while (iter.hasNext()) {
    		ListNode<T> node = iter.getNode();
    		T o = iter.next();
    		int cmp = o.compareTo(data);
    		if (cmp > 0)
    			return node;
    	}
		return null;
	}

	public void remove(ListNode<T> node) {
        if (node == null)
            throw new RuntimeException("Cannot remove null node.");
        ListNode<T> next = node.getNext();
        ListNode<T> previous = node.getPrevious();
        if (next != null)
            next.setPrevious(previous);
        else
            tail = previous;
        if (previous != null)
            previous.setNext(next);
        else
            head = next;
    }
    
    public <K extends Comparable<T>> T search(K key) {
        for (T o : this)
            if (key.compareTo(o) == 0)
                return o;
        return null;
    }
    
	public Iterator<T> iterator() {
        return (Iterator<T>) new ListIterator<>(this.head);
    }
    
    public ReverseIterator<T> reverseIterator() {
        return new ReverseIterator<>(this.tail);
    }
    
    public void visitAll(Visitor<T> visitor) {
        for (T o : this)
            visitor.visit(o);
    }
}
