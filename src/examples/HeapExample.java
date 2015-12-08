package examples;

import datastructures.Heap;

public class HeapExample {

	public static void main(String[] args) {
		Heap<Integer> heap = new Heap<>();
		heap.push(1);
		heap.push(4);
		heap.push(3);
		heap.push(2);
		while (!heap.isEmpty()) {
			System.out.print(heap.pop() + ", ");
		}
		System.out.println();
		heap = new Heap<>(false);
		heap.push(1);
		heap.push(4);
		heap.push(3);
		heap.push(2);
		while (!heap.isEmpty()) {
			System.out.print(heap.pop() + ", ");
		}
	}

}
