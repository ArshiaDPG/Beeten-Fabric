package net.digitalpear.beeten.init.worldgen;

import net.digitalpear.beeten.Beeten;
import net.digitalpear.beeten.common.worldgen.BigBeetrootFeatureConfig;
import net.digitalpear.beeten.init.BBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.collection.Pool;
import net.minecraft.util.math.floatprovider.ConstantFloatProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;

import java.util.ArrayList;
import java.util.List;

public class BConfiguredFeatures {
    public static List<RegistryKey<ConfiguredFeature<?, ?>>> features = new ArrayList<>();
    private static RegistryKey<ConfiguredFeature<?, ?>> of(String id) {
        RegistryKey<ConfiguredFeature<?, ?>> feature = RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Beeten.id(id));
        features.add(feature);
        return feature;
    }

    public static final RegistryKey<ConfiguredFeature<?, ?>> BIG_BEETROOT = of("big_beetroot");
    public static final RegistryKey<ConfiguredFeature<?, ?>> BIG_BEETROOT_GROWN = of("big_beetroot_grown");

    private static BigBeetrootFeatureConfig config(float chance){
        DataPool<BlockState> ROOT_POOL = DataPool.<BlockState>builder()
                .add(Blocks.ROOTED_DIRT.getDefaultState(), 5)
                .add(Blocks.DIRT.getDefaultState(), 3)
                .add(BBlocks.BEETROOT_BLOCK.getDefaultState(), 1)
                .build();
        return new BigBeetrootFeatureConfig(
                SimpleBlockStateProvider.of(BBlocks.BEETROOT_BLOCK),
                SimpleBlockStateProvider.of(BBlocks.BEETROOT_HEART),
                SimpleBlockStateProvider.of(BBlocks.BEETROOT_LEAVES),
                SimpleBlockStateProvider.of(BBlocks.BEETROOT_SPROUT),
                new WeightedBlockStateProvider(ROOT_POOL),
                SimpleBlockStateProvider.of(BBlocks.BEET_ROOTS),
                //Roots Radius
                UniformIntProvider.create(2, 3), //First Base Height
                UniformIntProvider.create(2, 3), //Second Base Height
                UniformIntProvider.create(2, 3), //Branch Height
                UniformIntProvider.create(4, 7), //Leaf height
                UniformIntProvider.create(2, 3),
                ConstantFloatProvider.create(chance));
    }

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> featureRegisterable) {
        ConfiguredFeatures.register(featureRegisterable, BIG_BEETROOT, BFeature.BIG_BEETROOT, config(1.0f));
        ConfiguredFeatures.register(featureRegisterable, BIG_BEETROOT_GROWN, BFeature.BIG_BEETROOT, config(0.0f));
    }
}
