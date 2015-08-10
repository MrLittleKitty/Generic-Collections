package com.gmail.nuclearcat1337.collections.generics;

import com.gmail.nuclearcat1337.collections.generics.interfaces.IDictionary;
import com.gmail.nuclearcat1337.collections.generics.interfaces.IReadOnlyCollection;
import com.gmail.nuclearcat1337.collections.generics.interfaces.IReadOnlyDictionary;

import java.util.Iterator;

/*
Created by Mr_Little_Kitty on 8/8/2015
*/
public class Dictionary<Key,Value> extends IDictionary<Key,Value> implements IReadOnlyDictionary<Key,Value>
{
    private int[] buckets;
    private Entry[] entries;
    private int count;
    private int version;
    private int freeList;
    private int freeCount;
    //private IEqualityComparer<TKey> comparer;
    //private KeyCollection keys;
    //private ValueCollection values;


    public Dictionary()
    {

    }

    public Dictionary(int capacity)
    {

    }

    public Dictionary(int capacity,float loadFactor)
    {

    }

    @Override
    public boolean containsKey(final Key key)
    {
        return false;
    }

    @Override
    public Value get(final Key key)
    {
        return null;
    }

    @Override
    public Iterable<Key> getKeys()
    {
        return null;
    }

    @Override
    public Iterable<Value> getValues()
    {
        return null;
    }

    @Override
    public IReadOnlyCollection<KeyValuePair<Key, Value>> getEntries()
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
    public int getCount()
    {
        return 0;
    }

    @Override
    public Iterator<KeyValuePair<Key, Value>> iterator()
    {
        return null;
    }

    private class Entry<Key,Value>
    {
        public int hashCode;
        public int next;
        public Key key;
        public Value value;
    }
}
