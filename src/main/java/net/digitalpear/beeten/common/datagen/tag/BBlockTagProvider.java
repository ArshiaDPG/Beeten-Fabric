package net.digitalpear.beeten.common.datagen.tag;

import net.digitalpear.beeten.init.BBlocks;
import net.digitalpear.beeten.init.BTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class BBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public BBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BTags.Blocks.BIG_BEETROOTS_CAN_REPLACE)
                .forceAddTag(BlockTags.AZALEA_ROOT_REPLACEABLE);

        getOrCreateTagBuilder(BTags.Blocks.CAN_CONVERT_T0_HEART_BEETROOTS)
                .add(Blocks.BEETROOTS)
                .addOptional(Identifier.of("farmersdelight", "wild_beetroots"))
        ;

        getOrCreateTagBuilder(BTags.Blocks.HEART_BEETS_PLACEABLE_ON)
                .forceAddTag(BTags.Blocks.BEETROOT_SPROUT_PLACEABLE_ON)
        ;

        getOrCreateTagBuilder(BTags.Blocks.BEETROOT_SPROUT_PLACEABLE_ON)
                .forceAddTag(BlockTags.DIRT)
                .forceAddTag(BlockTags.SAND)
                .add(Blocks.FARMLAND)
                .add(Blocks.CLAY)
                .add(
                        BBlocks.BEETROOT_HEART,
                        BBlocks.BEETROOT_BLOCK,
                        BBlocks.BEETROOT_TILES,
                        BBlocks.COOKED_BEETROOT_BLOCK,
                        BBlocks.COOKED_BEETROOT_TILES
                );

        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN).add(BBlocks.BEETROOT_BLOCK).add(BBlocks.COOKED_BEETROOT_BLOCK);
        getOrCreateTagBuilder(BlockTags.LEAVES).add(BBlocks.BEETROOT_LEAVES);

        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(
                BBlocks.BEETROOT_HEART,
                BBlocks.BEETROOT_BLOCK, BBlocks.BEETROOT_TILES,
                BBlocks.COOKED_BEETROOT_BLOCK, BBlocks.COOKED_BEETROOT_TILES
        );
        getOrCreateTagBuilder(BlockTags.HOE_MINEABLE).add(BBlocks.BEETROOT_LEAVES);

        getOrCreateTagBuilder(BlockTags.CRYSTAL_SOUND_BLOCKS).add(BBlocks.BEETROOT_HEART, BBlocks.HEART_BEETS);
        getOrCreateTagBuilder(BlockTags.VIBRATION_RESONATORS).add(BBlocks.BEETROOT_HEART, BBlocks.HEART_BEETS);
    }
}
