package util.map;

public interface Map<K, V> {

    void add(K key, V value, int freq);
    boolean contains(K key);
    V get(K key);
    void set(K key, V value);
    V remove(K key);
    int getSize();
    boolean isEmpty();
}
