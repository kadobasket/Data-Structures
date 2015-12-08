package datastructures;

import static algorithms.Util.*;

@SuppressWarnings("rawtypes")
public class KDTree<T extends KDData> {
	
	private KDNode<T> root;
	
	public KDTree(T[] points) {
		root = new KDNode<>(points, 0, points.length, 0);
	}
	
	public void print() {
		root.print();
	}

	private
	void getClosestLeaf(KDNode<T> node, T p, Stack<KDNode<T>> stack)
	{
		KDNode<T> t, iter = node;
		int d = 0;
		while (iter != null) {
			stack.push(iter);
			double c = p.getValueForDimension(d).doubleValue();
			double i=iter.getData().getValueForDimension(d).doubleValue();
			if (c < i) {
				t = iter.getLeft();
				if (t == null)
					t = iter.getRight();
			} else {
				t = iter.getRight();
				if (t == null)
					t = iter.getLeft();
			}
			iter = t;
			d = (d+1) % p.getDimensions();
		}
	}
	
	public java.util.Iterator<T> findKNearestPoint(T point, int k) {
		Vector<T> result = new Vector<>(k);
		@SuppressWarnings("unchecked")
		KDNode<T>[] found = findNearestPoints(point,k);
		for (KDNode<T> t : found)
			if (t != null) // in case there were not enough points.
				result.append(t.getData());
		return result.iterator();
	}
	public T findNearestPoint(T point) {
		return findKNearestPoint(point, 1).next();
	}

	@SuppressWarnings("unchecked")
	private KDNode[] findNearestPoints(T point, int k) {
		int evaluated = 1;
		KDNode[] res = new KDNode[k];
		int count = 0;
		// assert input values.
		if (root == null || k < 1 || point == null)
			return res;
		// search for closest candidate
		Stack<KDNode<T>> stack = new Stack<>();
		// find closest leaf.
		getClosestLeaf(root, point, stack);
		KDNode<T> last, closer = stack.pop();
		KDNode<T> candidate = closer;
		//System.out.println("Evaluating " + candidate.getData());
		double distance = point.distance(closer.getData());
		res[0] = closer; count++;
		// check if candidate is an exact match.
		if (distance == 0 && k == 1)
			return res;
		// traverse back the tree searching closer points.
		while (!stack.isEmpty()) {
			evaluated++;
			// get node candidate
			last = candidate;
			candidate = stack.pop();
			double d = point.distance(candidate.getData());
			// check if candidate is an exact match (iff k is 1).
			if (d == 0 && k == 1) {
				res[0] = candidate;
				return res;
			}
			// check if candidate is a better match than current.
			if (count < k || d < distance) {
				if (count < k) {
					res[count] = candidate;
					for (int i = count; i > 0; i--) {
						if (res[i-1].getData().distance(point) < d) {
							swap(res,i-1,i);
						} else break;
					}
					count++;
				} else {
					int i = count;
					while (i > 0) {
						i--;
						if (res[i].getData().distance(point) > d) break;
					}
					if (i > 0)
						System.arraycopy(res, i, res, i-1, i);
					res[i] = candidate;
				}
				closer = res[0];
				distance = point.distance(closer.getData());
			}
			// check other side of subtree
			double c, i;
			int dim = candidate.getDimension();
			c=candidate.getData().getValueForDimension(dim).doubleValue();
			i=point.getValueForDimension(dim).doubleValue();
			KDNode<T> other = candidate.getLeft() == last ? 
					candidate.getRight() : candidate.getLeft();
			if (other!=null && (count < k || Math.abs(i-c)<distance))
				getClosestLeaf(other, point, stack);
		}
		System.out.println("Nodes evaluated: " + evaluated);
		return res;
	}
}
