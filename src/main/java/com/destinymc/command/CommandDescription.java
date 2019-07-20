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
public @interface CommandDescription {

    /**
     * Description of the command, Will be shown in the help menu
     *
     * @return Description of the command
     */
    String value();
}
