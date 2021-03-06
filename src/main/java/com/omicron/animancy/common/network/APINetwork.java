package com.omicron.animancy.common.network;

import com.omicron.animancy.Animancy;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class APINetwork {

    private static SimpleChannel INSTANCE;

    private static int ID = 0;

    private static int nextID()
    {
        return ID++;
    }

    public static void registerMessages()
    {
        INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(Animancy.MODID, "api_network"),
                () -> "1.0",
                s -> true,
                s -> true);
        addMessage(TestPacket.class)
                .encoder(TestPacket::toBytes)
                .decoder(TestPacket::new)
                .consumer(TestPacket::handle)
                .add();

    }

    public static SimpleChannel getInstance()
    {
        return INSTANCE;
    }

    public static <T> SimpleChannel.MessageBuilder<T> addMessage(final Class<T> type)
    {
        return INSTANCE.messageBuilder(type, nextID());
    }

    public static void sendToClient(Object packet, ServerPlayerEntity player)
    {
        INSTANCE.sendTo(packet, player.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
    }

    public static void sendToServer(Object packet)
    {
        INSTANCE.sendTo(packet, Minecraft.getInstance().player.connection.getConnection(), NetworkDirection.PLAY_TO_SERVER);
    }
}
