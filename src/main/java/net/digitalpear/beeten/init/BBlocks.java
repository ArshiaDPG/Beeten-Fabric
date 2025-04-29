package net.digitalpear.beeten.init;

import net.digitalpear.beeten.Beeten;
import net.digitalpear.beeten.common.block.BeetrootSproutBlock;
import net.digitalpear.beeten.common.block.CompatBlock;
import net.digitalpear.beeten.common.block.HearBeetsBlock;
import net.digitalpear.beeten.common.block.BeetrootHeartBlock;
import net.digitalpear.beeten.init.data.ModCompat;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class BBlocks {

    public static Map<Block, Block> BEETROOT_COOKING_MAP = new HashMap<>();
    
    private static Block register(String name, Block block){
        registerBlockItem(name, block);
        return registerWithoutItem(name, block);
    }
    private static Item registerBlockItem(String name, Block block){
        return Registry.register(Registries.ITEM, Beeten.id(name), new BlockItem(block, new Item.Settings()));

    }
    private static Block registerWithoutItem(String name, Block block){
        return Registry.register(Registries.BLOCK, Beeten.id(name), block);
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

    public static final Block BEETROOT_BLOCK = register("beetroot_block", new PillarBlock(beetrootSettings()));
    public static final Block BEETROOT_TILES = register("beetroot_tiles", new PillarBlock(beetrootSettings()));
    public static final Block BEET_ROOTS = register("beet_roots", new HangingRootsBlock(AbstractBlock.Settings.copy(Blocks.HANGING_ROOTS)));



    public static final Block HEART_BEETS = registerWithoutItem("heart_beets", new HearBeetsBlock(AbstractBlock.Settings.copy(Blocks.BEETROOTS)
            .mapColor(MapColor.PINK))
    );
    public static final Block BEETROOT_HEART = register("beetroot_heart", new BeetrootHeartBlock(BBlocks.HEART_BEETS.getDefaultState(), AbstractBlock.Settings.create()
            .sounds(BlockSoundGroup.AMETHYST_BLOCK)
            .ticksRandomly()
            .hardness(4)
            .mapColor(MapColor.PINK)
            .luminance(state -> 5))
    );

    public static final Block COOKED_BEETROOT_BLOCK = register("cooked_beetroot_block", new PillarBlock(cookedBeetrootSettings()));
    public static final Block COOKED_BEETROOT_TILES = register("cooked_beetroot_tiles", new PillarBlock(cookedBeetrootSettings()));


    public static final Block BEETROOT_LEAVES = register("beetroot_leaves", new LeavesBlock(AbstractBlock.Settings.copy(Blocks.MANGROVE_LEAVES).sounds(BlockSoundGroup.AZALEA_LEAVES)));
    public static final Block BEETROOT_SPROUT = register("beetroot_sprout", new BeetrootSproutBlock(AbstractBlock.Settings.copy(Blocks.AZALEA).mapColor(MapColor.EMERALD_GREEN)));

    public static final Block HEART_BEET_CRATE = register("heart_beet_crate", new CompatBlock(ModCompat.FD_ID, AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).strength(2, 3).luminance(s -> 5).sounds(BlockSoundGroup.WOOD)));

    public static void init() {
        BEETROOT_COOKING_MAP.put(BEETROOT_BLOCK, COOKED_BEETROOT_BLOCK);
        BEETROOT_COOKING_MAP.put(BEETROOT_TILES, COOKED_BEETROOT_TILES);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
            entries.addBefore(Items.OAK_LOG, BEETROOT_BLOCK, BEETROOT_HEART);
            entries.addBefore(Items.AZALEA_LEAVES, BEETROOT_LEAVES);
            entries.addBefore(Items.AZALEA, BEETROOT_SPROUT);
            entries.addAfter(Items.HANGING_ROOTS, BEET_ROOTS);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.addBefore(Items.STONE,
                    BEETROOT_BLOCK, BEETROOT_HEART, BEETROOT_TILES,
                    COOKED_BEETROOT_BLOCK, COOKED_BEETROOT_TILES
            );
        });

        if (ModCompat.isFDLoaded()){
            ItemGroupEvents.modifyEntriesEvent(ModCompat.farmersDelightItemGroup()).register(fabricItemGroupEntries -> {
                fabricItemGroupEntries.addAfter(Registries.BLOCK.get(Identifier.of(ModCompat.FD_ID, "beetroot_crate")), HEART_BEET_CRATE);
            });
        }
    }
}
