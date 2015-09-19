package com.gmail.nuclearcat1337.collections.generics;

/*
Created by Mr_Little_Kitty on 8/8/2015
*/
public interface IReadOnlyDictionary<Key,Value > extends IReadOnlyCollection<KeyValuePair<Key,Value>>
{
    boolean containsValue(Value value);

    boolean containsKey(Key key);

    Value get(Key key);

    Iterable<Key> getKeys();

    Iterable<Value> getValues();
}
