package com.destinymc.command;

import com.google.common.collect.Maps;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

import static com.destinymc.locale.I18n.get;

/**
 * Created at 14/02/2019
 * <p>
 * @author Luke Bingham
 */
public abstract class BaseCommand<T extends CommandSender> extends BukkitCommand {

    private final Map<String, CommandData> dataMap = Maps.newHashMap();

    /* Bukkit's command cache, Null by default */
    private static CommandMap commandMap = null;

    /* Base command label */
    private final String command;

    /* Description of the command, Will be shown in the help menu */
    private String description = "No description.";

    /* Optional command aliases, multiple 'base command labels' */
    private String[] aliases;

    /**
     * Construct a new command.
     *
     * @param command - Base command label
     * @param aliases - Optional command aliases
     */
    public BaseCommand(String command, String... aliases) {
        super(command);
        this.command = command;
        this.aliases = aliases;
    }

    /**
     * Construct a new command.
     *
     * @param command - Base command label
     * @param description - Description of the command
     * @param aliases - Optional command aliases
     */
    public BaseCommand(String command, String description, String... aliases) {
        super(command);
        this.command = command;
        this.description = description;
        this.aliases = aliases;
    }

    /**
     * Construct a new command.
     *
     * @param command - BaseCommand class to get Annotation values
     */
    public BaseCommand(Class<? extends BaseCommand> command) {
        super(command.getAnnotation(CommandName.class).value());

        this.command = command.getAnnotation(CommandName.class).value();

        if (command.isAnnotationPresent(CommandDescription.class)) {
            this.description = command.getAnnotation(CommandDescription.class).value();
        }

        if (command.isAnnotationPresent(CommandAliases.class)) {
            this.aliases = command.getAnnotation(CommandAliases.class).value();
        }
    }

    @Override
    public final boolean execute(CommandSender commandSender, String s, String[] strings) {
        onExecute((T) commandSender, strings);

        for (String arg : strings) {
            CommandData data = this.dataMap.getOrDefault(arg, null);
            if (data == null) {
                commandSender.sendMessage(get("command.invalid.args"));
                return true;
            }

            CommandArgument argument = new CommandArgument((T) commandSender, arg, strings);

            if (commandSender instanceof Player) {
                Player player = (Player) commandSender;

                String permission = data.getCommand().permission();
                if (!permission.equals("null") && !player.hasPermission(permission)) {
                    commandSender.sendMessage(get("command.nopermission"));
                    return true;
                }
            }

            try {
                data.getMethod().invoke(data.getOwningClass(), argument);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    /**
     * This method is called when the command is executed.
     *
     * @param commandSender - Sender of the command
     * @param strings - String array of arguments
     */
    protected abstract void onExecute(T commandSender, String[] strings);

    /**
     * This method will register a command without the need of plugin.yml<br>
     * This method will also register foreign commands given the correct objects.
     */
    protected void register(Object... objects) {
        for (Object object : objects) {
            for (Method method : object.getClass().getMethods()) {
                if (!method.isAnnotationPresent(ForeignCommand.class))
                    continue;

                ForeignCommand cmd = method.getAnnotation(ForeignCommand.class);
                CommandData data = new CommandData(object, method, cmd);

                dataMap.put(cmd.value(), data);

                for (String relative : cmd.aliases()) {
                    dataMap.put(relative, data);
                }
            }
        }

        if (commandMap != null) {
            setAliases(Arrays.asList(aliases));
            setDescription(description);
            commandMap.register(command, this);
            return;
        }

        try {
            Field field = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            field.setAccessible(true);
            commandMap = (CommandMap) field.get(Bukkit.getServer());

            setAliases(Arrays.asList(aliases));
            setDescription(description);

            commandMap.register(command, this);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
