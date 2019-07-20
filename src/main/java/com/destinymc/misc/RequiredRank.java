package com.destinymc.misc;

import com.destinymc.rank.Rank;

import java.lang.annotation.*;

/**
 * @author Luke Bingham
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface RequiredRank {

    Rank value();
}
