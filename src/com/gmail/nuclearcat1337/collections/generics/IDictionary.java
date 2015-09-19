package com.gmail.nuclearcat1337.collections.generics;

/*
Created by Mr_Little_Kitty on 8/8/2015
*/
public abstract class IDictionary<Key,Value>
{
    public abstract IReadOnlyCollection<Key> getKeys();

    public abstract IReadOnlyCollection<Value> getValues();

    public abstract IReadOnlyCollection<KeyValuePair<Key,Value>> getEntries();

    public abstract boolean containsKey(Key key);

    public abstract boolean containsValue(Value value);

    public abstract boolean remove(Key key);

    public abstract void clear();

    public abstract void add(Key key, Value value);
}
