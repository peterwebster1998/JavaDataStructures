import java.util.Map.Entry;

public interface Map<K, V> {
	public V get(K key);
	public V put(K key, V value);
	public V remove(K key);
	public Iterable<K> keySet();
	public Iterable<V> values();
	public Iterable<Entry<K,V>> entrySet();
	public int size();
	public boolean isEmpty();
}
