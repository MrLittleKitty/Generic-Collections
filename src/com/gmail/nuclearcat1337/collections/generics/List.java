package com.gmail.nuclearcat1337.collections.generics;

import java.util.Iterator;

/*
Created by Mr_Little_Kitty on 8/8/2015
*/
public class List<T> extends IList<T> implements IReadOnlyList<T>
{
    @Override
    public T Get(final int index)
    {
        return null;
    }

    @Override
    public int IndexOf(final T item)
    {
        return 0;
    }

    @Override
    public void Insert(final int index, final T item)
    {

    }

    @Override
    public void RemoveAt(final int index)
    {

    }

    @Override
    public int Count()
    {
        return 0;
    }

    @Override
    public void Add(final T item)
    {

    }

    @Override
    public void Clear()
    {

    }

    @Override
    public boolean Contains(final T item)
    {
        return false;
    }

    @Override
    public void CopyTo(final T[] array, final int arrayIndex)
    {

    }

    @Override
    public boolean Remove(final T item)
    {
        return false;
    }

    @Override
    public Iterator<T> iterator()
    {
        return null;
    }
}
