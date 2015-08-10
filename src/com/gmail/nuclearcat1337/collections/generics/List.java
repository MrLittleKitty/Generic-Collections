package com.gmail.nuclearcat1337.collections.generics;

import com.gmail.nuclearcat1337.collections.generics.interfaces.IList;
import com.gmail.nuclearcat1337.collections.generics.interfaces.IReadOnlyList;

import java.util.ArrayList;
import java.util.Iterator;

/*
Created by Mr_Little_Kitty on 8/8/2015
*/
public class List<T extends Object> extends IList<T> implements IReadOnlyList<T>
{
    private ArrayList<T> items;

    public List()
    {
        items = new ArrayList<T>();
    }

    public List(int capacity)
    {
        items = new ArrayList<T>(capacity);
    }

    @Override
    public T Get(final int index)
    {
        return items.get(index);
    }

    @Override
    public int IndexOf(final T item)
    {
        return items.indexOf(item);
    }

    @Override
    public void Insert(final int index, final T item)
    {
        items.add(index,item);
    }

    @Override
    public void RemoveAt(final int index)
    {
        items.remove(index);
    }

    @Override
    public int Count()
    {
        return items.size();
    }

    @Override
    public void Add(final T item)
    {
        items.add(item);
    }

    @Override
    public void Clear()
    {
        items.clear();
    }

    @Override
    public boolean Contains(final T item)
    {
        return items.contains(item);
    }

    @Override
    public void CopyTo(final T[] array, final int arrayIndex)
    {
//        for(int i = 0; i <)
//        {
//            array[arrayIndex] = items.get()
//        }
    }

    @Override
    public boolean Remove(final T item)
    {
        return items.remove(item);
    }

    @Override
    public Iterator<T> iterator()
    {
        return items.iterator();
    }
}
