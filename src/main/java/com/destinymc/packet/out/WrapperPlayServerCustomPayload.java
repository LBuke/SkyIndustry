/**
 * PacketWrapper - ProtocolLib wrappers for Minecraft packets
 * Copyright (C) dmulloy2 <http://dmulloy2.net>
 * Copyright (C) Kristian S. Strangeland
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.destinymc.packet.out;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.utility.MinecraftReflection;
import com.destinymc.packet.AbstractPacket;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class WrapperPlayServerCustomPayload extends AbstractPacket {
    public static final PacketType TYPE = PacketType.Play.Server.CUSTOM_PAYLOAD;

    public WrapperPlayServerCustomPayload() {
        super(new PacketContainer(TYPE), TYPE);
        handle.getModifier().writeDefaults();
    }

    public WrapperPlayServerCustomPayload(PacketContainer packet) {
        super(packet, TYPE);
    }

    /**
     * Retrieve Channel.
     * <p>
     * Notes: name of the "channel" used to send the profile.
     *
     * @return The current Channel
     */
    public String getChannel() {
        return handle.getStrings().read(0);
    }

    /**
     * Set Channel.
     *
     * @param value - new value.
     */
    public void setChannel(String value) {
        handle.getStrings().write(0, value);
    }

    /**
     * Retrieve payload contents as a raw Netty buffer
     *
     * @return Payload contents as a Netty buffer
     */
    public ByteBuf getContentsBuffer() {
        return (ByteBuf) handle.getModifier().withType(ByteBuf.class).read(0);
    }

    /**
     * Retrieve payload contents
     *
     * @return Payload contents as a byte array
     */
    public byte[] getContents() {
        ByteBuf buffer = getContentsBuffer();
        byte[] array = new byte[buffer.readableBytes()];
        buffer.readBytes(array);
        return array;
    }

    /**
     * Update payload contents with a Netty buffer
     *
     * @param contents - new payload content
     */
    public void setContentsBuffer(ByteBuf contents) {
        if (MinecraftReflection.is(MinecraftReflection.getPacketDataSerializerClass(), contents)) {
            handle.getModifier().withType(ByteBuf.class).write(0, contents);
        } else {
            Object serializer = MinecraftReflection.getPacketDataSerializer(contents);
            handle.getModifier().withType(ByteBuf.class).write(0, serializer);
        }
    }

    /**
     * Update payload contents with a byte array
     *
     * @param content - new payload content
     */
    public void setContents(byte[] content) {
        setContentsBuffer(Unpooled.copiedBuffer(content));
    }
}