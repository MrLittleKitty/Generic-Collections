package com.gmail.nuclearcat1337.collections.generics;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

/*
Created by Mr_Little_Kitty on 9/22/2015
*/
public class ConcurrentQueue<T> extends IQueue<T> implements IReadOnlyCollection<T>
{
    private ConcurrentLinkedQueue<T> backingQueue;

    public ConcurrentQueue()
    {
        backingQueue = new ConcurrentLinkedQueue<T>();
    }

    public ConcurrentQueue(Collection<T> collection)
    {
        backingQueue = new ConcurrentLinkedQueue<T>(collection);
    }

    @Override
    public boolean enqueue(final T item)
    {
        return backingQueue.add(item);
    }

    @Override
    public T dequeue()
    {
        return backingQueue.poll();
    }

    @Override
    public int getCount()
    {
        return backingQueue.size();
    }

    @Override
    public void clear()
    {
        backingQueue.clear();
    }

    @Override
    public boolean contains(final T item)
    {
        return backingQueue.contains(item);
    }

    @Override
    public void copyTo(final T[] array, int arrayIndex)
    {
        for(T t : this)
        {
            array[arrayIndex] = t;
            arrayIndex++;
        }
    }

    @Override
    public boolean remove(final T item)
    {
        return backingQueue.remove(item);
    }

    @Override
    public Iterator<T> iterator()
    {
        return new ReadOnlyIterator<T>(backingQueue.iterator());
    }
}
