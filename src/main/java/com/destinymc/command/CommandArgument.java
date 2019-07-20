package com.destinymc.command;

import org.bukkit.command.CommandSender;

import java.util.Arrays;

/**
 * Created at 15/02/2019
 * <p>
 * @author Luke Bingham
 */
public final class CommandArgument {

    private final CommandSender sender;
    private final String label;
    private final String[] args;

    /**
     * @param sender - Sender of the command
     * @param label - Base command label
     * @param args - String array of arguments
     */
    public CommandArgument(CommandSender sender, String label, String[] args) {
        this.sender = sender;
        this.label = label;
        this.args = args;
    }

    public CommandSender getSender() {
        return sender;
    }

    public String getLabel() {
        return label;
    }

    public String[] getArgs() {
        return args;
    }

    @Override
    public String toString() {
        return "sender = [" + sender.getName() + "], label = [" + label + "], args = [" + Arrays.toString(args) + "]";
    }

    public int getInt(int index) {
        int i = -1;
        try {
            i = Integer.parseInt(args[index]);
        } catch (NumberFormatException e) {}

        return i;
    }
}
