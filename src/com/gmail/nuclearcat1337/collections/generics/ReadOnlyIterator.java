package com.gmail.nuclearcat1337.collections.generics;

import java.util.Iterator;

/*
Created by Mr_Little_Kitty on 9/22/2015
*/
class ReadOnlyIterator<T> implements Iterator<T>
{
    private Iterator<T> iterator;

    public ReadOnlyIterator(Iterator<T> iterator)
    {
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext()
    {
        return iterator.hasNext();
    }

    @Override
    public T next()
    {
        return iterator.next();
    }

    @Override
    public void remove()
    {
        throw new UnsupportedOperationException("You are not allowed to use this iterators remove function");
    }
}
