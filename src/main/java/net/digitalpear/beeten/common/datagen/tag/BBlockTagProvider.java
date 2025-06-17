package net.digitalpear.beeten.common.datagen.tag;

import net.digitalpear.beeten.init.BBlocks;
import net.digitalpear.beeten.init.BTags;
import net.digitalpear.beeten.init.data.ModCompat;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagEntry;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class BBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public BBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getTagBuilder(BTags.Blocks.BIG_BEETROOTS_CAN_REPLACE)
                .addOptionalTag(BlockTags.AZALEA_ROOT_REPLACEABLE.id());

        getTagBuilder(BTags.Blocks.CAN_CONVERT_T0_HEART_BEETROOTS)
                .add(getId(Blocks.BEETROOTS))
                .addOptional(Identifier.of(ModCompat.FD_ID, "wild_beetroots"))
                .addOptional(Identifier.of("bountifulfares", "wild_beetroots"))
        ;

        getTagBuilder(BTags.Blocks.HEART_BEETS_PLACEABLE_ON)
                .addOptionalTag(BlockTags.DIRT.id())
                .addOptionalTag(BlockTags.SAND.id())
                .add(getId(Blocks.FARMLAND))
                .add(getId(Blocks.CLAY));

        var tag = getTagBuilder(BTags.Blocks.BEETROOT_SPROUT_PLACEABLE_ON)
                .addOptionalTag(BTags.Blocks.HEART_BEETS_PLACEABLE_ON.id());
        for (Block block : List.of(BBlocks.BEETROOT_HEART,
                BBlocks.BEETROOT_BLOCK,
                BBlocks.BEETROOT_TILES,
                BBlocks.COOKED_BEETROOT_BLOCK,
                BBlocks.COOKED_BEETROOT_TILES,
                BBlocks.SOULROOT_BLOCK,
                BBlocks.SOULROOT_TILES)){
            tag.add(getId(block));
        }

        getTagBuilder(BTags.Blocks.SOULROOT_SPROUT_PLACEABLE_ON)
                .addOptionalTag(BTags.Blocks.BEETROOT_SPROUT_PLACEABLE_ON.id())
                .addOptionalTag(BlockTags.NYLIUM.id())
                .add(getId(Blocks.SOUL_SOIL))
                .add(getId(Blocks.SOUL_SAND));

        getTagBuilder(BlockTags.LOGS_THAT_BURN).add(getId(BBlocks.BEETROOT_BLOCK)).add(getId(BBlocks.COOKED_BEETROOT_BLOCK));
        getTagBuilder(BlockTags.LOGS).add(getId(BBlocks.SOULROOT_BLOCK));
        getTagBuilder(BlockTags.LEAVES).add(getId(BBlocks.BEETROOT_LEAVES)).add(getId(BBlocks.SOULROOT_LEAVES));



        var tag1 = getTagBuilder(BlockTags.AXE_MINEABLE);
        for (Block block : List.of(BBlocks.BEETROOT_HEART,
                BBlocks.BEETROOT_BLOCK, BBlocks.BEETROOT_TILES,
                BBlocks.COOKED_BEETROOT_BLOCK, BBlocks.COOKED_BEETROOT_TILES,
                BBlocks.HEART_BEET_CRATE, BBlocks.SOULROOT_BLOCK)){
            tag1.add(getId(block));
        }

        getTagBuilder(BlockTags.HOE_MINEABLE).add(getId(BBlocks.BEETROOT_LEAVES)).add(getId(BBlocks.SOULROOT_LEAVES));

        getTagBuilder(BlockTags.CRYSTAL_SOUND_BLOCKS).add(getId(BBlocks.BEETROOT_HEART)).add(getId(BBlocks.HEART_BEETS)).add(getId(BBlocks.HEART_BEET_CRATE));
        getTagBuilder(BlockTags.VIBRATION_RESONATORS).add(getId(BBlocks.BEETROOT_HEART)).add(getId(BBlocks.HEART_BEETS)).add(getId(BBlocks.HEART_BEET_CRATE));

        getTagBuilder(BlockTags.MAINTAINS_FARMLAND).add(getId(BBlocks.HEART_BEETS));

        getTagBuilder(BlockTags.ENDERMAN_HOLDABLE).add(getId(BBlocks.BEETROOT_SPROUT)).add(getId(BBlocks.SOULROOT_SPROUT));
    }

    public static TagEntry getId(Block block){
        return TagEntry.create(Registries.BLOCK.getId(block));
    }
}
