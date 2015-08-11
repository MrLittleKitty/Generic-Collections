package com.gmail.nuclearcat1337.collections.generics;

import com.gmail.nuclearcat1337.collections.generics.interfaces.ICollection;
import com.gmail.nuclearcat1337.collections.generics.interfaces.IDictionary;
import com.gmail.nuclearcat1337.collections.generics.interfaces.IEqualityComparer;
import com.gmail.nuclearcat1337.collections.generics.interfaces.IReadOnlyCollection;
import com.gmail.nuclearcat1337.collections.generics.interfaces.IReadOnlyDictionary;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;

/*
Created by Mr_Little_Kitty on 8/8/2015
*/
public class Dictionary<Key,Value> extends IDictionary<Key,Value> implements IReadOnlyDictionary<Key,Value>
{
    private int[] buckets;
    private Entry<Key,Value>[] entries;
    private int count;
    private int version;
    private int freeList;
    private int freeCount;
    private IEqualityComparer<Key> comparer;

    //private KeyCollection keys;
    //private ValueCollection values;

    public Dictionary()
    {
        this(0,null);
    }

    public Dictionary(int capacity)
    {
        this(capacity,null);
    }

    public Dictionary(int capactiy, IEqualityComparer<Key> comparer)
    {
        if(capactiy < 0)
            throw new IllegalArgumentException("Capacity must be greater than zero");
        if(capactiy > 0)
            initialize(capactiy);
        this.comparer = comparer != null ? comparer : null;//The default comparer should be used here
    }

    public IEqualityComparer getComparer()
    {
        return comparer;
    }

    private void initialize(int capacity)
    {
        int size = HashUtil.getPrime(capacity);//TODO--Prime size
        buckets = new int[size];
        for(int i = 0; i < buckets.length; i++ )
            buckets[i] = -1;
        entries = new Entry[size];
        freeList = -1;
    }

    private int findEntry(Key key)
    {
        if(key == null)
            throw new IllegalArgumentException("key cannot be null");

        if (buckets != null)
        {
            int hashCode = comparer.GetHashCode(key) & 0x7FFFFFFF;
            for (int i = buckets[hashCode % buckets.length]; i >= 0; i = entries[i].next)
            {
                if (entries[i].hashCode == hashCode && comparer.Equals(entries[i].key, key))
                    return i;
            }
        }
        return -1;
    }

    private void insert(Key key, Value value, boolean add)
    {
        if (key == null)
            throw new IllegalArgumentException("key cannot be null");
        if (buckets == null)
            initialize(0);
        int hashCode = comparer.GetHashCode(key) & 0x7FFFFFFF;
        int targetBucket = hashCode % buckets.length;

        for (int i = buckets[targetBucket]; i >= 0; i = entries[i].next)
        {
            if (entries[i].hashCode == hashCode && comparer.Equals(entries[i].key, key))
            {
                if (add)
                    throw new IllegalArgumentException("Attempted to add items with duplicate keys");
                entries[i].value = value;
                version++;
                return;
            }
        }

        int index;
        if (freeCount > 0)
        {
            index = freeList;
            freeList = entries[index].next;
            freeCount--;
        }
        else
        {
            if (count == entries.length)
            {
                //Resize();
                targetBucket = hashCode % buckets.length;
            }
            index = count;
            count++;
        }

        entries[index].hashCode = hashCode;
        entries[index].next = buckets[targetBucket];
        entries[index].key = key;
        entries[index].value = value;
        buckets[targetBucket] = index;
        version++;
    }

    @Override
    public Value get(final Key key)
    {
        int i = findEntry(key);
        if (i >= 0)
            return entries[i].value;
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
    public boolean containsKey(final Key key)
    {
        return findEntry(key) >= 0;
    }

    @Override
    public boolean remove(final Key key)
    {
        if(key == null)
            throw new IllegalArgumentException("key cannot be null");


        if (buckets != null)
        {
            int hashCode = comparer.GetHashCode(key) & 0x7FFFFFFF;
            int bucket = hashCode % buckets.length;
            int last = -1;
            for (int i = buckets[bucket]; i >= 0; last = i, i = entries[i].next)
            {
                if (entries[i].hashCode == hashCode && comparer.Equals(entries[i].key, key))
                {
                    if (last < 0)
                        buckets[bucket] = entries[i].next;
                    else
                        entries[last].next = entries[i].next;
                    entries[i].hashCode = -1;
                    entries[i].next = freeList;
                    entries[i].key = null;
                    entries[i].value = null;
                    freeList = i;
                    freeCount++;
                    version++;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void clear()
    {
        if (count > 0)
        {
            for (int i = 0; i < buckets.length; i++)
                buckets[i] = -1;
            ClearArray(entries,0,count);
            freeList = -1;
            count = 0;
            freeCount = 0;
            version++;
        }
    }

    @Override
    public void add(final Key key, final Value value)
    {
        insert(key,value,true);
    }

    private void ClearArray(Object[] array, int startIndex, int length)
    {
        int topIndex = startIndex+length;
        for(int index = startIndex; index < topIndex; index++)
            array[index] = null;
    }

    @Override
    public int getCount()
    {
        return count - freeCount;
    }

    @Override
    public Iterator<KeyValuePair<Key, Value>> iterator()
    {
        return null;
    }

    private class KeyCollection
    {

    }

    private class ValueCollection
    {

    }




    private class Entry<Key,Value>
    {
        public int hashCode;
        public int next;
        public Key key;
        public Value value;
    }
}
