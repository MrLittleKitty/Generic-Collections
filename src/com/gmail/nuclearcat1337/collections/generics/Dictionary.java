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

    private void copy(Object[] sourceArray, int sourceIndex, Object[] destinationArray, int destinationIndex, int length)
    {
        //int max =
        //for(int i = )
    }

    private void Resize()
    {
        Resize(HashUtil.expandPrime(count), false);
    }

    private void Resize(int newSize, bool forceNewHashCodes)
    {
        //Contract.Assert(newSize >= entries.Length);
        int[] newBuckets = new int[newSize];
        for (int i = 0; i < newBuckets.length; i++) newBuckets[i] = -1;
        Entry[] newEntries = new Entry[newSize];
        Arrays.copy  Copy(entries, 0, newEntries, 0, count);
        if(forceNewHashCodes) {
            for (int i = 0; i < count; i++) {
                if(newEntries[i].hashCode != -1) {
                    newEntries[i].hashCode = (comparer.GetHashCode(newEntries[i].key) & 0x7FFFFFFF);
                }
            }
        }
        for (int i = 0; i < count; i++) {
            if (newEntries[i].hashCode >= 0) {
                int bucket = newEntries[i].hashCode % newSize;
                newEntries[i].next = newBuckets[bucket];
                newBuckets[bucket] = i;
            }
        }
        buckets = newBuckets;
        entries = newEntries;
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

    private class Enumerator implements Iterator<KeyValuePair<Key,Value>>
    {
        private Dictionary<Key,Value> dictionary;
        private int index;

        public Enumerator(Dictionary<Key,Value> dictionary)
        {
            this.dictionary = dictionary;
            index = 0;
        }

        @Override
        public boolean hasNext()
        {
            return index < dictionary.getCount()-1;
        }

        @Override
        public KeyValuePair<Key, Value> next()
        {
            if(!hasNext())
                return null;
            index++;
            return new KeyValuePair<Key, Value>(dictionary.entries[index].key,dictionary.entries[index].value);
        }

        @Override
        public void remove()
        {
            throw new UnsupportedOperationException("You are not allowed to modify this collection.");
        }
    }

    private final class KeyCollection extends ICollection<Key> implements IReadOnlyCollection<Key>
    {
        private Dictionary<Key,Value> dictionary;

        public KeyCollection(Dictionary<Key,Value> dictionary)
        {
            if(dictionary == null)
                throw new IllegalArgumentException("dictionary can not be null");
            this.dictionary = dictionary;
        }

        @Override
        public int getCount()
        {
            return dictionary.getCount();
        }

        @Override
        public void add(final Key item)
        {
            throw new UnsupportedOperationException("You are not allowed to modify a KeyCollection");
        }

        @Override
        public void clear()
        {
            throw new UnsupportedOperationException("You are not allowed to modify a KeyCollection");
        }

        @Override
        public boolean contains(final Key item)
        {
            return dictionary.containsKey(item);
        }

        @Override
        public void copyTo(final Key[] array, int arrayIndex)
        {
            if (array == null)
                throw new IllegalArgumentException("The given array cannot be null.");

            if (arrayIndex < 0 || arrayIndex > array.length)
                throw new ArrayIndexOutOfBoundsException("The given index was out of range");

            if (array.length - arrayIndex < dictionary.getCount())
                throw new ArrayIndexOutOfBoundsException("There is not a space in the array to fit the items");


            int count = dictionary.count;
            //Entry[] entries = dictionary.entries;
            for (int i = 0; i < count; i++)
            {
                if (entries[i].hashCode >= 0)
                    array[arrayIndex++] = entries[i].key;
            }
        }

        @Override
        public boolean remove(final Key item)
        {
            throw new UnsupportedOperationException("You are not allowed to modify a KeyCollection");
        }

        @Override
        public Iterator<Key> iterator()
        {
            return new KeyEnumerator<Key>(dictionary);
        }
    }

    private class ValueCollection extends ICollection<Value> implements IReadOnlyCollection<Value>
    {
        private Dictionary<Key,Value> dictionary;

        public ValueCollection(Dictionary<Key,Value> dictionary)
        {
            this.dictionary = dictionary;
        }

        @Override
        public int getCount()
        {
            return dictionary.getCount();
        }

        @Override
        public void add(final Value item)
        {
            throw new UnsupportedOperationException("You are not allowed to modify a ValueCollection");
        }

        @Override
        public void clear()
        {
            throw new UnsupportedOperationException("You are not allowed to modify a ValueCollection");
        }

        @Override
        public boolean contains(final Value item)
        {
            return dictionary.containsValue(item);
        }

        @Override
        public void copyTo(final Value[] array, int arrayIndex)
        {
            if (array == null)
                throw new IllegalArgumentException("The given array cannot be null.");

            if (arrayIndex < 0 || arrayIndex > array.length)
                throw new ArrayIndexOutOfBoundsException("The given index was out of range");

            if (array.length - arrayIndex < dictionary.getCount())
                throw new ArrayIndexOutOfBoundsException("There is not a space in the array to fit the items");


            int count = dictionary.count;
            //Entry[] entries = dictionary.entries;
            for (int i = 0; i < count; i++)
            {
                if (entries[i].hashCode >= 0)
                    array[arrayIndex++] = entries[i].value;
            }
        }

        @Override
        public boolean remove(final Value item)
        {
            throw new UnsupportedOperationException("You are not allowed to modify a ValueCollection");
        }

        @Override
        public Iterator<Value> iterator()
        {
            return new ValueEnumerator<Value>(dictionary);
        }
    }

    private final class ValueEnumerator<Value> implements Iterator<Value>
    {
        private Dictionary<Key,Value> dictionary;
        private int index;

        public ValueEnumerator(Dictionary<Key,Value> dictionary)
        {
            this.dictionary = dictionary;
            index = 0;
        }

        @Override
        public boolean hasNext()
        {
            return index < dictionary.getCount()-1;
        }

        @Override
        public Value next()
        {
            if(!hasNext())
                return null;
            index++;
            return dictionary.entries[index].value;
        }

        @Override
        public void remove()
        {
            throw new UnsupportedOperationException("You are not allowed to modify a value collection");
        }
    }

    private final class KeyEnumerator<Key> implements Iterator<Key>
    {
        private Dictionary<Key,Value> dictionary;
        private int index;

        public KeyEnumerator(Dictionary<Key,Value> dictionary)
        {
            this.dictionary = dictionary;
            index = 0;
        }

        @Override
        public boolean hasNext()
        {
            return index < dictionary.getCount()-1;
        }

        @Override
        public Key next()
        {
            if(!hasNext())
                return null;
            index++;
            return dictionary.entries[index].key;
        }

        @Override
        public void remove()
        {
            throw new UnsupportedOperationException("You are not allowed to modify a key collection");
        }
    }

    private final class Entry
    {
        public int hashCode;
        public int next;
        public Key key;
        public Value value;
    }
}
