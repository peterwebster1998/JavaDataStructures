import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;

public class ArrayListMap<K,V> implements Map<K,V> {
	
	private class ArrayListMapEntry<K,V> implements Entry<K,V> {
		private K key;
		private V value;
		
		public ArrayListMapEntry(K k, V v) {
			key = k;
			value = v;
		}
		
		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		@Override
		public V setValue(V value) {
			V oldValue = value;
			this.value = value;
			return oldValue;
		}
		
		public String toString() {
			return "(" + key + ", " + value + ")";
		}
	}
	
	private ArrayList<ArrayListMapEntry<K,V>> map;
	private int size;
	
	public ArrayListMap() {
		map = new ArrayList<>();
		size = 0;
	}

	@Override
	public V get(K key) {
		for(ArrayListMapEntry<K,V> entry : map) {
			if(entry.key.equals(key)) {
				return entry.value;
			}
		}
		System.out.println("Key does not exist within this map");
		return null;
	}

	@Override
	public V put(K key, V value) {
		for(ArrayListMapEntry<K,V> entry : map) {
			if(entry.key.equals(key)) {
				V oldVal = entry.value;
				entry.value = value;
				return oldVal;
			}
		}
		map.add(new ArrayListMapEntry<>(key, value));
		size++;
		return value;
	}

	@Override
	public V remove(K key) {
		// Hint: using an iterator is easier than a for-each loop
		Iterator<ArrayListMapEntry<K,V>> iter = map.iterator();
		while(iter.hasNext()) {
			ArrayListMapEntry<K,V> entry = iter.next();
			if(entry.key.equals(key)) {
				V value = entry.value;
				iter.remove();
				size--;
				return value;
			}
		}
		return null;
	}

	@Override
	public Iterable<K> keySet() {
		// Use ArrayList here as well
		ArrayList<K> keys = new ArrayList<>();
		for(ArrayListMapEntry<K,V> entry : map) {
			keys.add(entry.key);
		}
		return keys;
	}

	@Override
	public Iterable<V> values() {
		// Use ArrayList here as well
		ArrayList<V> vals = new ArrayList<>();
		for(ArrayListMapEntry<K,V> entry : map) {
			vals.add(entry.value);
		}
		return vals;
	}

	@Override
	public Iterable<Entry<K, V>> entrySet() {
		// TODO
		// Use ArrayList here as well
		return null;
	}

	@Override
	public int size() {
		return size; // or return map.size();
	}

	@Override
	public boolean isEmpty() {
		return size == 0; // or return map.isEmpty();
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (ArrayListMapEntry<K,V> entry : map) {
			sb.append(entry.toString() + " ");
		}
		return sb.toString();
	}

}
