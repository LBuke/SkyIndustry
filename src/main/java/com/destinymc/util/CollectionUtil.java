package com.destinymc.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created at 18/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class CollectionUtil {

    public static <E, T extends List<E>> T combineLists(T one, T two) {
        List<E> newList = new ArrayList<E>();
        newList.addAll(one);
        newList.addAll(two);
        return (T) newList;
    }
}
