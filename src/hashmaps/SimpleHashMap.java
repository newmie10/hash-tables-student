/*
 * Copyright 2023 Marc Liberatore.
 */
package hashmaps;

import hashtables.ChainingHashTable;
import java.util.Set;
import java.util.HashSet;




/**
 * An implementation of a SimpleMap, built using the ChainingHashTable and 
 * SimpleMapEntry classes. This class should behave similarly to the built-in
 * java.util.HashMap, though it is much simpler!
 */
public class SimpleHashMap<K, V> implements SimpleMap<K, V> {
    private ChainingHashTable<SimpleMapEntry<K, V>> table;
    int size1 = 0;

    public SimpleHashMap() {
        table = new ChainingHashTable<>();
    }

    @Override
    public int size() {
        return table.size();
    }

    @Override
    public void put(K k, V v) {
        SimpleMapEntry<K, V> entry = new SimpleMapEntry<>(k, v);
        if (table.get(entry) != null) {
            table.remove(entry);
        }
        table.add(entry);
    }

    @Override
    public V get(K k) {
        SimpleMapEntry<K, V> entry1 = new SimpleMapEntry<>(k, null);
        if (table.get(entry1) == null) {
            return null;
        }
        return table.get(entry1).v;
    }

    @Override
    public V getOrDefault(K k, V defaultValue) {
        SimpleMapEntry<K, V> entry1 = new SimpleMapEntry<>(k, null);
        if (table.get(entry1) == null) {
            table.add(new SimpleMapEntry<>(k, defaultValue));
            return defaultValue;
        }
        return (table.get(entry1).v);
    }

    @Override
    public V remove(K k) {
        SimpleMapEntry<K, V> entry1 = new SimpleMapEntry<>(k, null);
        if (table.get(entry1) == null) {
            return null;
        }
        V ret = table.get(entry1).v;
        table.remove(entry1);
        return ret;
    }

    @Override
    public Set<K> keys() {
        Set<K> keys = new HashSet<>();
        for (SimpleMapEntry<K, V> entry : table) {
            keys.add(entry.k);
        }
        return keys;
    }    
}
