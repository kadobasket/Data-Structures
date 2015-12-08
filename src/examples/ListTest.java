package examples;

import datastructures.LinkedList;
import datastructures.ListIterator;
import datastructures.SortedLinkedList;
import designpatterns.Visitor;

public class ListTest {
	private static Visitor<Integer> print = new Visitor<Integer>() {
		@Override
		public void visit(Integer data) {
			System.out.println(data);
		}
	};
	
	public static void main(String... args) {
		
		LinkedList<Integer> lista = new LinkedList<>();
		SortedLinkedList<Integer> listaord = new SortedLinkedList<>();
		
		for(int i = 0; i < 10; i++) {
			int x = (int)(Math.random() * 15);
			lista.append(x);
			listaord.insert(x);
		}
		
		lista.visitAll(print);
		System.out.println("=============");
		listaord.visitAll(print);
		System.out.println("=============");
		ListIterator<Integer> iter;
		iter = (ListIterator<Integer>) lista.iterator();
		while (iter.hasNext()) {
			Integer i = iter.next();
			if (i > 10) {
				lista.insertBefore(iter.getNode(), 100);
				break;
			}
			iter.next();
		}
		lista.visitAll(print);
	}
}
