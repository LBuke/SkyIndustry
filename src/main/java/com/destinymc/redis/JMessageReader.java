package com.destinymc.redis;

import com.destinymc.server.Instance;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import redis.clients.jedis.JedisPubSub;

import java.util.List;

public final class JMessageReader extends JedisPubSub {

    private final Instance instance;
    private final Gson gson = new Gson();
    private final JsonParser jsonParser = new JsonParser();

    public JMessageReader(Instance instance) {
        this.instance = instance;
    }

    @Override
    public final void onMessage(String channel, String message) {
        if (!isValid(message)) return;

        try {
            JsonObject label = (JsonObject) jsonParser.parse(message);
            String messageName = label.get("name").getAsString();
            int senderId = label.get("senderId").getAsInt();
            String senderType = label.get("senderType").getAsString();
            int recipientId = label.get("recipientId").getAsInt();
            String recipientType = label.get("recipientType").getAsString();

            if(!"GLOBAL".equalsIgnoreCase(recipientType)) {
                if(!this.instance.getServerType().name().equals(recipientType)) return;
                if(recipientId != -1 && this.instance.getUniqueId() != recipientId) return;
            }
//            if (recipient.equalsIgnoreCase("all") || recipient.equalsIgnoreCase(serverName)) {
                Class<? extends JMessage> messageClass = (Class<? extends JMessage>) Class.forName(messageName);
                JMessage msg = gson.fromJson(label.getAsJsonObject("content"), messageClass);

                List<JMessageListener> listenerList = JedisModule.listeners.get(messageClass);

                //TODO: Fix this bag of wank
//                if (listenerList != null) listenerList.forEach(c -> c.onReceive(new DestinyInstance(ServerType.getByIdentifier(senderId), senderId), msg));
//            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public final void onPMessage(String s, String s1, String s2) {

    }

    @Override
    public final void onSubscribe(String s, int i) {

    }

    @Override
    public final void onUnsubscribe(String s, int i) {

    }

    @Override
    public final void onPUnsubscribe(String s, int i) {

    }

    @Override
    public final void onPSubscribe(String s, int i) {

    }

    private boolean isValid(String str) {
        try {
            jsonParser.parse(str);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
