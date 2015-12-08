/**
 * TODO: Implement method "remove(K key)".
 */
package datastructures;

import java.util.Iterator;

import designpatterns.Visitor;

class BSTDepthFirstIterator<T>
	implements Iterator<T>
{
	private Stack<BSTNode<?>> stack;	
	public BSTDepthFirstIterator(BSTNode<?> root)
	{
		stack = new Stack<>();
		stack.push(root);
	}
	@Override
	public boolean hasNext() {
		return !stack.isEmpty();
	}
	@Override
	public T next() {
		BSTNode<?> a, current = stack.pop();
		a = current.getRight();
		if (a != null)
			stack.push(a);
		a = current.getLeft();
		if (a != null)
			stack.push(a);
		@SuppressWarnings("unchecked")
		T res = (T)current.getData();
		return res;
	}
}

class BSTBreadthFirstIterator<T>
	implements Iterator<T>
{
	private Queue<BSTNode<?>> queue;
	public BSTBreadthFirstIterator(BSTNode<?> root)
	{
		queue = new Queue<>();
		queue.push(root);
	}
	@Override
	public boolean hasNext() {
		return !queue.isEmpty();
	}
	@Override
	public T next() {
		BSTNode<?> a, current = queue.pop();
		a = current.getLeft();
		if (a != null)
			queue.push(a);
		a = current.getRight();
		if (a != null)
			queue.push(a);
		@SuppressWarnings("unchecked")
		T res = (T)current.getData();
		return res;
	}
}

public class BinarySearchTree<T extends Comparable<T>>
	implements Iterable<T>
{
	private BSTNode<T> root;
	
	public BSTNode<T> getRoot() { return root; } 
	
	public BinarySearchTree() {
		root = null;
	}
	
	protected void insert(BSTNode<T> node)
		throws DuplicateKeyException
	{
		if (root == null) {
			root = node;
		} else
			root.insert(node);
	}
	
	public void insert(T data)
		throws DuplicateKeyException
	{
		BSTNode<T> node = new BSTNode<>(data);
		insert(node);
	}
	
	public <K extends Comparable<T>> T search(K key) {
		return root.search(key);
	}
	
	public void preOrder(Visitor<T> v) {
		preOrder(root,v);
	}
	
	private void preOrder(BSTNode<T> node, Visitor<T> v) {
		if (node == null) return;
		v.visit(node.getData());
		preOrder(node.getLeft(),v);
		preOrder(node.getRight(),v);
	}
	
	public void postOrder(Visitor<T> v) {
		postOrder(root,v);
	}
	
	private void postOrder(BSTNode<T> node, Visitor<T> v) {
		if (node == null) return;
		postOrder(node.getLeft(),v);
		postOrder(node.getRight(),v);
		v.visit(node.getData());
	}
	
	public void inOrder(Visitor<T> v) {
		inOrder(root,v);
	}
	private void inOrder(BSTNode<T> node, Visitor<T> v) {
		if (node == null) return;
		inOrder(node.getLeft(),v);
		v.visit(node.getData());
		inOrder(node.getRight(),v);
	}
	
	public void breadthSearch(Visitor<T> v) {
		Queue<BSTNode<T>> L = new Queue<>();
		L.push(root);
		while (!L.isEmpty()) {
			BSTNode<T> n = L.pop();
			if (n != null) { 
				L.push(n.getLeft());
				L.push(n.getRight());
				v.visit(n.getData());
			}
		}
	}
	
	public void depthSearch(Visitor<T> v) {
		Stack<BSTNode<T>> L = new Stack<>();
		L.push(root);
		while (!L.isEmpty()) {
			BSTNode<T> n = L.pop();
			if (n != null) { 
				L.push(n.getRight());
				L.push(n.getLeft());
				v.visit(n.getData());
			}
		}
	}
	
	public void vectorPrint() {
		System.out.print("| ");
		Visitor<T> v = new Visitor<T>() {
			public void visit(T data)
			{ System.out.print(data + " | "); }
		};
		breadthSearch(v);
	}
	public void debugPrint() {
		if (root != null)
			root.debugPrint();
		else
			System.out.println("(null)");
	}
	public Iterator<T> depthFirstIterator() {
		return (Iterator<T>)(new BSTDepthFirstIterator<T>(this.root));
	}
	public Iterator<T> breadthFirstIterator() {
		return (Iterator<T>)(new BSTBreadthFirstIterator<T>(this.root));
	}
	@Override
	public Iterator<T> iterator() {
		return new BSTDepthFirstIterator<T>(this.root);
	}
	protected void setRoot(BSTNode<T> newroot) {
		this.root = newroot;
	}
}
