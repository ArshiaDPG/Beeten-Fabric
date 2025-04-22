package net.digitalpear.beeten.common.block;

import net.digitalpear.beeten.init.BBlocks;
import net.digitalpear.beeten.init.BTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldEvents;

public class BeetrootHeartBlock extends PillarBlock {
    public static final int MAX_ATTEMPTS = 3;
    public static final int H_RANGE = 6;
    public static final int V_RANGE = 1;
    public BeetrootHeartBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (random.nextInt(1) == 0){
            for (int i = 0; i<random.nextBetween(1, MAX_ATTEMPTS); i++){
                int x = random.nextBetween(-H_RANGE, H_RANGE);
                int y = random.nextBetween(-V_RANGE, V_RANGE);
                int z = random.nextBetween(-H_RANGE, H_RANGE);
                BlockPos currentPos = pos.add(x, y, z);
                BlockState chosenState = world.getBlockState(currentPos);
                if (chosenState.isIn(BTags.Blocks.CAN_CONVERT_T0_HEART_BEETROOTS) && BBlocks.HEART_BEETS.getDefaultState().canPlaceAt(world, currentPos)){
                    world.setBlockState(currentPos, BBlocks.HEART_BEETS.getDefaultState());
                    world.syncWorldEvent(WorldEvents.BONE_MEAL_USED, currentPos, 15);
                }
            }
        }
    }
}
