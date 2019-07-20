package com.destinymc.redis;

import com.destinymc.server.Instance;

public interface JMessageListener<T extends JMessage> {
    void onReceive(Instance sender, T message);
}
