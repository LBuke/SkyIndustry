package com.destinymc.command;

import java.lang.annotation.*;

/**
 * Created at 15/02/2019
 * <p>
 * @author Luke Bingham
 */
@Documented
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface CommandName {

    /**
     * @return Base command label
     */
    String value();
}
