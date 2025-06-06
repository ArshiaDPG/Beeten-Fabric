package net.digitalpear.beeten.common.item.consume;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.digitalpear.beeten.init.BConsumeEffects;
import net.digitalpear.beeten.init.BGameRules;
import net.minecraft.component.ComponentsAccess;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.consume.ConsumeEffect;
import net.minecraft.item.tooltip.TooltipAppender;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.Texts;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.Objects;
import java.util.function.Consumer;

public record ChangeAttributeConsumeEffect(Identifier identifier, RegistryEntry<EntityAttribute> attribute, double value, EntityAttributeModifier.Operation operation) implements ConsumeEffect {
    public static final MapCodec<ChangeAttributeConsumeEffect> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
            Identifier.CODEC.fieldOf("identifier").forGetter(changeAttributeConsumeEffect -> changeAttributeConsumeEffect.identifier),
            ClampedEntityAttribute.CODEC.fieldOf("attribute").forGetter(changeAttributeConsumeEffect -> changeAttributeConsumeEffect.attribute),
            Codec.DOUBLE.fieldOf("value").forGetter(changeAttributeConsumeEffect -> changeAttributeConsumeEffect.value),
            EntityAttributeModifier.Operation.CODEC.fieldOf("operation").forGetter(changeAttributeConsumeEffect -> changeAttributeConsumeEffect.operation)
    ).apply(instance, ChangeAttributeConsumeEffect::new));
    public static final PacketCodec<RegistryByteBuf, ChangeAttributeConsumeEffect>  PACKET_CODEC = PacketCodec.tuple(
            Identifier.PACKET_CODEC, ChangeAttributeConsumeEffect::identifier,
            ClampedEntityAttribute.PACKET_CODEC, ChangeAttributeConsumeEffect::attribute,
            PacketCodecs.DOUBLE, ChangeAttributeConsumeEffect::value,
            EntityAttributeModifier.Operation.PACKET_CODEC, ChangeAttributeConsumeEffect::operation,
            ChangeAttributeConsumeEffect::new
    );


    @Override
    public Type<? extends ConsumeEffect> getType() {
        return BConsumeEffects.CHANGE_ATTRIBUTE;
    }

    @Override
    public boolean onConsume(World world, ItemStack stack, LivingEntity user) {
        if (world instanceof ServerWorld serverWorld){
            if (!user.getAttributes().hasAttribute(attribute)){
                return false;
            }
            else{
                double currentValue = value;
                EntityAttributeModifier attributeModifier = user.getAttributeInstance(attribute).getModifier(identifier);
                if (attributeModifier != null){
                    currentValue += attributeModifier.value();
                    if (serverWorld.getGameRules().getInt(BGameRules.MAX_HEART_BEAT_CONSUMPTION) < currentValue){
                        return false;
                    }
                    Objects.requireNonNull(user.getAttributeInstance(attribute)).removeModifier(identifier);
                }
                Objects.requireNonNull(user.getAttributeInstance(attribute)).addPersistentModifier(new EntityAttributeModifier(identifier, currentValue, operation));
                if (attribute == EntityAttributes.MAX_HEALTH){
                    user.heal(1);
                }
                return true;
            }
        }
        return false;
    }
}
