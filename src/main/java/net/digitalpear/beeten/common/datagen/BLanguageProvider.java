package net.digitalpear.beeten.common.datagen;

import net.digitalpear.beeten.init.BBlocks;
import net.digitalpear.beeten.init.BGameRules;
import net.digitalpear.beeten.init.BItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class BLanguageProvider extends FabricLanguageProvider {
    public BLanguageProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(BBlocks.BEETROOT_BLOCK, "Block of Beetroot");
        translationBuilder.add(BBlocks.BEETROOT_TILES, "Beetroot Tiles");

        translationBuilder.add(BBlocks.BEETROOT_HEART, "Heart of Beetroot");
        translationBuilder.add(BBlocks.HEART_BEETS, "Heart Beets");
        translationBuilder.add(BItems.HEART_BEET, "Heart Beet");

        translationBuilder.add(BBlocks.COOKED_BEETROOT_BLOCK, "Cooked Beetroot");
        translationBuilder.add(BBlocks.COOKED_BEETROOT_TILES, "Cooked Beetroot Tiles");

        translationBuilder.add(BBlocks.BEETROOT_LEAVES, "Beetroot Leaves");
        translationBuilder.add(BBlocks.BEETROOT_SPROUT, "Beetroot Sprout");

        translationBuilder.add(BBlocks.BEET_ROOTS, "Beet Roots");

        translationBuilder.add(BGameRules.MAX_HEART_BEAT_CONSUMPTION.getTranslationKey(), "Max health boost from Heart Beets");
        translationBuilder.add(BGameRules.MAX_HEART_BEAT_CONSUMPTION.getTranslationKey() + ".description", "The max amount of health that an entity can get from eating heart beets.");

        translationBuilder.add("advancements.story.obtain_heart_beet.title", "Good for the Heart");
        translationBuilder.add("advancements.story.obtain_heart_beet.description", "Obtain a Heart Beet by converting beetroot using a Heart of Beetroot.");

        translationBuilder.add(BBlocks.SOULROOT_BLOCK, "Soulroot Block");
        translationBuilder.add(BBlocks.SOULROOT_TILES, "Soulroot Tiles");
        translationBuilder.add(BBlocks.SOULROOT_LEAVES, "Soulroot Leaves");
    }
}
