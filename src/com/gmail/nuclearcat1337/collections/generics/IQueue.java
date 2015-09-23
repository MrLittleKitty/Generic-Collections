package com.gmail.nuclearcat1337.collections.generics;

/*
Created by Mr_Little_Kitty on 9/22/2015
*/
public abstract class IQueue<T> extends ICollection<T>
{
    public abstract boolean enqueue(T item);

    @Override
    public void add(T item)
    {
        enqueue(item);
    }

    public abstract T dequeue();
}
