package com.github.steveice10.mc.protocol.packet.ingame.server.entity;

import com.github.steveice10.mc.protocol.util.NetUtil;
import com.github.steveice10.opennbt.tag.builtin.CompoundTag;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ServerEntityNBTUpdatePacket implements Packet {
    private int entityId;
    private CompoundTag tag;

    @SuppressWarnings("unused")
    private ServerEntityNBTUpdatePacket() {
    }

    public ServerEntityNBTUpdatePacket(int entityId, CompoundTag tag) {
        this.entityId = entityId;
        this.tag = tag;
    }

    public int getEntityId() {
        return this.entityId;
    }

    public CompoundTag getTag() {
        return this.tag;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.entityId = in.readVarInt();
        this.tag = NetUtil.readNBT(in);
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(this.entityId);
        NetUtil.writeNBT(out, this.tag);
    }

    @Override
    public boolean isPriority() {
        return false;
    }
}
