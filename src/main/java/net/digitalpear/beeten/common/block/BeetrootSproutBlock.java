package net.digitalpear.beeten.common.block;

import net.digitalpear.beeten.init.BTags;
import net.digitalpear.beeten.init.worldgen.BConfiguredFeatures;
import net.minecraft.block.AzaleaBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;

public class BeetrootSproutBlock extends AzaleaBlock {
    public BeetrootSproutBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isIn(BTags.Blocks.BEETROOT_SPROUT_PLACEABLE_ON);
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        world.setBlockState(pos, Blocks.AIR.getDefaultState());
        world.getRegistryManager().getOptional(RegistryKeys.CONFIGURED_FEATURE).flatMap((registry) ->
                registry.getEntry(BConfiguredFeatures.BIG_BEETROOT_GROWN)).ifPresent((entry) -> {
            entry.value().generate(world, world.getChunkManager().getChunkGenerator(), random, pos);
        });
    }
}
