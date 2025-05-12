package net.digitalpear.beeten.init;

import net.digitalpear.beeten.Beeten;
import net.digitalpear.beeten.init.data.BConsumableComponents;
import net.digitalpear.beeten.init.data.BFoodComponents;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

import java.util.function.Function;

public class BItems {

    private static Item register(String name, Function<Item.Settings, Item> factory, Item.Settings settings){
        return Items.register(RegistryKey.of(RegistryKeys.ITEM, Beeten.id(name)), factory, settings);
    }
    private static Item register(String name, Item.Settings settings){
        return register(name, Item::new, settings);
    }

    public static final Item HEART_BEET = register("heart_beet", new Item.Settings()
            .food(BFoodComponents.HEART_BEET, BConsumableComponents.HEART_BEET)
    );

    public static void init() {
//        DefaultItemComponentEvents.MODIFY.register(modifyContext -> {
//            modifyContext.modify(Items.BEETROOT_SOUP, builder -> builder.add(DataComponentTypes.CONSUMABLE, BConsumableComponents.BEETROOT_SOUP));
//        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.addAfter(Items.BEETROOT, HEART_BEET);
        });
    }
}
