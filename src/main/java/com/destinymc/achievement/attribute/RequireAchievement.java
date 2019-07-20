package com.destinymc.achievement.attribute;

import java.lang.annotation.*;

/**
 * @author Luke Bingham
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface RequireAchievement {

    /**
     * This id value is the unique identifier of
     * the required Achievement.
     *
     * @return Achievement Identifier
     */
    int value();
}
