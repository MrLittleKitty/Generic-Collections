package com.gmail.nuclearcat1337.collections.generics;

import java.util.ArrayList;
import java.util.Iterator;

/*
Created by Mr_Little_Kitty on 8/8/2015
*/
public class List<T> extends IList<T> implements IReadOnlyList<T>
{
    private final ArrayList<T> items;

    public List()
    {
        items = new ArrayList<T>();
    }

    public List(int capacity)
    {
        items = new ArrayList<T>(capacity);
    }

    @Override
    public T get(final int index)
    {
        return items.get(index);
    }

    @Override
    public int indexOf(final T item)
    {
        return items.indexOf(item);
    }

    @Override
    public void insert(final int index, final T item)
    {
        items.add(index,item);
    }

    @Override
    public void removeAt(final int index)
    {
        items.remove(index);
    }

    @Override
    public int getCount()
    {
        return items.size();
    }

    @Override
    public void add(final T item)
    {
        items.add(item);
    }

    @Override
    public void clear()
    {
        items.clear();
    }

    @Override
    public boolean contains(final T item)
    {
        return items.contains(item);
    }

    @Override
    public void copyTo(final T[] array, final int arrayIndex)
    {
        int size = getCount();
        for(int i = 0; i < size;i++)
        {
            array[arrayIndex+i] = get(i);
        }
    }

    @Override
    public boolean remove(final T item)
    {
        return items.remove(item);
    }

    @Override
    public Iterator<T> iterator()
    {
        return new Enumerator(this);
    }

    private class Enumerator implements Iterator<T>
    {
        private List<T> list;
        private int index;

        public Enumerator(List<T> list)
        {
            this.list = list;
            index = -1;
        }

        @Override
        public boolean hasNext()
        {
            return index < list.getCount()-1;
        }

        @Override
        public T next()
        {
            index++;
            return list.get(index);
        }

        @Override
        public void remove()
        {
            throw new UnsupportedOperationException("You are not allowed to remove items using this iterator");
        }
    }
}
