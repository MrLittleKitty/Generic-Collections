package com.gmail.nuclearcat1337.collections.generics;

import java.util.Iterator;

/*
Created by Mr_Little_Kitty on 8/8/2015
*/
public class Dictionary<Key,Value> extends IDictionary<Key,Value> implements IReadOnlyDictionary<Key,Value>
{

    @Override
    public Iterable<Key> Keys()
    {
        return null;
    }

    @Override
    public Iterable<Value> Values()
    {
        return null;
    }

    @Override
    public IReadOnlyCollection<KeyValuePair<Key, Value>> Entries()
    {
        return null;
    }

    @Override
    public boolean ContainsKey(final Key key)
    {
        return false;
    }

    @Override
    public boolean Remove(final Key key)
    {
        return false;
    }

    @Override
    public Value Get(final Key key)
    {
        return null;
    }

    @Override
    public int Count()
    {
        return 0;
    }

    @Override
    public Iterator<KeyValuePair<Key, Value>> iterator()
    {
        return null;
    }
}
