package algorithms;

public class Util {
	private static long swapCount = 0;
	private static long compareCount = 0;
	
	public static long getSwapCount() {
		return swapCount;
	}	
	public static long getCompareCount() {
		return compareCount;
	}
	public static void resetStatistics() {
		swapCount = 0;
		compareCount = 0;
	}
	
	public static <T> void swap(T[] data, int a, int b) {
		T t = data[a];
		data[a] = data[b];
		data[b] = t;
		swapCount++;
	}

	public static <T extends Comparable<T>> boolean equals(T a, T b)
	{
		compareCount++;
		return a.compareTo(b) == 0;
	}
	
	public static <T extends Comparable<T>> boolean less(T a, T b)
	{
		compareCount++;
		return a.compareTo(b) < 0;
	}
	
	public static <T extends Comparable<T>> boolean greater(T a, T b)
	{
		compareCount++;
		return a.compareTo(b) > 0;
	}
}
