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
import net.minecraft.util.Identifier;

public class BConsumableComponents {

    public static final Identifier HEART_BEET_MODIFIER_ID = Beeten.id("beet_healing");

    public static final ConsumableComponent HEART_BEET = ConsumableComponents.food()
            .consumeEffect(new ChangeAttributeConsumeEffect(HEART_BEET_MODIFIER_ID, EntityAttributes.MAX_HEALTH, 1.0f, EntityAttributeModifier.Operation.ADD_VALUE))
            .build();
}
