package datastructures;

class BSTNode<T extends Comparable<T>> {
	private T data;
	private BSTNode<T> left;
	private BSTNode<T> right;
	private BSTNode<T> parent;
	private int height;
	
	public BSTNode(T data) {
		this.data = data;
		this.left = null;
		this.right = null;
		this.parent = null;
		this.height = 1;
	}
	protected void updateHeight() {
		updateHeight(true);
	}
	protected void updateHeight(boolean recurse) {
		int a = left == null? 0 : left.height;
		int b = right == null? 0 : right.height;
		int newHeight = 1 + Math.max(a, b);
		if (newHeight == height)
			return;
		height = newHeight;
		if (parent != null && recurse)
			parent.updateHeight();
	}
	public int getHeight() {
		return height;
	}
	public int getBalance() {
		int a = left == null? 0 : left.height;
		int b = right == null? 0 : right.height;
		return a - b;
	}
	public void insert(BSTNode<T> node) throws DuplicateKeyException
	{
		int cmp = node.data.compareTo(this.data);
		if (cmp < 0)
			insert_left(node);
		else if (cmp > 0)
			insert_right(node);
		else
			throw new DuplicateKeyException("Key already exists.");
	}
	protected void setLeft(BSTNode<T> node) {
		left = node;
		node.parent = this;
	}
	protected void setRight(BSTNode<T> node) {
		right = node;
		node.parent = this;
	}	
	protected void insert_left(BSTNode<T> node)
		throws DuplicateKeyException
	{
		if (left == null) {
			setLeft(node);
			updateHeight();
		} else
			left.insert(node);
	}
	protected void insert_right(BSTNode<T> node)
		throws DuplicateKeyException
	{
		if (right == null) {
			setRight(node);
			updateHeight();
		} else
			right.insert(node);
	}
	public <K extends Comparable<T>> T search(K key) {
		int cmp = key.compareTo(this.data);
		if (cmp < 0) {
			if (left == null)
				return null;
			return left.search(key);
		} else if (cmp > 0) {
			if (right == null)
				return null;
			return right.search(key);
		} else
			return data; // ignore duplicate keys.
	}	
	public T getData() {
		return data;
	}	
	public BSTNode<T> getLeft() {
		return left;
	}	
	public BSTNode<T> getRight() {
		return right;
	}	
	public BSTNode<T> getParent() {
		return parent;
	}
	public BSTNode<T> getGrandParent() {
		if (parent == null)
			return null;
		return parent.parent;
	}
	public BSTNode<T> getUncle() {
		BSTNode<T> G = getGrandParent();
		if (G == null)
			return null;
		if (parent.isRightSon())
			return G.getLeft();
		return G.getRight();
	}	
	public boolean isRightSon() {
		if (parent == null)
			return false;
		return parent.getRight() == this;
	}
	public boolean isLeftSon() {
		if (parent == null)
			return false;
		return parent.getLeft() == this;
	}
	public BSTNode<T> rotateRight() {
		// cannot rotate if there's no left subtree.
		if (this.left == null)
			return this;
		//---
		BSTNode<T> newroot = this.left;
		BSTNode<T> P = this.parent, B;
		B = this.left.right;
		this.left.right = this;
		this.left.parent = P;
		this.parent = this.left;
		this.left = B;
		return newroot;
	}
	public BSTNode<T> rotateLeft() {
		// cannot rotate if there's no right subtree.
		if (this.right == null)
			return this;
		//---
		BSTNode<T> newroot = this.right;
		BSTNode<T> P = this.parent, B;
		B = this.right.left;
		this.right.left = this;
		this.right.parent = P;
		this.parent = this.right;
		this.right = B;
		return newroot;
	}
	public void debugPrint() {
		System.out.print("(");
		System.out.print(data + " ");
		if (left == null) System.out.print("_");
		else left.debugPrint();
		System.out.print(" ");
		if (right == null) System.out.print("_");
		else right.debugPrint();
		System.out.print(")");
	}
}
