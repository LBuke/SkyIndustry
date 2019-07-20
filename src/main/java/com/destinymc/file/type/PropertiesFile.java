package com.destinymc.file.type;

import com.destinymc.file.BaseFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * Created at 18/02/2019
 *
 * @author Luke Bingham
 */
public final class PropertiesFile extends BaseFile<Properties> {

    public PropertiesFile(String directory, String fileName) {
        super(directory, fileName, "properties");
    }

    @Override
    public void load() {
        if (super.getFile() == null || !super.getFile().exists()) {
            this.generate();
            this.load();
            return;
        }

        try (FileInputStream fileInputStream = new FileInputStream(super.getFile())) {
            try (Reader reader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8)) {
                Properties prop = new Properties();
                prop.load(reader);
                super.setObject(prop);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Properties get() {
        if (super.getObject() == null) {
            this.load();
            return this.get();
        }

        return super.getObject();
    }

    @Override
    public void save() {
//        if (super.getObject() == null) return;
//        try {
//            super.getObject().save
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
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
