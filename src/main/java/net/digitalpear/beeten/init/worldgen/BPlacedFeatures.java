package net.digitalpear.beeten.init.worldgen;

import com.google.common.collect.ImmutableList;
import net.digitalpear.beeten.Beeten;
import net.digitalpear.beeten.init.BTags;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.ArrayList;
import java.util.List;

public class BPlacedFeatures {
    public static List<RegistryKey<PlacedFeature>> placedFeatures = new ArrayList<>();
    private static RegistryKey<PlacedFeature> of(String id) {
        RegistryKey<PlacedFeature> feature = RegistryKey.of(RegistryKeys.PLACED_FEATURE, Beeten.id(id));
        placedFeatures.add(feature);
        return feature;
    }


    public static final RegistryKey<PlacedFeature> BIG_BEETROOT = of("big_beetroot");
    public static final RegistryKey<PlacedFeature> BIG_SOULROOT = of("big_soulroot");
    private static final PlacementModifier NOT_IN_SURFACE_WATER_MODIFIER = SurfaceWaterDepthFilterPlacementModifier.of(0);


    private static ImmutableList.Builder<PlacementModifier> treeModifiersBuilder(PlacementModifier countModifier) {
        return ImmutableList.<PlacementModifier>builder().add(countModifier).add(SquarePlacementModifier.of()).add(NOT_IN_SURFACE_WATER_MODIFIER).add(PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP).add(BiomePlacementModifier.of());
    }
    public static void bootstrap(Registerable<PlacedFeature> featureRegisterable) {
        RegistryEntryLookup<ConfiguredFeature<?, ?>> registryEntryLookup = featureRegisterable.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);
        RegistryEntry<ConfiguredFeature<?, ?>> bigBeetFeature = registryEntryLookup.getOrThrow(BConfiguredFeatures.BIG_BEETROOT);
        RegistryEntry<ConfiguredFeature<?, ?>> bigSoulFeature = registryEntryLookup.getOrThrow(BConfiguredFeatures.BIG_SOULROOT);

        PlacedFeatures.register(featureRegisterable, BIG_BEETROOT, bigBeetFeature, treeModifiersBuilder(RarityFilterPlacementModifier.of(4)).build());
        PlacedFeatures.register(featureRegisterable, BIG_SOULROOT, bigSoulFeature, CountMultilayerPlacementModifier.of(8), BiomePlacementModifier.of());
    }


    public static void init() {
        BiomeModifications.addFeature(BiomeSelectors.tag(BTags.Biomes.SPAWNS_BEETROOT_FEATURES), GenerationStep.Feature.VEGETAL_DECORATION, BIG_BEETROOT);
        BiomeModifications.addFeature(BiomeSelectors.tag(BTags.Biomes.SPAWNS_SOULROOT_FEATURES), GenerationStep.Feature.VEGETAL_DECORATION, BIG_SOULROOT);
    }
}
