package net.digitalpear.beeten.init.worldgen;

import net.digitalpear.beeten.Beeten;
import net.digitalpear.beeten.common.worldgen.BigBeetrootFeatureConfig;
import net.digitalpear.beeten.common.worldgen.BigBeetrootFeature;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;

public class BFeature {

    public static final Feature<BigBeetrootFeatureConfig> BIG_BEETROOT = register("big_beetroot", new BigBeetrootFeature(BigBeetrootFeatureConfig.CODEC));
    private static <C extends FeatureConfig, F extends Feature<C>> F register(String name, F feature) {
        return Registry.register(Registries.FEATURE, Beeten.id(name), feature);
    }

    public static void init() {

    }
}
