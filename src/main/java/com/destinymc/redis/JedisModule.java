package com.destinymc.redis;

import com.destinymc.server.Instance;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;
import java.util.List;

public class JedisModule {

    private final Gson gson = new Gson();
    private JedisPool jedisPool;
    private JMessageReader reader;
    private JMessageWriter writer;
    private final JedisChannel channel;

    protected static final HashMap<Class<? extends JMessage>, List<JMessageListener>> listeners;

    static {
        listeners = new HashMap<>();
    }

    public JedisModule(Instance instance, JedisChannel channel, String vlan, int port, String password) {
        this.channel = channel;
        this.connect(instance, channel, vlan, port, password);
    }

    public final void connect(Instance instance, JedisChannel channel, String vlan, int port, String password) {
        this.jedisPool = new JedisPool(new JedisPoolConfig(), vlan, port, 2000, password);

        try (Jedis jedis = jedisPool.getResource()) {
            jedis.ping();
            System.out.println("PING");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        reader = new JMessageReader(instance);
        writer = new JMessageWriter(instance, jedisPool, channel);

        new Thread(() -> {
            try (Jedis j = getJedisPool().getResource()) {
                j.subscribe(reader, channel.getChannel());
                System.out.println("SUBSCRIBED.");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * This will send a message to the given server.
     *
     * @param message - The message a' server will receive.
     * @param instance - Server to send the message to.
     */
    public final void sendMessage(Object message, Instance instance) {
        writer.publishPacket(message, instance);
    }

    /**
     * This will send a message to all servers including Proxy and Operator.
     *
     * @param message - The message all servers will receive.
     */
    public final void sendMessage(Object message) {
        writer.publishPacket(message);
    }

    public final void disable() {
        if(reader != null) {
            if (reader.isSubscribed())
                reader.unsubscribe();
        }

        if(jedisPool != null)
            jedisPool.destroy();
    }

    public final void disconnect() {
        this.disable();
    }

    public final JedisPool getJedisPool() {
        return jedisPool;
    }

    public final <T extends JMessage> void registerListener(Class<T> msg, JMessageListener<T> listener) {
        if (listeners.containsKey(msg)) {
            listeners.get(msg).add(listener);
        }
        else {
            List<JMessageListener> list = Lists.newArrayList();
            list.add(listener);
            listeners.put(msg, list);
        }
    }
}
