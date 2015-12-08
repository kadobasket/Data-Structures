package datastructures;

class ListNode<T> {
    private T data;
    private ListNode<T> previous;
    private ListNode<T> next;
    
    public ListNode(T data) {
        this.data = data;
        this.next = null;
        this.previous = null;
    }
    
    public void setNext(ListNode<T> next) {
        this.next = next;
    }

    public void setPrevious(ListNode<T> previous) {
        this.previous = previous;
    }
    
    public ListNode<T> getNext() {
        return next;
    }
    
    public ListNode<T> getPrevious() {
        return previous;
    }
    
    public T getData() {
        return data;
    }
}