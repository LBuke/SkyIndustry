package com.destinymc.util;

import com.destinymc.file.type.YamlFile;
import com.destinymc.ic2.IC2Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.Properties;

/**
 * @author Luke Bingham
 */
public final class FileUtil {

    public static Properties loadPropertiesFromResource(String fileName) {
        try (InputStream inputStream = IC2Plugin.class.getClassLoader().getResourceAsStream(fileName)) {
            if (inputStream == null) return null;

            Properties prop = new Properties();
            prop.load(inputStream);
            return prop;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static YamlFile getYaml(String name) {
        JavaPlugin plugin = ServerUtil.getPlugin();
        YamlFile file = new YamlFile(plugin.getConfig().getString("files." + name + ".dir"), plugin.getConfig().getString("files." + name + ".name"));
        System.out.println(name + " = " + file.getDirectory().getAbsolutePath());
        saveResource("conf/" + name + ".yml", file.getDirectory().getAbsolutePath(), file.getFile().getName());
        return file;
    }

    public static void saveResource(String original, String outputDirectory, String fileName) {
        try (InputStream inputStream = IC2Plugin.class.getClassLoader().getResourceAsStream(original)) {
            if (inputStream == null) throw new NullPointerException("InputStream couldn't find resource.");

            File outputDir = new File(outputDirectory);
            if (!outputDir.exists()) outputDir.mkdirs();
            File file = new File(outputDir, fileName);

            boolean exists = file.exists();
            if (!exists) file.createNewFile();

            if (exists) {
//                System.out.println(fileName + " already exists, cannot copy defaults.");
                return;
            }

            try (OutputStream outputStream = new FileOutputStream(file)) {
                byte[] buf = new byte[1024];

                int len;
                while((len = inputStream.read(buf)) > 0) {
                    outputStream.write(buf, 0, len);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
