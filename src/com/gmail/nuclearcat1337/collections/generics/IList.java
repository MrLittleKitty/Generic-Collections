package com.gmail.nuclearcat1337.collections.generics;

/**
 * Created by Mr_Little_Kitty on 8/8/2015.
 */
public abstract class IList<T> extends ICollection<T>
{
    public abstract T Get(int index);

    public abstract int IndexOf(T item);

    public abstract void Insert(int index, T item);

    public abstract void RemoveAt(int index);
}
