package com.destinymc.inventory;

/**
 * Created at 22/01/2019
 * <p>
 * @author Luke Bingham
 */
public enum MenuFlag {

    /**
     * When a user clicks outside of the inventory ui,
     * the menu will close.
     * <p>
     * ! This does not account for empty slots. !
     */
    CLOSE_ON_NULL_CLICK,

    /**
     * When a user opens the specific inventory,
     * the cursor will be reset back to its
     * default position. (X:Center - Y:Bottom)
     * <p>
     * ! This only accounts for opening !
     */
    RESET_CURSOR_ON_OPEN,;
}
