package com.github.steveice10.mc.protocol.packet.ingame.server.entity.player;


import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ServerChangeHeldItemPacket implements Packet {

    private int slot;

    @SuppressWarnings("unused")
    private ServerChangeHeldItemPacket() {
    }

    public ServerChangeHeldItemPacket(int slot) {
        this.slot = slot;
    }

    public int getSlot() {
        return this.slot;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.slot = in.readByte();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeByte(this.slot);
    }

    @Override
    public boolean isPriority() {
        return false;
    }

}
