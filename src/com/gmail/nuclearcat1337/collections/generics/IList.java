package com.gmail.nuclearcat1337.collections.generics;

/**
 * Created by Mr_Little_Kitty on 8/8/2015.
 */
public abstract class IList<T> extends ICollection<T>
{
    public abstract T get(int index);

    public abstract int indexOf(T item);

    public abstract void insert(int index, T item);

    public abstract void removeAt(int index);
}
