package hashtables;

import java.util.Iterator;
import java.util.LinkedList;

public class ChainingHashTable<E> implements HashTable<E> {
    
    private int capacity;
    private int size;
    private double loadFactor;
    public LinkedList<E>[] table;
    private int nmap;
    
    /**
     * Instantiate a new hash table. The initial capacity should be 7.
     */
    public ChainingHashTable() {
        this(7);
    }

    /**
     * Instantiate a new hash table. The initial capacity should be 
     * at least sufficient to hold n elements, but must be one less
     * than a power of two.
     */
    public ChainingHashTable(int n) {
        int localCap = (int) Math.max(Math.pow(2, Math.ceil((Math.log(n) / Math.log(2)))) - 1, 3);
        this.table = new LinkedList[localCap];
        this.capacity = localCap;
        this.size = 0;
        this.loadFactor = 0.0;
        this.nmap = 3;
    }

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public double loadFactor() {
        return loadFactor;
    }

    @Override
    public boolean add(E e) {
        if (loadFactor > 0.75) {
            nmap++;
            capacity = (int) Math.pow(2, nmap) - 1;
            LinkedList<E>[] temp = table;
            table = new LinkedList[capacity];

            for (LinkedList<E> list : temp) {
                if (list != null) {
                    for (E element : list) {
                        int index = Math.abs(element.hashCode() % capacity);
                        if (table[index] == null) {
                            table[index] = new LinkedList<>();
                        }
                        table[index].add(element);
                    }
                }
            }
        }

        int index = Math.abs(e.hashCode()) % capacity;

        if (table[index] == null) {
            table[index] = new LinkedList<>();
        }

        if (!table[index].contains(e)) {
            table[index].add(e);
            size++;
            loadFactor = (double) size / capacity;
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(E e) {
        int index = Math.abs(e.hashCode()) % capacity;

        if (table[index] != null && table[index].remove(e)) {
            size--;
            loadFactor = (double) size / capacity;
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(E e) {
        int index = Math.abs(e.hashCode()) % capacity;
        return table[index] != null && table[index].contains(e);
    }

    @Override
    public E get(E e) {
        int index = Math.abs(e.hashCode()) % capacity;
        LinkedList<E> list = table[index];
        if (list != null && list.contains(e)) {
            return list.get(list.indexOf(e));
        }
        return null;
    }

    @Override
    public Iterator<E> iterator() {
        return new HashTableIterator<>(table);
    }
}
