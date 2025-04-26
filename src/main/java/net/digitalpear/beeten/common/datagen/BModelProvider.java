package net.digitalpear.beeten.common.datagen;

import net.digitalpear.beeten.Beeten;
import net.digitalpear.beeten.init.BBlocks;
import net.digitalpear.beeten.init.BItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;

import net.minecraft.data.client.*;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class BModelProvider extends FabricModelProvider {
    public static final Model TEMPLATE_BEETROOT_SPROUT = block("template_beetroot_sprout", TextureKey.TOP, TextureKey.SIDE);

    public BModelProvider(FabricDataOutput output) {
        super(output);
    }

    private static Model block(String parent, TextureKey... requiredTextureKeys) {
        return new Model(Optional.of(Beeten.id("block/" + parent)), Optional.empty(), requiredTextureKeys);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerLog(BBlocks.BEETROOT_BLOCK).stem(BBlocks.BEETROOT_BLOCK);
        blockStateModelGenerator.registerLog(BBlocks.BEETROOT_TILES).stem(BBlocks.BEETROOT_TILES);
        blockStateModelGenerator.registerLog(BBlocks.BEETROOT_HEART).stem(BBlocks.BEETROOT_HEART);

        blockStateModelGenerator.registerLog(BBlocks.COOKED_BEETROOT_BLOCK).stem(BBlocks.COOKED_BEETROOT_BLOCK);
        blockStateModelGenerator.registerLog(BBlocks.COOKED_BEETROOT_TILES).stem(BBlocks.COOKED_BEETROOT_TILES);

        blockStateModelGenerator.registerTintableCross(BBlocks.BEET_ROOTS, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerCrop(BBlocks.HEART_BEETS, Properties.AGE_3, 0, 1, 2, 3);

        registerBeetrootSprout(blockStateModelGenerator, BBlocks.BEETROOT_SPROUT);
        blockStateModelGenerator.registerSimpleCubeAll(BBlocks.BEETROOT_LEAVES);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(BItems.HEART_BEET, Models.GENERATED);
    }

    public final void registerBeetrootSprout(BlockStateModelGenerator blockStateModelGenerator, Block block) {
        Identifier weightedVariant = TEMPLATE_BEETROOT_SPROUT.upload(block, TextureMap.sideAndTop(block), blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(block, weightedVariant));
    }



}
