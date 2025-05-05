package net.digitalpear.beeten.init;

import net.digitalpear.beeten.Beeten;
import net.digitalpear.beeten.common.item.HeartBeetItem;
import net.digitalpear.beeten.init.data.BFoodComponents;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class BItems {

    private static Item register(String name, Item item){
        return Registry.register(Registries.ITEM, Beeten.id(name), item);
    }
    private static Item register(String name, Item.Settings settings){
        return register(name, new Item(settings));
    }

    public static final Item HEART_BEET = register("heart_beet", new HeartBeetItem(new Item.Settings()
            .food(BFoodComponents.HEART_BEET))
    );

    public static void init() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.addAfter(Items.BEETROOT, HEART_BEET);
        });
    }
}
