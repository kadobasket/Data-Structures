package algorithms;

import static algorithms.Util.*;

public class Sorting {
	/*
	 * Uncomment the lines for "changes" and "compares" to count
	 * how many swaps and comparisions are performed. 
	 * You should manually reset both to 0 (zero) before sorting.
	 */
	//public static int changes;
	public static int compares;
/*
	private static <T> void swap(T[] array, int o, int t) {
		changes++;
		T s = array[o];
		array[o] = array[t];
		array[t] = s;
	}

	private static <T extends Comparable<T>> boolean less(T a, T b)
	{
		compares++;
		return a.compareTo(b) < 0;
	}
	
	private static <T extends Comparable<T>> boolean greater(T a, T b)
	{
		compares++;
		return a.compareTo(b) > 0;
	}
*/	
	public static <T extends Comparable<T>>
	void selectionSort(T[] data)
	{
		for (int i = 0; i < data.length-1; i++) {
			T min = data[i];
			int n = i;
			for (int j = i+1; j < data.length; j++) {
				if (less(data[j],min)) {
					min = data[j];
					n = j;
				}
			}
			swap(data,i,n);
		}
	}

	/**
	 * Sorts the array data using insertion sort.
	 * This algorithm performs O(N^2) operations, uses O(1) memory and
	 * is stable relative to key ordering.
	 * @param data
	 */
	public static <T extends Comparable<T>>
	void insertionSort(T[] data)
	{
		insertionSort(data,0,data.length);
	}

	/**
	 * Sorts the array data in the range [start, end).
	 * This algorithm performs O(N^2) operations, uses O(1) memory and
	 * is stable relative to key ordering.
	 * @param data
	 * @param start
	 * @param end
	 */
	public static <T extends Comparable<T>>
	void insertionSort(T[] data, int start, int end)
	{
		if (start < 0 || end < 0)
			throw new ArrayIndexOutOfBoundsException("Invalid index.");
		if (start >= data.length || end > data.length)
			throw new ArrayIndexOutOfBoundsException("Invalid index.");
		if ((end-start) <= 1)
			return;
		for (int i = start; i < end-1; i++) {
			for (int j = i+1; j > 0; j--) {
				if (less(data[j],data[j-1])) {
					swap(data,j-1,j);
				} else {
					break;
				}
			}
		}
	}


	public static <T extends Comparable<T>>
	void bubbleSort(T[] data)
	{
		for (int i = 0; i < data.length; i++) {
			boolean changed = false;
			for (int j = 0; j < data.length-i-1; j++) {
				if (greater(data[j],data[j+1])) {
					swap(data,j,j+1);
					changed = true;
				}
			}
			if (!changed) return;
		}
	}
	
	public static <T extends Comparable<T>>
	void quickSort(T[] array)
	{
		recursiveQuicksort(array, 0, array.length);
	}
	private static <T extends Comparable<T>>
	void recursiveQuicksort(T[] array, int s, int e)
	{
		if (e-s <= 1) return; // base case, unity array.
		int p = partition(array, s, e);
		// ensure we split the partition, as we are not using an
		// index as pivot, but a value.
		if (p == s) p++; 
		recursiveQuicksort(array,s,p);
		recursiveQuicksort(array,p,e);
	}
	
	private static <T extends Comparable<T>>
	int partition(T[] array, int s, int e)
	{
		return partition(array, s, e, array[s]);
	}
	
	private static <T extends Comparable<T>>
	int partition(T[] array, int s, int e, T pivot)
	{
		int h = e-1;
		int l = s;
		while (l < h) {
			if (less(array[l],pivot)) {
				l++;
			} else if (! less(array[h],pivot)) {
				h--;
			} else {
				swap(array,l,h);
			}
		}
		return h;
	}

	public static <T extends Comparable<T>>
	T medianOfThree(T a, T b, T c)
	{
		int ab = a.compareTo(b);
		int ac = a.compareTo(c);
		int bc = b.compareTo(c);
		// a > (b,c)
		if (ab > 0 && ac > 0) {
			if (bc > 0)
				return b; // a > b > c
			else
				return c; // a > c >= b
		}
		// b >= (a,c)
		if (bc > 0) {
			if (ac > 0)
				return a; // b >= a > c
			else
				return c; // b >= c >= a
		}
		// c >= (a,b)
		if (ab > 0)
			return a; // c >= a > b
		else
			return b; // c >= b >= a
	}
	
	/**
	 * Partially sort the array so that the n-th element of it is
	 * in the right position.
	 * @param array
	 * @param n
	 */
	public static <T extends Comparable<T>>
	void nthElement(T[] array, int n)
	{
		int limit = 3;
		int s = 0;
		int e = array.length;
		while (e - s > limit) {
			T a = array[s];
			T b = array[e-1];
			T c = array[(s+e)/2];
			T m = medianOfThree(a,b,c);
			int cut = partition(array, s, e, m);
			if (cut <= n)
				s = cut;
			else
				e = cut;
		}
		insertionSort(array,s,e);
	}
	
	public static <T extends Comparable<T>>
	void mergeSort(T[] data)
	{
		Object[] temp = new Object[data.length];
		for (int sz = 1; sz < data.length; sz *= 2) {
			for (int i = 0; i < data.length; i = i + 2*sz) {
				int iLeft = i;
				int iRight = Math.min(i + sz, data.length);
				int lim = iRight;
				int iEnd = Math.min(i + 2*sz, data.length);
				for (int j = iLeft; j < iEnd; j++) {
					if (iLeft < lim && (iRight >= iEnd ||
						!greater(data[iLeft],data[iRight])))
					{
						temp[j] = data[iLeft];
						iLeft++;
					} else {
						temp[j] = data[iRight];
						iRight++;
					}
				}
			}
			System.arraycopy(temp, 0, data, 0, temp.length);
		}
	}
	
	public static <T extends Comparable<T>>
	void heapSort(T[] data)
	{
		heapify(data);
		int size = data.length;
		while (size > 0) {
			swap(data,0,size-1);
			size--;
			// heapdown
			int root = 0;
			while (root*2 < size) {
				int esq = root*2 + 1;
				int dir = root*2 + 2;
				if (dir >= size)
					dir = esq;
				if (esq >= size)
					break;
				int index = dir; 
				if (greater(data[esq],data[dir]))
					index = esq;
				if (less(data[root],data[index]))
					swap(data,root,index);
				root = index;
			}
		}
	}
	private static <T extends Comparable<T>>
	void heapify(T[] data)
	{
		int sz = 1;
		while (sz < data.length) {
			int index = sz;
			// heapup
			while (index > 0) {
				int parent = index / 2;
				if (index % 2 == 0)
					parent--;
				if (less(data[parent],data[index])) {
					swap(data, parent, index);
					index = parent;
				} else {
					break;
				}
			}
			sz++;
		}
	}

}
