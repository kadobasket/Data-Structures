package datastructures;

class RedBlackNode<T extends Comparable<T>>
	extends BSTNode<T>
{
	private boolean red;
	public RedBlackNode(T data) {
		super(data);
		this.red = true;
	}
	public void setBlack() {
		this.red = false;
	}
	public void setRed() {
		this.red = true;
	}
	public boolean isRed() {
		return red;
	}
}

public class RedBlackTree<T extends Comparable<T>>
	extends BinarySearchTree<T>
{
	protected void insert(RedBlackNode<T> node)
		throws DuplicateKeyException
	{
		super.insert(node);
		insert_case1(node);
	}
	
	public void insert(T data)
		throws DuplicateKeyException
	{
		RedBlackNode<T> node = new RedBlackNode<>(data);
		insert(node);
	}

	private void insert_case1(RedBlackNode<T> node) {
		if (node.getParent() == null) {
			node.setBlack();
			return;
		}
		insert_case2(node);
	}
	
	private void insert_case2(RedBlackNode<T> node) {
		RedBlackNode<T> P = (RedBlackNode<T>)(node.getParent());
		if (!P.isRed()) return;
		insert_case3(node);
	}
	
	private void insert_case3(RedBlackNode<T> node) {
		RedBlackNode<T> P = (RedBlackNode<T>)(node.getParent());
		RedBlackNode<T> U = (RedBlackNode<T>)(node.getUncle());
		RedBlackNode<T> G = (RedBlackNode<T>)(P.getParent());
		if (P.isRed() && (U != null && U.isRed())) {
			P.setBlack();
			U.setBlack();
			G.setRed();
			insert_case1(G);
		} else
			insert_case4(node);
	}
	
	private void insert_case4(RedBlackNode<T> node) { // P is R, U is B 
		RedBlackNode<T> P = (RedBlackNode<T>)(node.getParent());
		RedBlackNode<T> G = (RedBlackNode<T>)(P.getParent());
		RedBlackNode<T> N = node;

		if (P.isRightSon() && !node.isRightSon()) {
			P.rotateRight();
			G.setRight(node);
			N = P;
		}
		else if (P.isLeftSon() && !node.isLeftSon()) {
			P.rotateLeft();
			G.setLeft(node);
			N = P;
		}
			
		insert_case5(N);
	}
	
	private void insert_case5(RedBlackNode<T> node) { // P is R, U is B 
		RedBlackNode<T> P = (RedBlackNode<T>)(node.getParent());
		RedBlackNode<T> G = (RedBlackNode<T>)P.getParent();
		RedBlackNode<T> GG = null;
		boolean gl = G.isLeftSon(); 
		if (G.getParent() != null)
			GG = (RedBlackNode<T>)(G.getParent());
		
		P.setBlack();
		G.setRed();
		if (P.isRightSon()) {
			G.rotateLeft();
		} else {
			G.rotateRight();
		}
		if (GG != null)
			if (gl) GG.setLeft(P);
			else GG.setRight(P);
		else
			setRoot(P);
	}
}
