package net.digitalpear.beeten.common.item;

import net.digitalpear.beeten.Beeten;
import net.digitalpear.beeten.init.BGameRules;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.Objects;

public class HeartBeetItem extends Item {
    public static final Identifier EFFECT_ID = Beeten.id("beet_healing");
    public static final RegistryEntry<EntityAttribute> GENERIC_MAX_HEALTH = EntityAttributes.GENERIC_MAX_HEALTH;
    public static final float BASE_INCREASE_AMOUNT = 1;
    public HeartBeetItem(Settings settings) {
        super(settings);
    }


    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (user.getAttributes().hasAttribute(GENERIC_MAX_HEALTH) && !world.isClient()){
            double currentValue = BASE_INCREASE_AMOUNT;
            EntityAttributeModifier attributeModifier = user.getAttributeInstance(GENERIC_MAX_HEALTH).getModifier(EFFECT_ID);
            if (attributeModifier != null){
                currentValue += attributeModifier.value();
                if (world.getGameRules().getInt(BGameRules.MAX_HEART_BEAT_CONSUMPTION) < currentValue){
                    return super.finishUsing(stack, world, user);
                }
                Objects.requireNonNull(user.getAttributeInstance(GENERIC_MAX_HEALTH)).removeModifier(EFFECT_ID);
            }
            Objects.requireNonNull(user.getAttributeInstance(GENERIC_MAX_HEALTH)).addPersistentModifier(new EntityAttributeModifier(EFFECT_ID, currentValue, EntityAttributeModifier.Operation.ADD_VALUE));
            user.heal(BASE_INCREASE_AMOUNT);
        }
        return super.finishUsing(stack, world, user);
    }
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return ItemUsage.consumeHeldItem(world, user, hand);
    }
}
