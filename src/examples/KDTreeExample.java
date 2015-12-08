package examples;

import datastructures.KDData;
import datastructures.KDTree;

class Point2D<T extends Number> implements KDData<T> {
	public final T x;
	public final T y;
	
	public Point2D(T x, T y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int getDimensions() {
		return 2;
	}

	@Override
	public double distance(KDData<T> other) {
		double ox = other.getValueForDimension(0).doubleValue();
		double oy = other.getValueForDimension(1).doubleValue();
		double xd = x.doubleValue() - ox;
		double yd = y.doubleValue() - oy;
		return Math.sqrt(xd*xd + yd*yd);
	}

	@Override
	public int compare(KDData<T> other, int dimension) {
		double ox = other.getValueForDimension(0).doubleValue();
		double oy = other.getValueForDimension(1).doubleValue();
		if (dimension == 0)
			return (int)(x.doubleValue() - ox);
		return (int)(y.doubleValue() - oy);
	}

	@Override
	public T getValueForDimension(int dimension) {
		if (dimension == 0)
			return x;
		return y;
	}
	
	@Override
	public String toString() {
		return String.format("[%d,%d]", x,y);
	}

	@Override
	public int compareTo(KDData<T> o) {
		return (int)this.distance(o);
	}
}

public class KDTreeExample {
	public static void main(String... args) {
		(new KDTreeExample()).run();
	}
	
	private KDTree<Point2D<Integer>> kd = null;

	public void run() {
		Integer[] xs = {0,1,2,3,4,5,6,7,8,9};
		Integer[] ys = {9,8,7,6,5,4,3,2,1,0};
		int sz = xs.length;
		@SuppressWarnings("unchecked")
		Point2D<Integer>[] points = new Point2D[sz];
		for (int i = 0; i < sz; i++)
			points[i] = new Point2D<Integer>(xs[i], ys[i]);

		System.out.println("Started...");
		long s = System.currentTimeMillis();
		kd = new KDTree<>(points);
		long e = System.currentTimeMillis();
		System.out.println("Creation:\n\tSize = " + sz + ", Time = " + (e-s)/1000.0);
		kd.print();
		System.out.println();
		
		findPoint(new Point2D<Integer>(2,5)); // [2,5]
		findPoint(new Point2D<Integer>(8,10)); // [7,9]
		findPoint(new Point2D<Integer>(0,0)); // [2,0]
		findPoint(new Point2D<Integer>(1,2)); // [1,3]
		findPoint(new Point2D<Integer>(4,8)); // [5,9]

		// [0,5]:[1,4]:[2,5]
		findPoints(3, new Point2D<Integer>(1,5));
		// [0,5]:[1,4]:[2,5]:[1,3]:[1,7]
		findPoints(5, new Point2D<Integer>(1,5));
	}

	private void findPoint(Point2D<Integer> point) {
		System.out.println("Nearest point to " + point);
		System.out.println(kd.findNearestPoint(point));
	}
	
	private void findPoints(int k, Point2D<Integer> point) {
		System.out.println("Searching "+k+" nearest points to "+point);
		java.util.Iterator<Point2D<Integer>> iter;
		iter = kd.findKNearestPoint(point, k);
		while (iter.hasNext()) {
			Point2D<Integer> v = iter.next();
			System.out.print(v + ":" + v.distance(point) + " - ");
		}
		System.out.println();
	}
}
