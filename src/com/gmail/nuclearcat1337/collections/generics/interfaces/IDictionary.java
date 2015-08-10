package com.gmail.nuclearcat1337.collections.generics.interfaces;

import com.gmail.nuclearcat1337.collections.generics.KeyValuePair;

/*
Created by Mr_Little_Kitty on 8/8/2015
*/
public abstract class IDictionary<Key extends Object,Value extends Object>
{
    public abstract Iterable<Key> getKeys();

    public abstract Iterable<Value> getValues();

    public abstract IReadOnlyCollection<KeyValuePair<Key,Value>> getEntries();

    public abstract boolean ContainsKey(Key key);

    public abstract boolean Remove(Key key);
}
