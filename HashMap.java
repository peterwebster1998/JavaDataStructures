import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Map.Entry;

public class HashMap<K, V> implements Map<K, V> {

	// Since Java's Entry<K,V> is an interface, we need a
	// class that implements Entry<K,V> so we can create
	// instances of entries.
	private class MapEntry implements Entry<K, V> {
		private K key;
		private V value;

		public MapEntry(K k, V v) {
			key = k;
			value = v;
		}

		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}

		public V setValue(V value) {
			V oldValue = value;
			this.value = value;
			return oldValue;
		}

		public String toString() {
			return "(" + key.toString() + ", " + value.toString() + ")";
		}
	}

	//Each LinkedList value contains a bucket of key value pairs 
	private LinkedList<MapEntry>[] map;
	private int size;
	private int a, b, p, N;
	// This constant doesn't have to be public, but similar to
	// StdDraw.BLACK and Math.PI, it's kinda nice to let other
	// classes have access to this value.
	public static final double LOAD_FACTOR_THRESHOLD = 0.75;

	public HashMap() {
		// Since we'll have to rehash the map at some point,
		// it's useful to put all the initialization in a
		// helper method that we can use elsewhere.
		initHashMap(16);
	}

	private void initHashMap(int newN) {
		//Initialize the instance variables here
		size = 0;
		N = newN;
		
		//Gets the next prime number that is larger than N
		p = getNextPrime(N);
		
		//sets b to be a random number between 0 and p-1
		b = (int) (Math.random() * p);
		
		//sets a to be a random number between 1 and p-1
		a = (int) (Math.random() * (p - 1)) + 1;
		
		//creation of new LinkedList 
		map = new LinkedList[N];
		
		//sets all the values in the linkedlist to be new linkedlists 
		for (int i = 0; i < map.length; i++) 
		{
			map[i] = new LinkedList<>();
		}
	}

	private int getNextPrime(int x) {
		return (new BigInteger(Integer.toString(x))).nextProbablePrime().intValue();
	}

	private void expandIfNeeded() {
		if ((double) size/N > LOAD_FACTOR_THRESHOLD) {
			//saves the old values in the map to the entries variable
			Iterable<Entry<K,V>> entries = entrySet();
			
			//increases the size capacity
			initHashMap(N*2);
			
			//puts the entries back into the new hash map
			for (Entry<K,V> entry : entries) 
			{
				put(entry.getKey(),entry.getValue());
			}
		}
	}

	private int hash(K key) {
		return (Math.abs(key.hashCode() * a + b) % p) % N;
	}

	public V get(K key) {
		//finds the place the value should be found in the map
		int index = hash(key);
		
		//saves the linkedlist at map[index] to the variable bucket
		LinkedList<MapEntry> bucket = map[index];
		
		//checks to see if the bucket contains an element with this key, and if it does then return that value
		for (MapEntry entry : bucket) 
		{
			if (entry.key.equals(key)) 
			{
				return entry.value;
			}
		}
		return null;
	}

	public V put(K key, V value) {
		//finds the new place the value should be placed in the map
		int index = hash(key);
		
		//saves the linkedlist at map[index] to the variable bucket
		LinkedList<MapEntry> bucket = map[index];

		//checks to see if the bucket already contains an element with this key, and if it does then override that value in the bucket
		for (MapEntry entry : bucket) 
		{
			if (entry.key.equals(key)) 
			{
				return entry.setValue(value);
			}
		}
		
		//if it does not already exist within bucket then add it to bucket, increment the size and return the value
		bucket.add(new MapEntry(key, value));
		size++;
		//checks if we need to expand the array
		expandIfNeeded();
		return value;
	}

	public V remove(K key) {
		//finds the place the value should be found in the map
		int index = hash(key);
		
		//saves the linkedlist at map[index] to the variable bucket
		LinkedList<MapEntry> bucket = map[index];
		
		//checks to see if the bucket contains the element with this key, and if it does then delete the map entry and return the value found there
		for (MapEntry entry : bucket) 
		{
			if (entry.key.equals(key)) 
			{
				//saves the value in a temp variable and removes the MapEntry in that bucket
				V value = entry.value;
				bucket.remove(entry);
				size--;
				return value;
			}
		}
		return null;
	}

	public Iterable<K> keySet() {
		// TODO
		return null;
	}

	public Iterable<V> values() {
		// TODO
		return null;
	}

	public Iterable<Entry<K, V>> entrySet() {
		//adds all the entries in the hash map into the variable entries
		LinkedList<Entry<K,V>> entries = new LinkedList<>();
		
		for (LinkedList<MapEntry> bucket : map) 
		{ 
			for (MapEntry entry : bucket) 
			{
				entries.add(entry);
			}
		}
		return entries;
	}

	//He wants it to look like this
	/*
	 * 0: [( , ) ( ,)]
	 * 1: [( , ) ( ,)]
	 * 2: [( , ) ( ,)]
	 * 3: [( , ) ( ,)]
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i<map.length; i++) 
		{
			sb.append(i + ": [");
			for (MapEntry entry : map[i]) 
			{
				sb.append(entry.toString() + " ");
			}
			sb.append("]\n");
		}
		return sb.toString();
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

}
