package com.gmail.nuclearcat1337.collections.generics;

import java.util.Iterator;

/**
 * Created by Mr_Little_Kitty on 8/8/2015.
 */
public abstract class ICollection<T> implements Iterable<T>
{
    public abstract int Count();

    public abstract void Add(T item);

    public abstract void Clear();

    public abstract boolean Contains(T item);

    // CopyTo copies a collection into an Array, starting at a particular
    // index into the array.
    //
    public abstract void CopyTo(T[] array, int arrayIndex);

    //void CopyTo(int sourceIndex, T[] destinationArray, int destinationIndex, int count);

    public abstract boolean Remove(T item);

    public abstract Iterator<T> iterator();
}