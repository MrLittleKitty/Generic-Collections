package com.gmail.nuclearcat1337.collections.generics;

/**
 * Created by Mr_Little_Kitty on 8/8/2015.
 */
public interface IReadOnlyList<E> extends IReadOnlyCollection<E>
{
    E Get(int index);
}
