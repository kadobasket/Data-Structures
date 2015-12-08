package examples;

import datastructures.*;

public class AutoBalancedTreeExample {
	public static void main(String[] args) {
		BinarySearchTree<Integer> bst;
		//-- Escolha um dos modelos--//
		//bst = new AVLTree<>();
		bst = new RedBlackTree<>();
		//----//
		try {
			bst.insert(1);
			bst.debugPrint();
			System.out.println();
			bst.insert(3);
			bst.debugPrint();
			System.out.println();
			bst.insert(2);
			bst.debugPrint();
			System.out.println();
			bst.insert(4);
			bst.debugPrint();
			System.out.println();
			bst.insert(5);
			bst.debugPrint();
			System.out.println();
			bst.insert(6);
			bst.debugPrint();
			System.out.println();
			bst.insert(7);
			bst.debugPrint();
			System.out.println();
			bst.insert(9);
			bst.debugPrint();
			System.out.println();
			bst.insert(8);
			bst.debugPrint();
			System.out.println();
		} catch(Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}

	}
}
