package datastructures;

class AVLNode<T extends Comparable<T>>
	extends BSTNode<T>
{
	private void update() {
		if (getLeft() != null)
			((AVLNode<T>)(getLeft())).update();
		if (getRight() != null)
			((AVLNode<T>)(getRight())).update();
		updateHeight(false);
	}
	
	public AVLNode(T data) {
		super(data);
	}
	
	public AVLNode<T> validateAVL() {
		updateHeight(false);
		int fb = getBalance();
		boolean l = isLeftSon();
		AVLNode<T> newroot = this; 
		if (fb == -2) {
			if (getRight().getBalance() == 1)
				setRight(getRight().rotateRight());
			newroot = (AVLNode<T>) rotateLeft();
		} else if (fb == 2) {
			if (getRight().getBalance() == 1)
				setRight(getRight().rotateRight());
			newroot = (AVLNode<T>) rotateRight();
		}
		if (newroot != this) {
			newroot.update();
			if (newroot.getParent() != null) {
				if (l)
					newroot.getParent().setLeft(newroot);
				else
					newroot.getParent().setRight(newroot);
			}
		}
		if (newroot.getParent() != null)
			return ((AVLNode<T>)(newroot.getParent())).validateAVL();
		else
			return newroot;
	}
}

public class AVLTree<T extends Comparable<T>>
	extends BinarySearchTree<T>
{
	protected void insert(AVLNode<T> node)
		throws DuplicateKeyException
	{
		super.insert(node);
		super.setRoot(node.validateAVL());
	}	
	
	public void insert(T data)
		throws DuplicateKeyException
	{
		AVLNode<T> node = new AVLNode<>(data);
		insert(node);
	}
}
