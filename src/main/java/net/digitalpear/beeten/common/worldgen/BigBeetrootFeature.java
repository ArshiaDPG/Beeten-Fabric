package net.digitalpear.beeten.common.worldgen;

import com.mojang.serialization.Codec;
import net.digitalpear.beeten.init.BBlocks;
import net.digitalpear.beeten.init.BTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

import java.util.List;
import java.util.function.Function;

public class BigBeetrootFeature extends Feature<BigBeetrootFeatureConfig> {

    public static final List<Direction> HORIZONTAL_DIRECTIONS = List.of(Direction.EAST, Direction.SOUTH, Direction.NORTH, Direction.WEST);

    public BigBeetrootFeature(Codec<BigBeetrootFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<BigBeetrootFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos startPos = context.getOrigin();
        BigBeetrootFeatureConfig config = context.getConfig();
        Random random = context.getRandom();

        if (!world.getFluidState(startPos).isEmpty() || !world.getBlockState(startPos.down()).isSolidBlock(world, startPos.down())){
            return false;
        }

        if (BlockPos.stream(startPos, startPos.up(config.firstBaseHeight().get(random))).anyMatch(pos -> !replaceable(world.getBlockState(pos)) && !world.getBlockState(pos).isOf(BBlocks.BEETROOT_SPROUT))){
            return false;
        }

        placeRoots(world, startPos, random, config);
        BlockPos branchPos = startPos;
        BlockPos.stream(startPos.add(-1, -config.firstBaseHeight().get(random)/2, -1), startPos.add(1, config.firstBaseHeight().get(random)/2, 1)).forEach(pos -> {
            if (!world.getBlockState(pos.down()).isReplaceable() && random.nextBoolean()){
                setBlock(world, pos, config.baseBlock().get(random, pos));
            }
        });
        /*
            Place Base
         */
        for (int i = -2; i< config.firstBaseHeight().get(random) + config.secondBaseHeight().get(random); i++){
            if (setBlock(world, startPos.up(i), config.baseBlock().get(random, startPos.up(i)))){
                branchPos = branchPos.up();
            }
        }
        for (Direction direction : HORIZONTAL_DIRECTIONS) {
            for (int i = 0; i < config.secondBaseHeight().get(random); i++) {
                setBlock(world, startPos.offset(direction).up(i), config.baseBlock().get(random, startPos.offset(direction).up(i)));
            }
        }

        /*
            Place Branches
         */
        for (Direction direction : HORIZONTAL_DIRECTIONS) {
            int branchOffset = 1;
            int branchHeight = config.branchLength().get(random);
            int leafHeight = config.leafHeight().get(random);
            Direction faceDirection = direction.rotateYClockwise();

            for (int i = 0; i<branchHeight; i++){
                if (!setBlock(world, branchPos.offset(direction, branchOffset).up(i), config.baseBlock().get(random, branchPos.offset(direction, branchOffset).up(i)))) {
                    break;
                }
                else {
                    if (i > leafHeight){
                        BlockPos pos = branchPos.offset(direction, branchOffset).up(i);
                        setBlock(world, pos.offset(faceDirection, -1), config.leaves().get(random, pos));
                        setBlock(world, pos.offset(faceDirection, 1), config.leaves().get(random, pos));
                    }
                    if (random.nextBoolean() && i < branchHeight-1){
                        if (random.nextBoolean()){
                            setBlock(world, branchPos.offset(direction, branchOffset).up(i+1), config.sprouts().get(random, branchPos.offset(direction, branchOffset).up(i)));
                        }
                        setBlock(world, branchPos.offset(direction, branchOffset).up(i-1), config.hangingRoots().get(random, branchPos.offset(direction, branchOffset).up(i-1)));
                        branchOffset++;
                    }
                }
            }
            placeLeaf(world, branchPos.offset(direction, branchOffset).up(branchHeight-2), random, direction, config);
        }
        if (random.nextFloat() < config.heartChance().get(random)){
            world.setBlockState(startPos.up(), config.heart().get(random, startPos.up()), 3);
        }
        return true;
    }

    public void placeLeaf(StructureWorldAccess world, BlockPos startPos, Random random, Direction direction, BigBeetrootFeatureConfig config){
        int leafHeight = config.leafHeight().get(random);
        Direction faceDirection = direction.rotateYClockwise();

        Direction tiltDirection = random.nextBoolean() ? faceDirection.rotateYClockwise() : faceDirection.rotateYCounterclockwise();
        int tiltAmount = 0;

        Vec3i blobSize = Vec3i.ZERO;
        blobSize.offset(faceDirection, config.leafHeight().get(random));
        blobSize.offset(Direction.UP, random.nextBetween(2, 3));
        blobSize.offset(faceDirection.rotateYClockwise(), config.leafHeight().get(random));

        placeBlob(world, startPos, random, blobSize.getX(), blobSize.getY(), blobSize.getZ(), config.leaves(), BigBeetrootFeature::replaceable);
        for (int i = 0; i<leafHeight; i++){
            if (random.nextFloat() < 0.1f && i>leafHeight-1){
                tiltAmount++;
                BlockPos pos = startPos.up(i).offset(tiltDirection, tiltAmount);
                setBlock(world, pos, config.leaves().get(random, pos));
                setBlock(world, pos.offset(faceDirection, -1), config.leaves().get(random, pos));
                setBlock(world, pos.offset(faceDirection, 1), config.leaves().get(random, pos));
            }
            BlockPos pos = startPos.up(i).offset(tiltDirection, tiltAmount);
            setBlock(world, pos, config.leaves().get(random, pos));
            setBlock(world, pos.offset(faceDirection, -1), config.leaves().get(random, pos));
            setBlock(world, pos.offset(faceDirection, 1), config.leaves().get(random, pos));
        }
        BlockPos pos = startPos.offset(tiltDirection, tiltAmount).up(leafHeight);
        setBlock(world, pos, config.leaves().get(random, pos));
    }

    public void placeRoots(StructureWorldAccess world, BlockPos startPos, Random random, BigBeetrootFeatureConfig config){
        for(int i = 0; i < 3; ++i) {
            int sizeX = config.rootsRadius().get(random);
            int sizeY = config.rootsRadius().get(random);
            int sizeZ = config.rootsRadius().get(random);
            placeBlob(world, startPos, random, sizeX, sizeY, sizeZ, config.roots(), state -> state.isIn(BTags.Blocks.BIG_BEETROOTS_CAN_REPLACE));
            startPos = startPos.add(-1 + random.nextInt(sizeX), -random.nextInt(2), -1 + random.nextInt(2));
        }
    }

    public void placeBlob(StructureWorldAccess world, BlockPos startPos, Random random, int sizeX, int sizeY, int sizeZ, BlockStateProvider state, Function<BlockState, Boolean> stateFunction){
        float radius = (float)(sizeX + sizeY + sizeZ) * 0.333F + 0.5F;
        for (BlockPos currentPos : BlockPos.iterate(startPos.add(-sizeZ, -sizeY, -sizeZ), startPos.add(sizeZ, sizeY, sizeZ))) {
            if (currentPos.getSquaredDistance(startPos) <= (double) (radius * radius) && stateFunction.apply(world.getBlockState(currentPos))) {
                world.setBlockState(currentPos, state.get(random, currentPos), 3);
            }
        }
    }

    public static boolean replaceable(BlockState state){
        return state.isAir() || state.isIn(BlockTags.REPLACEABLE_BY_TREES) || state.isReplaceable();
    }
    public static boolean setBlock(StructureWorldAccess world, BlockPos pos, BlockState state){
        return placeBlock(world, pos, state, BigBeetrootFeature::replaceable);
    }
    public static boolean placeBlock(StructureWorldAccess world, BlockPos pos,BlockState state, Function<BlockState, Boolean> condition){
        if (condition.apply(world.getBlockState(pos))){
            world.setBlockState(pos,
                    state
                            .withIfExists(Properties.WATERLOGGED, world.isWater(pos))
                            .withIfExists(Properties.DISTANCE_0_7, 0)
                    , Block.NOTIFY_ALL);
            world.scheduleBlockTick(pos, state.getBlock(), 1);
            return true;
        }
        return false;
    }
}
