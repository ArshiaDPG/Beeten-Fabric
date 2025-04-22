package net.digitalpear.beeten.common.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.floatprovider.FloatProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public class BigBeetrootFeatureConfig implements FeatureConfig {
    public static final Codec<BigBeetrootFeatureConfig> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            BlockStateProvider.TYPE_CODEC.fieldOf("base_block").forGetter(BigBeetrootFeatureConfig::getBaseBlock),
            BlockStateProvider.TYPE_CODEC.fieldOf("heart").forGetter(BigBeetrootFeatureConfig::getHeart),
            BlockStateProvider.TYPE_CODEC.fieldOf("leaves").forGetter(BigBeetrootFeatureConfig::getLeaves),
            BlockStateProvider.TYPE_CODEC.fieldOf("sprouts").forGetter(BigBeetrootFeatureConfig::getSprouts),
            BlockStateProvider.TYPE_CODEC.fieldOf("roots").forGetter(BigBeetrootFeatureConfig::getRoots),
            BlockStateProvider.TYPE_CODEC.fieldOf("hanging_roots").forGetter(BigBeetrootFeatureConfig::getHangingRoots),
            IntProvider.NON_NEGATIVE_CODEC.fieldOf("roots_radius").forGetter(BigBeetrootFeatureConfig::getRootsRadius),
            IntProvider.NON_NEGATIVE_CODEC.fieldOf("first_base_height").forGetter(BigBeetrootFeatureConfig::getFirstBaseHeight),
            IntProvider.NON_NEGATIVE_CODEC.fieldOf("second_base_height").forGetter(BigBeetrootFeatureConfig::getSecondBaseHeight),
            IntProvider.NON_NEGATIVE_CODEC.fieldOf("branch_length").forGetter(BigBeetrootFeatureConfig::getBranchLength),
            IntProvider.NON_NEGATIVE_CODEC.fieldOf("leaf_height").forGetter(BigBeetrootFeatureConfig::getLeafHeight),
            FloatProvider.createValidatedCodec(0.0F, 1.0F).fieldOf("heart_chance").forGetter(BigBeetrootFeatureConfig::getHeartChance)
    ).apply(instance, BigBeetrootFeatureConfig::new));

    private final BlockStateProvider baseBlock;
    private final BlockStateProvider heart;
    private final BlockStateProvider leaves;
    private final BlockStateProvider sprouts;
    private final BlockStateProvider roots;
    private final BlockStateProvider hangingRoots;
    private final IntProvider rootsRadius;
    private final IntProvider firstBaseHeight;
    private final IntProvider secondBaseHeight;
    private final IntProvider branchLength;
    private final IntProvider leafHeight;
    private final FloatProvider heartChance;

    public BigBeetrootFeatureConfig(BlockStateProvider baseBlock, BlockStateProvider heart, BlockStateProvider leaves, BlockStateProvider sprouts, BlockStateProvider roots, BlockStateProvider hangingRoots, IntProvider rootsRadius, IntProvider firstBaseHeight, IntProvider secondBaseHeight, IntProvider branchLength, IntProvider leafHeight, FloatProvider heartChance) {
        this.baseBlock = baseBlock;
        this.heart = heart;
        this.leaves = leaves;
        this.sprouts = sprouts;
        this.roots = roots;
        this.hangingRoots = hangingRoots;
        this.rootsRadius = rootsRadius;
        this.firstBaseHeight = firstBaseHeight;
        this.secondBaseHeight = secondBaseHeight;
        this.branchLength = branchLength;
        this.leafHeight = leafHeight;
        this.heartChance = heartChance;
    }

    public BlockStateProvider getBaseBlock() {
        return baseBlock;
    }

    public BlockStateProvider getHeart() {
        return heart;
    }

    public BlockStateProvider getLeaves() {
        return leaves;
    }

    public BlockStateProvider getRoots() {
        return roots;
    }

    public BlockStateProvider getHangingRoots() {
        return hangingRoots;
    }

    public BlockStateProvider getSprouts() {
        return sprouts;
    }

    public IntProvider getSecondBaseHeight() {
        return secondBaseHeight;
    }

    public IntProvider getFirstBaseHeight() {
        return firstBaseHeight;
    }

    public IntProvider getRootsRadius() {
        return rootsRadius;
    }

    public IntProvider getBranchLength() {
        return branchLength;
    }

    public IntProvider getLeafHeight() {
        return leafHeight;
    }

    public FloatProvider getHeartChance() {
        return heartChance;
    }
}
