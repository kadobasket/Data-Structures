package examples;

import algorithms.Sorting;

import static algorithms.Util.*;

public class SortingTest {

	private static void print(Object[] arr) {
		for (Object o : arr)
			System.out.print(o + ", ");
	}
	
	public static void main(String[] args) {
		// Use one of these arrays.
		Integer[] arr = { 15,12,2,11,10,5,7,8,1,6,9,14,4,13,3 };
		//Integer[] arr = { 15,14,13,11,12,10,9,8,7,6,5,4,3,2,1 };
		//Integer[] arr = { 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15 };
		
		Integer[] temp = null; // this will store the array to be sorted.
		
		System.out.print("Bubble Sort: ");
		temp = arr.clone();
		resetStatistics();
		Sorting.bubbleSort(temp);
		System.out.println("\nTotal = " +
				(getSwapCount() + getCompareCount()));
		print(temp);

		System.out.print("\nSelection Sort: ");
		temp = arr.clone();
		resetStatistics();
		Sorting.selectionSort(temp);
		System.out.println("\nTotal = " +
				(getSwapCount() + getCompareCount()));
		print(temp);

		System.out.print("\nInsertion Sort: ");
		temp = arr.clone();
		resetStatistics();
		Sorting.insertionSort(temp);
		System.out.println("\nTotal = " +
				(getSwapCount() + getCompareCount()));
		print(temp);

		System.out.print("\nQuick Sort: ");
		temp = arr.clone();
		resetStatistics();
		Sorting.quickSort(temp);
		System.out.println("\nTotal = " +
				(getSwapCount() + getCompareCount()));
		print(temp);

		System.out.print("\nMerge Sort: ");
		temp = arr.clone();
		resetStatistics();
		Sorting.mergeSort(temp);
		System.out.println("\nTotal = " +
				(getSwapCount() + getCompareCount()));
		print(temp);

		System.out.print("\nHeap Sort: ");
		temp = arr.clone();
		resetStatistics();
		Sorting.heapSort(temp);
		System.out.println("\nTotal = " +
				(getSwapCount() + getCompareCount()));
		print(temp);
	}
}
