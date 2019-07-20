package com.destinymc.ic2.guide.command;

import com.destinymc.command.BaseCommand;
import com.destinymc.command.CommandAliases;
import com.destinymc.command.CommandDescription;
import com.destinymc.command.CommandName;
import com.destinymc.ic2.guide.GuideManager;
import com.destinymc.misc.Unfinished;
import org.bukkit.entity.Player;

/**
 * Created at 14/02/2019
 * <p>
 * @author Luke Bingham
 */
@CommandName("guide")
@CommandAliases({"recipe", "recipes"})
@CommandDescription("A visual tutorial to all recipes.")
public final class GuideCommand extends BaseCommand<Player> {

    public GuideCommand() {
        super(GuideCommand.class);

        register(GuideManager.class);
    }

    @Unfinished
    @Override
    protected void onExecute(Player commandSender, String[] strings) {
        //Open guide inventory
    }
}
