package com.gmail.nuclearcat1337.collections.generics;

/*
Created by Mr_Little_Kitty on 8/8/2015
*/
public interface IReadOnlyDictionary<Key extends Object,Value extends Object> extends IReadOnlyCollection<KeyValuePair<Key,Value>>
{
    boolean ContainsKey(Key key);

    Value Get(Key key);

    Iterable<Key> Keys();

    Iterable<Value> Values();
}
