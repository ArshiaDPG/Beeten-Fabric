package net.digitalpear.beeten.common.datagen;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.digitalpear.beeten.Beeten;
import net.digitalpear.beeten.init.BBlocks;
import net.digitalpear.beeten.init.BItems;
import net.digitalpear.beeten.init.data.ModCompat;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.data.*;
import net.minecraft.client.render.model.json.WeightedVariant;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class BModelProvider extends FabricModelProvider {
    public static final Model TEMPLATE_BEETROOT_SPROUT = block("template_beetroot_sprout", TextureKey.TOP, TextureKey.SIDE, TextureKey.PLANT);
    private static Model block(String parent, TextureKey... requiredTextureKeys) {
        return new Model(Optional.of(Beeten.id("block/" + parent)), Optional.empty(), requiredTextureKeys);
    }
    public BModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.createLogTexturePool(BBlocks.BEETROOT_BLOCK).stem(BBlocks.BEETROOT_BLOCK);
        blockStateModelGenerator.createLogTexturePool(BBlocks.BEETROOT_TILES).stem(BBlocks.BEETROOT_TILES);
        blockStateModelGenerator.createLogTexturePool(BBlocks.BEETROOT_HEART).stem(BBlocks.BEETROOT_HEART);

        blockStateModelGenerator.createLogTexturePool(BBlocks.COOKED_BEETROOT_BLOCK).stem(BBlocks.COOKED_BEETROOT_BLOCK);
        blockStateModelGenerator.createLogTexturePool(BBlocks.COOKED_BEETROOT_TILES).stem(BBlocks.COOKED_BEETROOT_TILES);

        blockStateModelGenerator.registerTintableCross(BBlocks.BEET_ROOTS, BlockStateModelGenerator.CrossType.NOT_TINTED);
        registerCrop(blockStateModelGenerator, BBlocks.HEART_BEETS, Properties.AGE_3, 0, 1, 2, 3);

        registerBeetrootSprout(blockStateModelGenerator, BBlocks.BEETROOT_SPROUT);
        blockStateModelGenerator.registerSimpleCubeAll(BBlocks.BEETROOT_LEAVES);

        registerCrate(blockStateModelGenerator, BBlocks.HEART_BEET_CRATE);

        blockStateModelGenerator.createLogTexturePool(BBlocks.SOULROOT_BLOCK).stem(BBlocks.SOULROOT_BLOCK);
        blockStateModelGenerator.createLogTexturePool(BBlocks.SOULROOT_TILES).stem(BBlocks.SOULROOT_TILES);
        blockStateModelGenerator.registerSimpleCubeAll(BBlocks.SOULROOT_LEAVES);

    }
    public final void registerCrop(BlockStateModelGenerator blockStateModelGenerator, Block crop, Property<Integer> ageProperty, int... ageTextureIndices) {
        if (ageProperty.getValues().size() != ageTextureIndices.length) {
            throw new IllegalArgumentException();
        } else {
            Int2ObjectMap<Identifier> int2ObjectMap = new Int2ObjectOpenHashMap<>();
            blockStateModelGenerator.blockStateCollector.accept(VariantsBlockModelDefinitionCreator.of(crop).with(BlockStateVariantMap.models(ageProperty).generate((age) -> {
                int i = ageTextureIndices[age];
                return BlockStateModelGenerator.createWeightedVariant(int2ObjectMap.computeIfAbsent(i, (stage) -> blockStateModelGenerator.createSubModel(crop, "_stage" + stage, Models.CROP, TextureMap::crop)));
            })));
        }
    }
    public final void registerCrate(BlockStateModelGenerator blockStateModelGenerator, Block block) {
        TextureMap textureMap = new TextureMap().put(TextureKey.BOTTOM, Identifier.of(ModCompat.FD_ID, "block/crate_bottom")).put(TextureKey.TOP, TextureMap.getId(block)).put(TextureKey.SIDE, TextureMap.getSubId(block, "_side"));
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(block, BlockStateModelGenerator.createWeightedVariant(Models.CUBE_BOTTOM_TOP.upload(block, textureMap, blockStateModelGenerator.modelCollector))));
    }
    public final void registerBeetrootSprout(BlockStateModelGenerator blockStateModelGenerator, Block block) {
        WeightedVariant weightedVariant = BlockStateModelGenerator.createWeightedVariant(TEMPLATE_BEETROOT_SPROUT.upload(block, TextureMap.sideAndTop(block).put(TextureKey.PLANT, TextureMap.getSubId(block, "_plant")), blockStateModelGenerator.modelCollector));
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(block, weightedVariant));
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(BItems.HEART_BEET, Models.GENERATED);
    }
}
