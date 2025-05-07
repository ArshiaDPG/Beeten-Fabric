package net.digitalpear.beeten.common.block;

import net.digitalpear.beeten.init.BBlocks;
import net.digitalpear.beeten.init.BTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;

public class BeetrootHeartBlock extends PillarBlock {
    public static final int MAX_ATTEMPTS = 3;
    public static final int H_RANGE = 6;
    public static final int V_RANGE = 1;
    private final BlockState convertingState;

    public BeetrootHeartBlock(BlockState convertingState, Settings settings) {
        super(settings);
        this.convertingState = convertingState;
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (random.nextInt(1) == 0){
            for (int i = 0; i < UniformIntProvider.create(1, MAX_ATTEMPTS).get(random); i++){
                int x = random.nextBetween(-getHorizontalRange(), getHorizontalRange());
                int y = random.nextBetween(-getVerticalRange(), getVerticalRange());
                int z = random.nextBetween(-getHorizontalRange(), getHorizontalRange());
                BlockPos currentPos = pos.add(x, y, z);
                BlockState chosenState = world.getBlockState(currentPos);
                if (canConvertState(world, currentPos, chosenState)){
                    BlockState placedState = convertingState;
                    if (chosenState.contains(HearBeetsBlock.AGE)){
                        placedState.with(HearBeetsBlock.AGE, chosenState.get(HearBeetsBlock.AGE));
                    }
                    world.setBlockState(currentPos, placedState);
                    world.syncWorldEvent(WorldEvents.BONE_MEAL_USED, currentPos, 15);
                }
            }
        }
    }

    public boolean canConvertState(World world, BlockPos pos, BlockState state){
        return state.isIn(BTags.Blocks.CAN_CONVERT_T0_HEART_BEETROOTS) && convertingState.canPlaceAt(world, pos);
    }

    public int getHorizontalRange(){
        return H_RANGE;
    }
    public int getVerticalRange(){
        return V_RANGE;
    }
}