package com.destinymc.ic2;

import com.destinymc.achievement.AchievementManager;
import com.destinymc.achievement.exception.AchievementRegisterException;
import com.destinymc.achievement.preset.EnergyGenerationAchievement;
import com.destinymc.achievement.preset.TestAchievement;
import com.destinymc.achievement.preset.energy.PowerProgressionAchievementI;
import com.destinymc.achievement.preset.energy.PowerProgressionAchievementII;
import com.destinymc.achievement.preset.energy.PowerProgressionAchievementIII;
import com.destinymc.changelog.ChangelogManager;
import com.destinymc.database.BaseDatabase;
import com.destinymc.file.type.YamlFile;
import com.destinymc.ic2.guide.GuideManager;
import com.destinymc.ic2.guide.command.GuideCommand;
import com.destinymc.ic2.item.IC2Item;
import com.destinymc.ic2.item.IC2ItemManager;
import com.destinymc.ic2.machine.MachineManager;
import com.destinymc.ic2.recipe.RecipeManager;
import com.destinymc.inventory.MenuHandler;
import com.destinymc.locale.LocaleManager;
import com.destinymc.profile.ProfileManager;
import com.destinymc.tip.TipManager;
import com.destinymc.util.FileUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created at 10/01/2019
 * <p>
 * @author Luke Bingham
 */
public final class IC2Plugin extends JavaPlugin implements CommandExecutor {

    private IC2ItemManager itemManager;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        YamlFile.MYSQL = FileUtil.getYaml("mysql");

        BaseDatabase.getInstance().init(YamlFile.MYSQL.get());
        BaseDatabase.createTables();

        itemManager = new IC2ItemManager(this);
        MachineManager machineManager = new MachineManager(this, itemManager);
        RecipeManager recipeManager = new RecipeManager(this);
        MenuHandler menuHandler = new MenuHandler(this);
        GuideManager guideManager = new GuideManager(recipeManager, itemManager);
        ChangelogManager changelogManager = new ChangelogManager(this);
        TipManager tipManager = new TipManager(this, changelogManager);
        LocaleManager localeManager = new LocaleManager(this, tipManager, changelogManager);
        ProfileManager profileManager = new ProfileManager(this);

        itemManager.register();

        getCommand("items").setExecutor(this);
        new GuideCommand();

        try {
            AchievementManager.registerAchievement(new TestAchievement());
            AchievementManager.registerAchievement(new EnergyGenerationAchievement());
            AchievementManager.registerAchievement(new PowerProgressionAchievementI());
            AchievementManager.registerAchievement(new PowerProgressionAchievementII());
            AchievementManager.registerAchievement(new PowerProgressionAchievementIII());
        } catch (AchievementRegisterException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("items")) {
            Inventory inv = Bukkit.createInventory(null, 9 * 9, "Items");

            int index = 0;
            for (IC2Item item : itemManager.getItemMap().values()) {
                inv.setItem(index, item);
                index += 1;
            }

            ((Player) sender).openInventory(inv);
            return true;
        }

        return true;
    }
}
