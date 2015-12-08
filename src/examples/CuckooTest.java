package examples;

import datastructures.CuckooHashMap;
import static java.lang.String.format;

public class CuckooTest {
	public static void main(String... args)
			throws Exception
	{
		CuckooHashMap<String, Integer> hash = new CuckooHashMap<>(2);
		String[] keys = {"rafael", "andre", "betina", "etiene", "ana",
				         "rogerio", "carlos", "marcos", "natalia"};
		
		try {
			for (int i = 0; i < keys.length; i++) {
				System.out.println(format("Adding: {%s,%s}",keys[i],i+1));
				hash.add(keys[i], i+1);
			}
		} catch(Throwable t) {
			System.out.println("Failed to insert with load factor = " +
								hash.getLoadFactor());
		}
		
		hash = new CuckooHashMap<>(2);
		hash.setAuthash(true);
		for (int i = 0; i < keys.length; i++) {
			hash.add(keys[i], i+1);
			hash.debugPrint();
		}
		System.out.println("natalia = " + hash.get("natalia"));
		System.out.println("betina = " + hash.get("betina"));
	}
}
