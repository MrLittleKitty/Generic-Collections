package com.gmail.nuclearcat1337.collections.generics;

/*
Created by Mr_Little_Kitty on 9/12/2015
*/
public class EqualityComparer implements IEqualityComparer
{
    public static final EqualityComparer DEFAULT = new EqualityComparer();

    private EqualityComparer()
    {

    }

    @Override
    public boolean Equals(final Object x, final Object y)
    {
        return x.equals(y);
       // if(x instanceof Comparable)
     //   return false;
    }

    @Override
    public int GetHashCode(final Object t)
    {
        return t.hashCode();
    }
}
