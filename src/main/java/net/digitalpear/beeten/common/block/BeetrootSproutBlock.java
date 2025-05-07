package net.digitalpear.beeten.common.block;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.digitalpear.beeten.common.block.compat.CompatRequired;
import net.digitalpear.beeten.init.BTags;
import net.digitalpear.beeten.init.worldgen.BConfiguredFeatures;
import net.minecraft.block.*;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import java.util.List;

public class BeetrootSproutBlock extends PlantBlock implements Fertilizable, CompatRequired {
    public static final MapCodec<BeetrootSproutBlock> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
            TagKey.codec(RegistryKeys.BLOCK).fieldOf("supporting_blocks").orElse(BTags.Blocks.BEETROOT_SPROUT_PLACEABLE_ON).forGetter(beetrootSproutBlock -> beetrootSproutBlock.supportingFloor),
            Codec.list(Codecs.NON_EMPTY_STRING).fieldOf("required_mods").orElse(List.of()).forGetter(BeetrootSproutBlock::requiredMods),
            RegistryKey.createCodec(RegistryKeys.CONFIGURED_FEATURE).fieldOf("feature").orElse(BConfiguredFeatures.BIG_BEETROOT_GROWN).forGetter((block) -> block.feature),
            createSettingsCodec()
    ).apply(instance, BeetrootSproutBlock::new));
    private static final VoxelShape SHAPE = VoxelShapes.union(Block.createColumnShape(16.0, 8.0, 16.0), Block.createColumnShape(4.0, 0.0, 8.0));

    private final List<String> requiredMods;
    private final RegistryKey<ConfiguredFeature<?, ?>> feature;
    private final TagKey<Block> supportingFloor;

    public BeetrootSproutBlock(Settings settings) {
        this(BTags.Blocks.BEETROOT_SPROUT_PLACEABLE_ON, List.of(), BConfiguredFeatures.BIG_BEETROOT_GROWN, settings);
    }
    public BeetrootSproutBlock(TagKey<Block> supportingFloor, List<String> requiredMods, RegistryKey<ConfiguredFeature<?, ?>> feature, Settings settings) {
        super(settings);
        this.requiredMods = requiredMods;
        this.feature = feature;
        this.supportingFloor = supportingFloor;
    }
    public BeetrootSproutBlock(List<String> requiredMods, RegistryKey<ConfiguredFeature<?, ?>> feature, Settings settings){
        this(BTags.Blocks.BEETROOT_SPROUT_PLACEABLE_ON, requiredMods, feature, settings);
    }
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        return world.getFluidState(pos.up()).isEmpty();
    }

    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return (double)world.random.nextFloat() < 0.45;
    }
    protected boolean canPathfindThrough(BlockState state, NavigationType type) {
        return false;
    }

    @Override
    protected MapCodec<? extends PlantBlock> getCodec() {
        return CODEC;
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isIn(supportingFloor);
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        world.getRegistryManager().getOptional(RegistryKeys.CONFIGURED_FEATURE).flatMap((registry) ->
                registry.getOptional(feature)).ifPresent((entry) -> {
            entry.value().generate(world, world.getChunkManager().getChunkGenerator(), random, pos);
        });
    }

    @Override
    public List<String> requiredMods() {
        return this.requiredMods;
    }
}
