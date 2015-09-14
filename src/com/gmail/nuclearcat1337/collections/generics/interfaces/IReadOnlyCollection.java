package com.gmail.nuclearcat1337.collections.generics.interfaces;

import java.util.Iterator;

/**
 * Created by Mr_Little_Kitty on 8/8/2015.
 */
public interface IReadOnlyCollection<E> extends Iterable<E>
{
    boolean contains(E item);

    int getCount();

    Iterator<E> iterator();
}
