package net.digitalpear.beeten.common.datagen;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.digitalpear.beeten.Beeten;
import net.digitalpear.beeten.init.BBlocks;
import net.digitalpear.beeten.init.BItems;
import net.digitalpear.beeten.init.data.ModCompat;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;

import net.minecraft.data.client.*;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class BModelProvider extends FabricModelProvider {
    public static final Model TEMPLATE_BEETROOT_SPROUT = block("template_beetroot_sprout", TextureKey.TOP, TextureKey.SIDE, TextureKey.PLANT);

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

        registerSeedlessCrop(blockStateModelGenerator, BBlocks.HEART_BEETS, Properties.AGE_3, 0, 1, 2, 3);

        registerBeetrootSprout(blockStateModelGenerator, BBlocks.BEETROOT_SPROUT);
        blockStateModelGenerator.registerSimpleCubeAll(BBlocks.BEETROOT_LEAVES);

        /*
            Compat
         */

        registerCrate(blockStateModelGenerator, BBlocks.HEART_BEET_CRATE);
    }

    public final void registerSeedlessCrop(BlockStateModelGenerator blockStateModelGenerator, Block crop, Property<Integer> ageProperty, int... ageTextureIndices) {
        if (ageProperty.getValues().size() != ageTextureIndices.length) {
            throw new IllegalArgumentException();
        } else {
            Int2ObjectMap<Identifier> int2ObjectMap = new Int2ObjectOpenHashMap<>();
            BlockStateVariantMap blockStateVariantMap = BlockStateVariantMap.create(ageProperty).register((integer) -> {
                int i = ageTextureIndices[integer];
                Identifier identifier = int2ObjectMap.computeIfAbsent(i, (j) -> blockStateModelGenerator.createSubModel(crop, "_stage" + i, Models.CROP, TextureMap::crop));
                return BlockStateVariant.create().put(VariantSettings.MODEL, identifier);
            });
            blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(crop).coordinate(blockStateVariantMap));
        }
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(BItems.HEART_BEET, Models.GENERATED);
    }
    public final void registerCrate(BlockStateModelGenerator blockStateModelGenerator, Block block) {
        TextureMap textureMap = new TextureMap().put(TextureKey.BOTTOM, Identifier.of(ModCompat.FD_ID, "block/crate_bottom")).put(TextureKey.TOP, TextureMap.getId(block)).put(TextureKey.SIDE, TextureMap.getSubId(block, "_side"));
        Identifier MODEL = Models.CUBE_BOTTOM_TOP.upload(block, textureMap, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(block, MODEL));
    }
    public final void registerBeetrootSprout(BlockStateModelGenerator blockStateModelGenerator, Block block) {
        Identifier weightedVariant = TEMPLATE_BEETROOT_SPROUT.upload(block, TextureMap.sideAndTop(block).put(TextureKey.PLANT, TextureMap.getSubId(block, "_plant")), blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(block, weightedVariant));
    }
}
