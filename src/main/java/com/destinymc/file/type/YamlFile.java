package com.destinymc.file.type;

import com.destinymc.file.BaseFile;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.IOException;

/**
 * Created at 18/02/2019
 *
 * @author Luke Bingham
 */
public final class YamlFile extends BaseFile<FileConfiguration> {

    public static YamlFile MYSQL;

    public YamlFile(String directory, String fileName) {
        super(directory, fileName, "yml");
    }

    @Override
    public void load() {
        if (super.getFile() == null || !super.getFile().exists()) {
            this.generate();
            this.load();
            return;
        }

        super.setObject(YamlConfiguration.loadConfiguration(super.getFile()));
    }

    @Override
    public FileConfiguration get() {
        if (super.getObject() == null) {
            this.load();
            return this.get();
        }

        return super.getObject();
    }

    @Override
    public void save() {
        if (super.getObject() == null) return;
        try {
            super.getObject().save(super.getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void generate() {
        if (super.getFile().exists()) return;

        super.getFile().mkdirs();
        try {
            super.getFile().createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
