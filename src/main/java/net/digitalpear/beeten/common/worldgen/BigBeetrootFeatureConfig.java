package net.digitalpear.beeten.common.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.floatprovider.FloatProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public record BigBeetrootFeatureConfig(BlockStateProvider baseBlock, BlockStateProvider heart,
                                       BlockStateProvider leaves, BlockStateProvider sprouts, BlockStateProvider roots,
                                       BlockStateProvider hangingRoots, IntProvider rootsRadius,
                                       IntProvider firstBaseHeight, IntProvider secondBaseHeight,
                                       IntProvider branchLength, IntProvider leafHeight,
                                       FloatProvider heartChance) implements FeatureConfig {
    public static final Codec<BigBeetrootFeatureConfig> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            BlockStateProvider.TYPE_CODEC.fieldOf("base_block").forGetter(BigBeetrootFeatureConfig::baseBlock),
            BlockStateProvider.TYPE_CODEC.fieldOf("heart").forGetter(BigBeetrootFeatureConfig::heart),
            BlockStateProvider.TYPE_CODEC.fieldOf("leaves").forGetter(BigBeetrootFeatureConfig::leaves),
            BlockStateProvider.TYPE_CODEC.fieldOf("sprouts").forGetter(BigBeetrootFeatureConfig::sprouts),
            BlockStateProvider.TYPE_CODEC.fieldOf("roots").forGetter(BigBeetrootFeatureConfig::roots),
            BlockStateProvider.TYPE_CODEC.fieldOf("hanging_roots").forGetter(BigBeetrootFeatureConfig::hangingRoots),
            IntProvider.NON_NEGATIVE_CODEC.fieldOf("roots_radius").forGetter(BigBeetrootFeatureConfig::rootsRadius),
            IntProvider.NON_NEGATIVE_CODEC.fieldOf("first_base_height").forGetter(BigBeetrootFeatureConfig::firstBaseHeight),
            IntProvider.NON_NEGATIVE_CODEC.fieldOf("second_base_height").forGetter(BigBeetrootFeatureConfig::secondBaseHeight),
            IntProvider.NON_NEGATIVE_CODEC.fieldOf("branch_length").forGetter(BigBeetrootFeatureConfig::branchLength),
            IntProvider.NON_NEGATIVE_CODEC.fieldOf("leaf_height").forGetter(BigBeetrootFeatureConfig::leafHeight),
            FloatProvider.createValidatedCodec(0.0F, 1.0F).fieldOf("heart_chance").forGetter(BigBeetrootFeatureConfig::heartChance)
    ).apply(instance, BigBeetrootFeatureConfig::new));
}
