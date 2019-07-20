package com.destinymc.file;

/**
 * Created at 18/02/2019
 *
 * @author Luke Bingham
 */
public interface File<T> {

    void load();

    T get();

    void save();

    void generate();
}
