package test.test8;

public class Map<K, V> {

	private K[] keys;
	private V[] values;

	public Map(K[] keys, V[] values) {
		this.keys = keys;
		this.values = values;
	}

	public V getValueByKey(K key) {
		// logic
		return values[0];
	}

}
