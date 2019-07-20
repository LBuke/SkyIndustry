package com.destinymc.redis;

public enum JedisChannel {

    GLOBAL("global"),
    ;

    private String channel;

    JedisChannel(String channel) {
        this.channel = channel;
    }

    public String getChannel() {
        return channel;
    }
}
