package net.digitalpear.beeten.init.data;

import net.digitalpear.beeten.Beeten;
import net.digitalpear.beeten.common.item.consume.ChangeAttributeConsumeEffect;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.ConsumableComponents;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;

public class BConsumableComponents {

    public static final ConsumableComponent HEART_BEET = ConsumableComponents.food()
            .consumeEffect(new ChangeAttributeConsumeEffect(Beeten.id("beet_healing"), EntityAttributes.MAX_HEALTH, 1, EntityAttributeModifier.Operation.ADD_VALUE))
            .build();
}
