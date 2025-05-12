package net.digitalpear.beeten.init.data;

import net.digitalpear.beeten.Beeten;
import net.digitalpear.beeten.common.item.consume.ChangeAttributeConsumeEffect;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.ConsumableComponents;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;

public class BConsumableComponents {

    public static final ConsumableComponent HEART_BEET = ConsumableComponents.food()
            .consumeEffect(new ChangeAttributeConsumeEffect(Beeten.id("beet_healing"), EntityAttributes.MAX_HEALTH, 1.0f, EntityAttributeModifier.Operation.ADD_VALUE))
            .build();

//    public static final ConsumableComponent BEETROOT_SOUP = ConsumableComponents.food()
//            .consumeEffect(new ApplyEffectsConsumeEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 20*5)))
//            .build();
}
