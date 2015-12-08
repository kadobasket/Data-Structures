package datastructures;

import java.util.ArrayList;

class Entry<K,V> {
	private int[] hashes;
	private int hash;
	public final K key;
	public final V value;
	public Entry(K key, V value, int hashes) {
		this.key = key;
		this.value = value;
		this.hashes = new int[hashes];
		for (int i = 0; i < hashes; i++)
			this.hashes[i] = this.hashvalue(key.hashCode(), i);
	}
	private int hashvalue(int hashcode, int j) {
		java.util.Random gen = new java.util.Random( hashcode );
		for (int i = 0; i < j; i++)
			gen.nextInt();
		return gen.nextInt();
	}
	public int hash(int i) {
		return hashes[i];
	}
	public int getHash() {
		return hash;
	}
	public void setHash(int hash) {
		this.hash = hash;
	}
}

public class CuckooHashMap<K,V> {
	private Entry<K,V>[] dados;
	private int hashCount;
	private int count;
	private boolean autorehash;
	
	public CuckooHashMap() {
		this(2);
	}
	
	@SuppressWarnings("unchecked")
	public CuckooHashMap(int hashCount) {
		this.hashCount = hashCount;
		this.dados = new Entry[10];
		this.count = 0;
		this.autorehash = false;
	}

	private int validIndex(int i) {
		return Math.abs(i) % dados.length;
	}
	
	public void add(K key, V value) throws Exception {
		Entry<K,V> novo = new Entry<>(key, value, hashCount);
		try {
			put(novo);
		} catch(Exception e) {
			if (autorehash) {
				rehash();
				put(novo);
			}
			throw e;
		}
		count++;
	}
	
	private void put(Entry<K, V> novo)
		throws Exception
	{
		ArrayList<Integer> ops = getOperations(novo);
		// tries hold a list of changes.
		Entry<K,V> temp;
		//System.err.println("Moves: " + (ops.size() - 1));
		for (int i : ops) {
			//System.err.println("Storing at position: " + i);
			temp = dados[i];
			dados[i] = novo;
			novo = temp;
		}
	}
	
	private ArrayList<Integer> getOperations(Entry<K, V> novo)
			throws Exception
	{
		int i;
		ArrayList<Integer> tries = new ArrayList<>();
		int oindex = validIndex(novo.hash(0));
		tries.add(oindex);
		while (dados[oindex] != null) {
			Entry<K,V> old = dados[oindex];
			int oldhash = old.getHash();
			for (i = 0; i < hashCount; i++) {
				int h = old.hash(i);
				if (oldhash != h) {
					int index = validIndex(h);
					if (tries.contains(index))
						continue;
					// next iteration
					tries.add(index);
					oindex = index;
					break;
				}
			}
			if (i == hashCount) {
				throw new Exception("Could not find empty slot.");
			}
		}
		return tries;
	}

	public double getLoadFactor() {
		return count/(1.0 * dados.length);
	}
	
	public V get(K key) throws Exception {
		Entry<K,V> needle = new Entry<>(key, null, hashCount);
		for (int i = 0; i < hashCount; i++) {
			int index = validIndex(needle.hash(i));
			if (dados[index].key.equals(key))
				return dados[index].value;
		}
		throw new Exception("Value for key '" + key + "' not found.");
	}
	
	@SuppressWarnings("unchecked")
	public void rehash() throws Exception {
		Entry<K,V>[] temp = dados;
		dados = new Entry[temp.length * 2];
		for (Entry<K,V> e : temp)
			if (e != null) put(e);
	}
	
	public void debugPrint() {
		System.out.println("----- start -----");
		for (int i = 0; i < dados.length; i++) {
			if (dados[i] == null) {
				System.out.println(i + ": null");
			} else {
				K k = dados[i].key;
				V v = dados[i].value;
				String data = String.format("%d: %s,%s",i,k,v);
				System.out.println(data);
			}
		}
		System.out.println("LoadFactor: " + getLoadFactor());
		System.out.println("------ end ------");
	}

	public void setAuthash(boolean state) {
		this.autorehash = state;
	}
}
