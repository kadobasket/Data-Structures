package datastructures;

import static algorithms.Util.swap;

@SuppressWarnings("rawtypes")
class KDNode<T extends KDData> implements Comparable<KDNode<T>> {
	public void print() {
		System.out.print("(" + point);
		if (left != null || right != null) {
			if (left == null)
				System.out.print("_ ");
			else
				left.print();
			if (right == null)
				System.out.print("_ ");
			else
				right.print();
		}
		System.out.print(") ");
	}

	@SuppressWarnings("unchecked")
	private
	int partition(T[] data, int s, int e, T p, int dimension)
	{
		int l = s;
		int h = e-1;
		while (l < h) {
			if (data[l].compare(p,dimension) < 0) l++;
			else if (data[h].compare(p,dimension) >= 0) h--;
			else swap(data,l,h);
		}
		return h;
	}

	@SuppressWarnings("unchecked")
	private void sort(T[] data, int s, int e, int dimension) {
		for (int i = s; i < e-1; i++) {
			for (int j = i+1; j < e; j++) {
				if (data[j].compare(data[i],dimension) < 0)
					swap(data,i,j);
				else
					break;
			}
		}
	}

	@SuppressWarnings("unchecked")
	private T medianOfThree(T a, T b, T c, int dimension)
	{
		int ab = a.compare(b, dimension);
		int ac = a.compare(c, dimension);
		int bc = b.compare(c, dimension);
		if (ab > 0 && ac > 0) { // a is greater.
			if (bc >= 0) return b; // a > b >= c
			else return c; // a > c > b
		}
		if (bc > 0) { // b is greater.
			if (ac > 0) return a; // b >= a > c
			else return c; // b > c >= a
		}
		// c is greater.
		if (ab > 0) return a; // c >= a > b
		else return b; // c >= b >= a
	}

	public
	void partialSort(T[] data, int start, int end, int n, int dimension)
	{
		int limit = 3;
		int s = start;
		int e = end;
		while (e - s > limit) {
			T a = data[s];
			T b = data[e-1];
			T c = data[(s+e)/2];
			T m = medianOfThree(a,b,c,dimension);
			int cut = partition(data, s, e, m, dimension);
			if (cut == s) cut++;
			if (cut <= n) s = cut;
			else e = cut;
		}
		sort(data,s,e,dimension);
	}
	
	private T point = null;
	private int dimension = -1;
	private KDNode<T> left = null;
	private KDNode<T> right = null;

	private KDNode(T point, int dimension) {
		this.point = point;
		this.dimension = dimension % point.getDimensions();
	}
	
	public KDNode(T[] points, int start, int end, int dimension)
	{
		// partially sort points, halving array.
		int median = start + ((end - start) / 2);
		partialSort(points, start, end, median, dimension);
		this.point = points[median];
		this.dimension = dimension;
		int next = (dimension + 1) % points[0].getDimensions();
		if (median - start > 1)
			left = new KDNode<>(points, start, median, next);
		else if (median - start == 1)
			left = new KDNode<>(points[start], next);
		if (end - (median+1) > 1)
			right = new KDNode<>(points, median + 1, end, next);
		else if (end - (median+1) == 1)
			right = new KDNode<>(points[median+1], next);
	}

	public KDNode<T> getLeft() {
		return left;
	}

	public KDNode<T> getRight() {
		return right;
	}

	public T getData() {
		return point;
	}

	public int getDimension() {
		return dimension;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int compareTo(KDNode<T> o) {
		int od = o.getDimension();
		int pd = o.getDimension();
		if (od != pd)
			return (pd - od);
		T a = o.getData();
		for (int i = 0; i < this.point.getDimensions(); i++) {
			if (this.point.compare(a,i) != 0)
				return this.point.compare(a,i);
		}
		return 0;
	}
	
	@Override
	public String toString() {
		return point.toString();
	}
}
