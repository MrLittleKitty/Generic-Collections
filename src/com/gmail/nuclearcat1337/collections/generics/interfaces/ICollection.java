package com.gmail.nuclearcat1337.collections.generics.interfaces;

import java.util.Iterator;

/**
 * Created by Mr_Little_Kitty on 8/8/2015.
 */
public abstract class ICollection<T> implements Iterable<T>
{
    public abstract int getCount();

    public abstract void add(T item);

    public abstract void clear();

    public abstract boolean contains(T item);

    // CopyTo copies a collection into an Array, starting at a particular
    // index into the array.
    //
    public abstract void copyTo(T[] array, int arrayIndex);

    //void CopyTo(int sourceIndex, T[] destinationArray, int destinationIndex, int count);

    public abstract boolean remove(T item);

    public abstract Iterator<T> iterator();
}
