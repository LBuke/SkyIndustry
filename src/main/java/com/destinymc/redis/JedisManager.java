package com.destinymc.redis;

import com.destinymc.server.Instance;

import java.util.HashMap;

public class JedisManager {

    private final HashMap<JedisChannel, JedisModule> jedisModules;

    public JedisManager() {
        this.jedisModules = new HashMap<JedisChannel, JedisModule>();
    }

    public JedisModule getModule(JedisChannel channel) {
        return this.jedisModules.getOrDefault(channel, null);
    }

    public void initModule(Instance instance, JedisChannel channel, String vlan, int port, String password) {
        this.jedisModules.putIfAbsent(channel, new JedisModule(instance, channel, vlan, port, password));
    }

    public HashMap<JedisChannel, JedisModule> getJedisModules() {
        return this.jedisModules;
    }
}
