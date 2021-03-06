package com.github.steveice10.mc.protocol.packet.ingame.server.world;

import com.github.steveice10.mc.protocol.data.game.Position;
import com.github.steveice10.mc.protocol.data.message.Message;
import com.github.steveice10.mc.protocol.util.NetUtil;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ServerUpdateSignPacket implements Packet {
    private Position position;
    private Message lines[];

    @SuppressWarnings("unused")
    private ServerUpdateSignPacket() {
    }

    public ServerUpdateSignPacket(Position position, String lines[]) {
        this(position, toMessages(lines));
    }

    public ServerUpdateSignPacket(Position position, Message lines[]) {
        if(lines.length != 4) {
            throw new IllegalArgumentException("Lines must contain exactly 4 strings!");
        }

        this.position = position;
        this.lines = lines;
    }

    public Position getPosition() {
        return this.position;
    }

    public Message[] getLines() {
        return this.lines;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.position = NetUtil.readPosition(in);
        this.lines = new Message[4];
        for(int count = 0; count < this.lines.length; count++) {
            this.lines[count] = Message.fromString(in.readString());
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        NetUtil.writePosition(out, this.position);
        for(Message line : this.lines) {
            out.writeString(line.toJsonString());
        }
    }

    @Override
    public boolean isPriority() {
        return false;
    }

    private static Message[] toMessages(String lines[]) {
        Message messages[] = new Message[lines.length];
        for(int index = 0; index < lines.length; index++) {
            messages[index] = Message.fromString(lines[index]);
        }

        return messages;
    }
}
