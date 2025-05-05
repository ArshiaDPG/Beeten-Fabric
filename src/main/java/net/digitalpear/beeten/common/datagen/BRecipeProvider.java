package net.digitalpear.beeten.common.datagen;

import net.digitalpear.beeten.init.BBlocks;
import net.digitalpear.beeten.init.BItems;
import net.digitalpear.beeten.init.data.ModCompat;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.impl.resource.conditions.conditions.AllModsLoadedResourceCondition;
import net.minecraft.data.server.recipe.CookingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class BRecipeProvider extends FabricRecipeProvider {
    public BRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter recipeExporter) {
        offerReversibleCompactingRecipes(withConditions(recipeExporter, new AllModsLoadedResourceCondition(List.of(ModCompat.FD_ID))), RecipeCategory.FOOD, BItems.HEART_BEET, RecipeCategory.BUILDING_BLOCKS, BBlocks.HEART_BEET_CRATE);
        BBlocks.BEETROOT_COOKING_MAP.forEach((input, output) -> {
            CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(input), RecipeCategory.BUILDING_BLOCKS, output, 0.5f, 200).criterion(hasItem(input), conditionsFromItem(input)).offerTo(recipeExporter, getItemPath(output));
            CookingRecipeJsonBuilder.createCampfireCooking(Ingredient.ofItems(input), RecipeCategory.BUILDING_BLOCKS, output, 0.5f, 600).criterion(hasItem(input), conditionsFromItem(input)).offerTo(recipeExporter, getName(output, "campfire_cooking"));
            CookingRecipeJsonBuilder.createSmoking(Ingredient.ofItems(input), RecipeCategory.BUILDING_BLOCKS, output, 0.5f, 100).criterion(hasItem(input), conditionsFromItem(input)).offerTo(recipeExporter, getName(output, "smoking"));
        });
        offerCutCopperRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, BBlocks.BEETROOT_TILES, BBlocks.BEETROOT_BLOCK);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, BBlocks.BEETROOT_HEART)
                .pattern("BBB")
                .pattern("BLB")
                .pattern("BBB")
                .input('L', BBlocks.BEETROOT_BLOCK)
                .input('B', BItems.HEART_BEET)
                .criterion(hasItem(BItems.HEART_BEET), conditionsFromItem(BItems.HEART_BEET))
                .offerTo(recipeExporter);
    }
    public static void offerReversibleCompactingRecipes(RecipeExporter exporter, RecipeCategory reverseCategory, ItemConvertible baseItem, RecipeCategory compactingCategory, ItemConvertible compactItem, String compactingId, @Nullable String compactingGroup, String reverseId, @Nullable String reverseGroup) {
        ShapelessRecipeJsonBuilder.create(reverseCategory, baseItem, 9).input(compactItem).group(reverseGroup).criterion(hasItem(compactItem), conditionsFromItem(compactItem)).offerTo(exporter, Identifier.of(reverseId));
        ShapedRecipeJsonBuilder.create(compactingCategory, compactItem).input('#', baseItem).pattern("###").pattern("###").pattern("###").group(compactingGroup).criterion(hasItem(baseItem), conditionsFromItem(baseItem)).offerTo(exporter, Identifier.of(compactingId));
    }
    private String getName(ItemConvertible itemConvertible, String cooker){
        return getItemPath(itemConvertible) + "_from_" + cooker;
    }
}
