package net.digitalpear.beeten.common.datagen;

import net.digitalpear.beeten.Beeten;
import net.digitalpear.beeten.init.BItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.data.advancement.AdvancementTabGenerator;
import net.minecraft.item.Item;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class BAdvancementProvider extends FabricAdvancementProvider {


    public BAdvancementProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement(RegistryWrapper.WrapperLookup wrapperLookup, Consumer<AdvancementEntry> consumer) {
        AdvancementEntry advancementEntry = AdvancementTabGenerator.reference("husbandry/plant_seed");
        RegistryEntryLookup<Item> registryEntryLookup = wrapperLookup.getOrThrow(RegistryKeys.ITEM);

        AdvancementEntry advancementEntry2 = Advancement.Builder.create().parent(advancementEntry)
                .display(BItems.HEART_BEET,
                        Text.translatable("advancements.story.obtain_heart_beet.title"),
                        Text.translatable("advancements.story.obtain_heart_beet.description"),
                        null, AdvancementFrame.GOAL, true, true, false)
                .criterion("get_heart_beet", InventoryChangedCriterion.Conditions.items(ItemPredicate.Builder.create()
                        .items(registryEntryLookup, BItems.HEART_BEET)
                )).build(consumer, Beeten.MOD_ID +":husbandry/obtain_heart_beet");

    }
}
