package datastructures;

public interface KDData<T extends Number> extends Comparable<KDData<T>> {
	T getValueForDimension(int dimension);
	int getDimensions();
	double distance(KDData<T> other);
	int compare(KDData<T> other, int dimension);
}
