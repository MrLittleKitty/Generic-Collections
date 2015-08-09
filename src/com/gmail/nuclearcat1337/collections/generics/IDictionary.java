package com.gmail.nuclearcat1337.collections.generics;

/*
Created by Mr_Little_Kitty on 8/8/2015
*/
public abstract class IDictionary<Key extends Object,Value extends Object>
{
    public abstract Iterable<Key> Keys();

    public abstract Iterable<Value> Values();

    public abstract IReadOnlyCollection<KeyValuePair<Key,Value>> Entries();

    public abstract boolean ContainsKey(Key key);

    public abstract boolean Remove(Key key);
}
