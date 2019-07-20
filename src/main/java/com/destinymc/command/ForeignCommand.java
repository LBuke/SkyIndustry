package com.destinymc.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created at 15/02/2019
 * <p>
 * @author Luke Bingham
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ForeignCommand {

    /**
     * This is the argument required for this
     * foreign command addon to execute.
     *
     * @return required argument
     */
    String value();

    /**
     * This is the aliases for the foreign command
     * addon, example. "hello, bonjour, hola"
     * <br>
     * These aliases will be tired to the same base label.
     *
     * @return foreign argument aliases
     */
    String[] aliases() default {};

    /**
     * This is the required permission to use
     * this foreign command addon.
     *
     * @return required permission
     */
    String permission() default "null";
}
