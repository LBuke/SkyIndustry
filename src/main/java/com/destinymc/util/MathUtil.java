package com.destinymc.util;

/**
 * Created at 25/02/2019
 *
 * @author Luke Bingham
 */
public final class MathUtil {

    /**
     * Get the percentage between two points.
     *
     * @param current - Let's assume this is <b>5</b>
     * @param max - Let's assume this is <b>10</b>
     * @return The result would be <b>50</b>%
     */
    public static int getPercent(int current, int max) {
        return (int) (Math.round((current * 100 / max) * 10.0) / 10.0);
    }
}
