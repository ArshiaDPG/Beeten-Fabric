package net.digitalpear.beeten.common.datagen.recipes;

import net.digitalpear.beeten.init.BBlocks;
import net.digitalpear.beeten.init.data.ModCompat;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class BSNRecipeProvider extends FabricRecipeProvider {
    public BSNRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
        return new RecipeGenerator(wrapperLookup, withConditions(recipeExporter, ResourceConditions.allModsLoaded(ModCompat.SN_ID))) {
            @Override
            public void generate() {
                offerCutCopperRecipe(RecipeCategory.BUILDING_BLOCKS, BBlocks.SOULROOT_TILES, BBlocks.SOULROOT_BLOCK);
            }
        };
    }

    @Override
    public String getName() {
        return "Soulful Nether Recipes";
    }
}
