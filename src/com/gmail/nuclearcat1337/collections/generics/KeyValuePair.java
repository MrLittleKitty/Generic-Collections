package com.gmail.nuclearcat1337.collections.generics;

/*
Created by Mr_Little_Kitty on 8/8/2015
*/
public class KeyValuePair<T,U>
{
    private T key;
    private U value;

    public KeyValuePair(T key, U value)
    {
        this.key = key;
        this.value = value;
    }

    T Key()
    {
        return key;
    }

    U Value()
    {
        return value;
    }
}
