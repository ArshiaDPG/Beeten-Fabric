package net.digitalpear.beeten.init;

import net.digitalpear.beeten.Beeten;
import net.minecraft.block.Block;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.world.biome.Biome;

public class BTags {
    private static <T> TagKey<T> of(String id, RegistryKey<? extends Registry<T>> registryRef){
        return TagKey.of(registryRef, Beeten.id(id));
    }

    public static class Blocks {
        private static TagKey<Block> of(String id){
            return BTags.of(id, RegistryKeys.BLOCK);
        }

        public static final TagKey<Block> HEART_BEETS_PLACEABLE_ON = of("heart_beets_placeable_on");
        public static final TagKey<Block> BEETROOT_SPROUT_PLACEABLE_ON = of("beetroot_sprout_placeable_on");
        public static final TagKey<Block> BIG_BEETROOTS_CAN_REPLACE = of("big_beetroots_can_replace");
        public static final TagKey<Block> CAN_CONVERT_T0_HEART_BEETROOTS = of("can_convert_to_heart_beetroot");
    }
    public static class Biomes {
        private static TagKey<Biome> of(String id){
            return BTags.of(id, RegistryKeys.BIOME);
        }
        public static final TagKey<Biome> SPAWNS_BEETROOT_FEATURES = of("spawns_beetroot_features");
        public static final TagKey<Biome> SPAWNS_SOULROOT_FEATURES = of("spawns_soulroot_features");

    }
}
