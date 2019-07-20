package com.destinymc.redis;

import com.destinymc.server.Instance;
import com.destinymc.server.ServerType;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public final class JMessageWriter {

    private final Instance instance;
    private final Gson gson = new Gson();
    private final JedisPool pool;
    private final JedisChannel channel;

    public JMessageWriter(Instance instance, JedisPool pool, JedisChannel channel) {
        this.instance = instance;
        this.pool = pool;
        this.channel = channel;
    }

    public final void publishPacket(Object message, Instance recipient) {
        JsonObject label = new JsonObject();
        label.addProperty("name", message.getClass().getName());
        label.addProperty("senderId", instance.getUniqueId());
        label.addProperty("senderType", instance.getServerType().name());
        label.addProperty("recipientId", recipient.getUniqueId());
        label.addProperty("recipientType", recipient.getServerType().name());
        label.add("content", gson.toJsonTree(message));

        try (Jedis jedis = pool.getResource()) {
            jedis.publish(channel.getChannel(), label.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void publishPacket(Object message) {
        JsonObject label = new JsonObject();
        label.addProperty("name", message.getClass().getName());
        label.addProperty("senderId", instance.getUniqueId());
        label.addProperty("senderType", instance.getServerType().name());
        label.addProperty("recipientId", -1);
        label.addProperty("recipientType", ServerType.UNKNOWN.name());
        label.add("content", gson.toJsonTree(message));

        try (Jedis jedis = pool.getResource()) {
            jedis.publish(channel.getChannel(), label.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
