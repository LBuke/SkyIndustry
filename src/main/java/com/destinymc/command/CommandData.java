package com.destinymc.command;

import java.lang.reflect.Method;

/**
 * Created at 15/02/2019
 * <p>
 * @author Luke Bingham
 */
public final class CommandData {

    /* Parent class of the command */
    private final Object owningClass;

    /* Method ofthe ForeignCommand addon */
    private final Method method;

    /* The ForeignCommand annotation */
    private final ForeignCommand command;

    /**
     * @param owningClass - Parent class of the command
     * @param method - Method of ForeignCommand addon
     * @param command - The ForeignCommand annotation
     */
    public CommandData(Object owningClass, Method method, ForeignCommand command) {
        this.owningClass = owningClass;
        this.method = method;
        this.command = command;
    }

    public Object getOwningClass() {
        return owningClass;
    }

    public Method getMethod() {
        return method;
    }

    public ForeignCommand getCommand() {
        return command;
    }
}
