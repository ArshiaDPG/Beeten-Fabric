package net.digitalpear.beeten.init;

import net.digitalpear.beeten.Beeten;
import net.digitalpear.beeten.common.block.BeetrootLeavesBlock;
import net.digitalpear.beeten.common.block.BeetrootSproutBlock;
import net.digitalpear.beeten.common.block.HearBeetsBlock;
import net.digitalpear.beeten.common.block.BeetrootHeartBlock;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class BBlocks {

    public static Map<Block, Block> BEETROOT_COOKING_MAP = new HashMap<>();
    
    private static Block register(String name, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings){
        Block block = registerWithoutItem(name, factory, settings);
        Items.register(block);
        return block;
    }
    private static Block registerWithoutItem(String name, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings){
        return Blocks.register(RegistryKey.of(RegistryKeys.BLOCK, Beeten.id(name)), factory, settings);
    }

    private static AbstractBlock.Settings beetrootSettings(){
        return AbstractBlock.Settings.create()
                .mapColor(MapColor.RED)
                .strength(1.5f)
                .sounds(BlockSoundGroup.CORAL);
    }

    private static AbstractBlock.Settings cookedBeetrootSettings(){
        return beetrootSettings().mapColor(MapColor.DARK_CRIMSON);
    }

    public static final Block BEETROOT_BLOCK = register("beetroot_block", PillarBlock::new, beetrootSettings());
    public static final Block BEETROOT_TILES = register("beetroot_tiles", PillarBlock::new, beetrootSettings());
    public static final Block BEET_ROOTS = register("beet_roots", HangingRootsBlock::new, AbstractBlock.Settings.copy(Blocks.HANGING_ROOTS));


    public static final Block BEETROOT_HEART = register("beetroot_heart", BeetrootHeartBlock::new, AbstractBlock.Settings.create()
            .sounds(BlockSoundGroup.AMETHYST_BLOCK)
            .ticksRandomly()
            .hardness(4)
            .mapColor(MapColor.PINK)
            .luminance(state -> 5)
    );
    public static final Block HEART_BEETS = registerWithoutItem("heart_beets", HearBeetsBlock::new,
            AbstractBlock.Settings.copy(Blocks.BEETROOTS)
                    .mapColor(MapColor.PINK)
    );


    public static final Block COOKED_BEETROOT_BLOCK = register("cooked_beetroot_block", PillarBlock::new, cookedBeetrootSettings());
    public static final Block COOKED_BEETROOT_TILES = register("cooked_beetroot_tiles", PillarBlock::new, cookedBeetrootSettings());


    public static final Block BEETROOT_LEAVES = register("beetroot_leaves", settings -> new BeetrootLeavesBlock(0.01f, settings),
            AbstractBlock.Settings.copy(Blocks.MANGROVE_LEAVES).sounds(BlockSoundGroup.LEAF_LITTER)
            );
    public static final Block BEETROOT_SPROUT = register("beetroot_sprout", BeetrootSproutBlock::new, AbstractBlock.Settings.copy(Blocks.AZALEA).mapColor(MapColor.EMERALD_GREEN));

    public static void init() {
        BEETROOT_COOKING_MAP.put(BEETROOT_BLOCK, COOKED_BEETROOT_BLOCK);
        BEETROOT_COOKING_MAP.put(BEETROOT_TILES, COOKED_BEETROOT_TILES);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
            entries.addBefore(Items.OAK_LOG, BEETROOT_BLOCK);
            entries.addBefore(Items.AZALEA_LEAVES, BEETROOT_LEAVES);
            entries.addBefore(Items.AZALEA, BEETROOT_SPROUT);
            entries.addAfter(Items.HANGING_ROOTS, BEET_ROOTS);
            entries.addAfter(Items.CREAKING_HEART, BEETROOT_HEART);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.addBefore(Items.STONE,
                    BEETROOT_BLOCK, BEETROOT_HEART, BEETROOT_TILES,
                    COOKED_BEETROOT_BLOCK, COOKED_BEETROOT_TILES
            );
        });
    }
}
