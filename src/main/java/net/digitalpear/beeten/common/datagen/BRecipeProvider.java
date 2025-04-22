package net.digitalpear.beeten.common.datagen;

import net.digitalpear.beeten.init.BBlocks;
import net.digitalpear.beeten.init.BItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.CookingRecipeJsonBuilder;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.data.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class BRecipeProvider extends FabricRecipeProvider {
    public BRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
        return new RecipeGenerator(wrapperLookup, recipeExporter) {
            @Override
            public void generate() {
                offerCompactingRecipe(RecipeCategory.BUILDING_BLOCKS, BBlocks.BEETROOT_BLOCK, Items.BEETROOT, hasItem(Items.BEETROOT));

                BBlocks.BEETROOT_COOKING_MAP.forEach((input, output) -> {
                    CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(input), RecipeCategory.BUILDING_BLOCKS, output, 0.5f, 200).criterion(hasItem(input), conditionsFromItem(input)).offerTo(exporter, getItemPath(output));
                    CookingRecipeJsonBuilder.createCampfireCooking(Ingredient.ofItems(input), RecipeCategory.BUILDING_BLOCKS, output, 0.5f, 600).criterion(hasItem(input), conditionsFromItem(input)).offerTo(exporter, getName(output, "campfire_cooking"));
                    CookingRecipeJsonBuilder.createSmoking(Ingredient.ofItems(input), RecipeCategory.BUILDING_BLOCKS, output, 0.5f, 100).criterion(hasItem(input), conditionsFromItem(input)).offerTo(exporter, getName(output, "smoking"));
                });

                offerCutCopperRecipe(RecipeCategory.BUILDING_BLOCKS, BBlocks.BEETROOT_TILES, BBlocks.BEETROOT_BLOCK);

                ShapedRecipeJsonBuilder.create(wrapperLookup.getOrThrow(RegistryKeys.ITEM), RecipeCategory.MISC, BBlocks.BEETROOT_HEART)
                        .pattern("BBB")
                        .pattern("BLB")
                        .pattern("BBB")
                        .input('L', BBlocks.BEETROOT_BLOCK)
                        .input('B', BItems.HEART_BEET)
                        .criterion(hasItem(BItems.HEART_BEET), conditionsFromItem(BItems.HEART_BEET))
                        .offerTo(exporter);
            }
            private String getName(ItemConvertible itemConvertible, String cooker){
                return getItemPath(itemConvertible) + "_from_" + cooker;
            }
        };
    }

    @Override
    public String getName() {
        return "recipe";
    }
}
