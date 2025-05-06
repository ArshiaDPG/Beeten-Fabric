package net.digitalpear.beeten.common.datagen.recipes;

import net.digitalpear.beeten.init.BBlocks;
import net.digitalpear.beeten.init.BItems;
import net.digitalpear.beeten.init.data.ModCompat;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;
import net.minecraft.data.DataOutput;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class BFDRecipeProvider extends FabricRecipeProvider {

    public BFDRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
        return new RecipeGenerator(wrapperLookup, withConditions(recipeExporter, ResourceConditions.allModsLoaded(ModCompat.FD_ID))) {
            @Override
            public void generate() {
                offerReversibleCompactingRecipes(RecipeCategory.FOOD, BItems.HEART_BEET, RecipeCategory.BUILDING_BLOCKS, BBlocks.HEART_BEET_CRATE);
            }
        };
    }

    @Override
    public String getName() {
        return "Farmer's Delight Recipes";
    }
}
