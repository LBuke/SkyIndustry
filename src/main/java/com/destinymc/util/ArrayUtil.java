package com.destinymc.util;

import java.util.Arrays;
import java.util.List;

/**
 * Created at 18/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class ArrayUtil {

    public static <T> boolean containsElement(T o, T... array) {
        if (array == null || array.length == 0)
            return false;

        for (T e : array)
            if (e == o)
                return true;

        return false;
    }
}
