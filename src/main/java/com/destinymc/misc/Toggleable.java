package com.destinymc.misc;

/**
 * Created at 16/02/2019
 * <p>
 * @author Luke Bingham
 */
public interface Toggleable {

    boolean isEnabled();

    default boolean isDisabled() {
        return !this.isEnabled();
    }

    void toggle();
}
