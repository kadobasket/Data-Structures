package datastructures;

import java.util.Iterator;

import designpatterns.Visitor;

public class LinkedList<T> implements Iterable<T> {
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
    
    public void insertAfter(ListNode<T> previous, T data) {
        if (previous == null)
            throw new RuntimeException("Cannot insert after null.");
        ListNode<T> next = previous.getNext();

        addNode(previous, newNode(data), next);
    }
    
    public void insertBefore(ListNode<T> next, T data) {
        if (next == null)
            throw new RuntimeException("Cannot insert before null.");
        ListNode<T> previous = next.getPrevious();

        addNode(previous, newNode(data), next);
    }
    
    public void append(T data) {
        addNode(tail, newNode(data), null);
    }
    
    public void addFirst(T data) {
        addNode(null, newNode(data), head);
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
    
    public void removeFirst() {
		remove(head);
    }

    public void removeLast() {
		remove(tail);
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

	public boolean isEmpty() {
		return head == null;
	}
}