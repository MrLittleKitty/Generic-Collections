package com.gmail.nuclearcat1337.collections.generics.interfaces;

/**
 * Created by Mr_Little_Kitty on 8/8/2015.
 */
public interface IReadOnlyList<E> extends IReadOnlyCollection<E>
{
    E get(int index);
}
