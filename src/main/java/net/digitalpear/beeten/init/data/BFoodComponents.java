package net.digitalpear.beeten.init.data;

import net.minecraft.component.type.FoodComponent;

public class BFoodComponents {
    public static final FoodComponent HEART_BEET = new FoodComponent.Builder().nutrition(1)
            .saturationModifier(0.4F).alwaysEdible()
            .build();
}
