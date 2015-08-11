package com.gmail.nuclearcat1337.collections.generics.interfaces;

/**
 * Created by Mr_Little_Kitty on 8/10/2015.
 */
public interface IEqualityComparer<T>
{
    boolean Equals(T x, T y);
    int GetHashCode(T t);
}
