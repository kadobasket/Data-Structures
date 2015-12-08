package datastructures;

import static algorithms.Util.*;

public class Heap<T extends Comparable<T>> {
	private Vector<T> data;
	private boolean max;
	
	public Heap() {
		this(true);
	}
	
	public Heap(boolean max) {
		this.data = new Vector<>();
		this.max = max;
	}
	
	public void push(T elem) {
		data.append(elem);
		int e = data.length()-1;
		while (e > 0) {
			int r = e >> 1;
			if (e % 2 == 0) r--;
			T a = data.get(r);
			T b = data.get(e);
			if ((max && less(a,b)) || (!max && greater(a,b)))
				{ data.set(r,b); data.set(e,a); }
			else
				{ data.set(r,a); data.set(e,b); }
			 e = r;
		}
	}

	public T pop() {
		if (data.length() == 0)
			return null;
		T res = data.get(0);
		data.set(0, data.get(data.length()-1));
		data.remove(data.length()-1);
		int r = 0;
		while (r < data.length()) {
			int e = r*2 + 1;
			int d = r*2 + 2;
			T v = data.get(r);
			T a,b,m;
			int s = r;
			if (e >= data.length()) break;
			if (d >= data.length()) d = e;
			a = data.get(e);
			b = data.get(d);
			if (max) {
				s = greater(a,b) ? e : d;
				m = data.get(s);
				if (greater(m,v)) { data.set(r,m); data.set(s,v); r = s;}
				else break;
			} else {
				s = less(a,b) ? e : d;
				m = data.get(s);
				if (less(m,v)) { data.set(r,m); data.set(s,v); r = s;}
				else break;
			}
		}
		return res;
	}

	public T peek() {
		return data.get(0);
	}
	
	public boolean isEmpty() {
		return data.length() == 0;
	}

	public int size() {
		return data.length();
	}

	public void print() {
		System.out.print("| ");
		for (T t : data)
			System.out.print(t + " | " );
		System.out.println();
	}
}
