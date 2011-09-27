package fi.evident.enlight.utils;

import java.util.Iterator;

/**
 * An iterator that allows peeking the next element without consuming it.
 */
public final class PeekableIterator<T> implements Iterator<T> {

    private boolean peeked = false;
    private T peekedValue;
    private final Iterator<T> iterator;

    public PeekableIterator(Iterator<T> iterator) {
        if (iterator == null) throw new NullPointerException();

        this.iterator = iterator;
    }
    
    @Override
    public boolean hasNext() {
        return peeked || iterator.hasNext();
    }

    @Override
    public T next() {
        if (!hasNext()) throw new IllegalStateException();

        if (peeked) {
            peeked = false;
            return peekedValue;
        } else {
            return iterator.next();
        }
    }
    
    public T peek() {
        if (!hasNext()) throw new IllegalStateException();

        if (!peeked) {
            peeked = true;
            peekedValue = iterator.next();
        }
        return peekedValue;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
