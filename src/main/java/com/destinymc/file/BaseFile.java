package com.destinymc.file;

import com.destinymc.util.ServerUtil;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created at 18/02/2019
 *
 * @author Luke Bingham
 */
public abstract class BaseFile<T> implements File<T> {

    private T object = null;

    private final java.io.File file;
    private final java.io.File directory;

    private final String fileName;

    public BaseFile(String directory, String fileName, String extention) {

        Pattern pattern = Pattern.compile("\\{server}");
        Matcher matcher = pattern.matcher(directory);
        if (matcher.find()) {
            String prefix = directory.split(pattern.pattern())[1];
            directory = ServerUtil.getPlugin().getServer().getWorldContainer().getAbsolutePath() + prefix;
        }

        this.directory = new java.io.File(directory);
        this.file = new java.io.File(this.directory, fileName + "." + extention);
        this.fileName = fileName;

//        if (!this.file.exists()) {
//            this.generate();
//        }
    }

    protected T getObject() {
        return this.object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public String getFileName() {
        return this.fileName;
    }

    public java.io.File getFile() {
        return file;
    }

    public java.io.File getDirectory() {
        return this.directory;
    }
}
