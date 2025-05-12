package net.digitalpear.beeten.common.datagen;

import net.digitalpear.beeten.init.BBlocks;
import net.digitalpear.beeten.init.BGameRules;
import net.digitalpear.beeten.init.BItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class BLanguageProvider extends FabricLanguageProvider {
    public BLanguageProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(BBlocks.BEETROOT_BLOCK, "Block of Beetroot");
        translationBuilder.add(BBlocks.COOKED_BEETROOT_BLOCK, "Cooked Beetroot");
        translationBuilder.add(BBlocks.SOULROOT_BLOCK, "Block of Soulroot");

        translationBuilder.add(BBlocks.BEETROOT_HEART, "Heart of Beetroot");

        makeTranslation(translationBuilder, BBlocks.BEETROOT_TILES);
        makeTranslation(translationBuilder, BBlocks.COOKED_BEETROOT_TILES);
        makeTranslation(translationBuilder, BBlocks.SOULROOT_TILES);

        makeTranslation(translationBuilder, BBlocks.BEET_ROOTS);
        makeTranslation(translationBuilder, BBlocks.SOUL_ROOTS);

        makeTranslation(translationBuilder, BBlocks.BEETROOT_SPROUT);
        makeTranslation(translationBuilder, BBlocks.SOULROOT_SPROUT);

        makeTranslation(translationBuilder, BBlocks.SOULROOT_LEAVES);

        makeTranslation(translationBuilder, BItems.HEART_BEET);

        makeTranslation(translationBuilder, BBlocks.HEART_BEET_CRATE);

        translationBuilder.add(BGameRules.MAX_HEART_BEAT_CONSUMPTION.getTranslationKey(), "Max health boost from Heart Beets");
        translationBuilder.add(BGameRules.MAX_HEART_BEAT_CONSUMPTION.getTranslationKey() + ".description", "The max amount of health that an entity can get from eating heart beets.");

        translationBuilder.add("advancements.story.obtain_heart_beet.title", "Good for the Heart");
        translationBuilder.add("advancements.story.obtain_heart_beet.description", "Obtain a Heart Beet by converting beetroot using a Heart of Beetroot.");
    }

    private void makeTranslation(TranslationBuilder builder, Block block){
        builder.add(block, autoNameInner(Registries.BLOCK.getId(block).getPath()));
    }
    private void makeTranslation(TranslationBuilder builder, Item item){
        builder.add(item, autoNameInner(Registries.ITEM.getId(item).getPath()));
    }

    private String autoNameInner(String id) {
        StringBuilder name = new StringBuilder();
        String[] split = id.split("_");
        for(String str : split) {
            if(!name.isEmpty()) {
                name.append(" ");
            }
            name.append(capitalize(str));
        }
        return name.toString();
    }

    public static String capitalize(String inputString) {

        // get the first character of the inputString
        char firstLetter = inputString.charAt(0);

        // return the output string by updating
        //the first char of the input string
        return inputString.replaceFirst(String.valueOf(firstLetter), String.valueOf(firstLetter).toUpperCase());
    }
}
