package examples;

import java.util.Iterator;

import datastructures.BinarySearchTree;
import designpatterns.Visitor;

public class BSTExample {

	// This print shows the tree as a "vector".
	static Visitor<Integer> print = new Visitor<Integer>() {
		@Override
		public void visit(Integer data)
		{ System.out.print(data + " | "); }
	};
	
	public static void printAll(Iterator<Integer> iter)
	{
		while (iter.hasNext())
			print.visit(iter.next());
	}
	
	public static void main(String[] args) {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		try {
			bst.insert(10);
			bst.insert(5);
			bst.insert(1);
			bst.insert(15);
			bst.insert(17);
			bst.insert(12);
			bst.insert(7);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		
		System.out.println("Tree");
		bst.debugPrint();
		System.out.println("\nPre Order");
		System.out.print("| ");
		bst.preOrder(print);
		System.out.println("\nIn Order");
		System.out.print("| ");
		bst.inOrder(print);
		System.out.println("\nPost Order");
		System.out.print("| ");
		bst.postOrder(print);
		System.out.println("\nBreadth First");
		System.out.print("| ");
		bst.breadthSearch(print);
		System.out.println("\nDepth First");
		System.out.print("| ");
		bst.depthSearch(print);

		System.out.println("\n------ Iterators ------");

		System.out.println("For each:");
		for (int i : bst)
			print.visit(i);
		System.out.println("\nBreadth First:");
		printAll(bst.breadthFirstIterator());
		System.out.println("\nDepth First:");
		printAll(bst.depthFirstIterator());

	}
}
