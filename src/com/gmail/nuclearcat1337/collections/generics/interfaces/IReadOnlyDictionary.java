package com.gmail.nuclearcat1337.collections.generics.interfaces;

import com.gmail.nuclearcat1337.collections.generics.KeyValuePair;

/*
Created by Mr_Little_Kitty on 8/8/2015
*/
public interface IReadOnlyDictionary<Key extends Object,Value extends Object> extends IReadOnlyCollection<KeyValuePair<Key,Value>>
{
    boolean containsKey(Key key);

    Value get(Key key);

    Iterable<Key> getKeys();

    Iterable<Value> getValues();
}
