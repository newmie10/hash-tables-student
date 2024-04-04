package hashtables;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class HashTableIterator<E> implements Iterator<E> {
    private LinkedList<E>[] table;
    private int currentIndex;
    private Iterator<E> currentIterator;

    public HashTableIterator(LinkedList<E>[] table) {
        this.table = table;
        currentIndex = 0;
        currentIterator = null;
        findNextNonNullList();
    }

    public boolean hasNext() {
        // If there's a current iterator and it has next elements, return true
        if (currentIterator != null && currentIterator.hasNext()) {
            return true;
        }
        // If the current iterator is null or doesn't have next elements, try to find the next non-null list
        while (currentIndex < table.length) {
            currentIndex++;
            findNextNonNullList();
            if (currentIterator != null) {
                return true;
            }
        }
        // If no more non-null lists found, return false
        return false;
    }

    @Override
    public E next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more elements in the hash table");
        }

        // While the current iterator has no more elements, find the next non-null list
        while (!currentIterator.hasNext()) {
            currentIndex++;
            findNextNonNullList();
        }
        
        // Return the next element from the current iterator
        return currentIterator.next();
    }


    private void findNextNonNullList() {
        while (currentIndex < table.length && (table[currentIndex] == null || table[currentIndex].isEmpty())) {
            currentIndex++;
        }
        if (currentIndex < table.length) {
            currentIterator = table[currentIndex].iterator();
        } else {
            currentIterator = null;
        }
    }

    private Boolean checkNextList() {
        int currentI = currentIndex + 1;
        while (currentI < table.length) {
            if (table[currentI] != null && !table[currentI].isEmpty()) {
                return true;
            }
            currentI++;
        }
        return false;
    }
}
