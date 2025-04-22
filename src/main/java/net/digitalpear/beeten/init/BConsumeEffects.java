package net.digitalpear.beeten.init;

import com.mojang.serialization.MapCodec;
import net.digitalpear.beeten.Beeten;
import net.digitalpear.beeten.common.item.consume.ChangeAttributeConsumeEffect;
import net.minecraft.item.consume.ConsumeEffect;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class BConsumeEffects {

    public static final ConsumeEffect.Type<ChangeAttributeConsumeEffect> CHANGE_ATTRIBUTE = register("change_attribute", ChangeAttributeConsumeEffect.CODEC, ChangeAttributeConsumeEffect.PACKET_CODEC);


    private static <T extends ConsumeEffect> ConsumeEffect.Type<T> register(String id, MapCodec<T> codec, PacketCodec<RegistryByteBuf, T> packetCodec) {
        return (ConsumeEffect.Type)Registry.register(Registries.CONSUME_EFFECT_TYPE, Beeten.id(id), new ConsumeEffect.Type(codec, packetCodec));
    }

    public static void init(){}
}
