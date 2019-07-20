package com.destinymc.util;

public enum MilliType {

    MILLISECOND(1),
    MC_TICK(49),
    SECOND(1000),
    MINUTE(1000 * 60),
    HOUR(1000 * 60 * 60),
    DAY(1000 * 60 * 60 * 24),;

    private final long length;

    MilliType(long length) {
        this.length = length;
    }

    public long getLength() {
        return length;
    }

    public long getLength(int multiply) {
        return length * multiply;
    }
}
