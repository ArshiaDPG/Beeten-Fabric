package net.digitalpear.beeten.init.data;

import net.digitalpear.beeten.common.block.BeetrootSproutBlock;
import net.digitalpear.beeten.common.block.compat.CompatLeavesBlock;
import net.digitalpear.beeten.common.block.compat.CompatPillarBlock;
import net.digitalpear.beeten.init.BTags;
import net.digitalpear.beeten.init.worldgen.BConfiguredFeatures;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.*;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class BeetrootFamily {

    private final Block block;
    private final Block tiles;
    private final Block leaves;
    private final Block sprout;
    private final Block roots;
    private final Settings settingsBuilder;

    public BeetrootFamily(Identifier name, Settings builder){
        this.settingsBuilder = builder;
        this.block = register(name.withSuffixedPath("_block"), settings -> new CompatPillarBlock(settingsBuilder.registerConditions(), settings), builder.baseSettings.mapColor(builder.mapColor));
        this.tiles = register(name.withSuffixedPath("_tiles"), settings -> new CompatPillarBlock(settingsBuilder.registerConditions(), settings), builder.baseSettings.mapColor(builder.mapColor));
        this.leaves = register(name.withSuffixedPath("_leaves"), settings -> new CompatLeavesBlock(settingsBuilder.registerConditions(), settings), AbstractBlock.Settings.copy(Blocks.MANGROVE_LEAVES).sounds(BlockSoundGroup.LEAF_LITTER).mapColor(builder.leavesMapColor));
        this.sprout = register(name.withSuffixedPath("_sprout"), settings -> new BeetrootSproutBlock(settingsBuilder.sproutSupportingBlocks, settingsBuilder.requiredMods, settingsBuilder.sproutFeature, settings), builder.baseSettings.mapColor(builder.leavesMapColor));
        this.roots = register(name.withSuffixedPath("_sprout"), HangingRootsBlock::new, AbstractBlock.Settings.copy(Blocks.HANGING_ROOTS));
    }

    public Block getBlock() {
        return block;
    }

    public Block getTiles() {
        return tiles;
    }

    public Block getLeaves() {
        return leaves;
    }

    public Block getSprout() {
        return sprout;
    }

    public Block getRoots() {
        return roots;
    }
    private Block register(Identifier name, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings){
        Block block = registerWithoutItem(name, factory, settings);
        Items.register(block);
        return block;
    }
    private Block registerWithoutItem(Identifier name, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings){
        return Blocks.register(RegistryKey.of(RegistryKeys.BLOCK, name), factory, settings);
    }


    public static class Settings {
        private MapColor mapColor = MapColor.RED;
        private MapColor leavesMapColor = MapColor.EMERALD_GREEN;
        private RegistryKey<ConfiguredFeature<?, ?>> sproutFeature = BConfiguredFeatures.BIG_BEETROOT_GROWN;
        private List<String> requiredMods = List.of();
        private AbstractBlock.Settings baseSettings = AbstractBlock.Settings.create()
                .mapColor(MapColor.RED)
                .strength(1.5f)
                .sounds(BlockSoundGroup.CORAL);
        private TagKey<Block> sproutSupportingBlocks = BTags.Blocks.BEETROOT_SPROUT_PLACEABLE_ON;

        public Settings setMapColor(MapColor newColor){
            mapColor = newColor;
            return this;
        }
        public Settings setLeavesMapColor(MapColor newColor){
            leavesMapColor = newColor;
            return this;
        }
        public Settings setSproutFeature(RegistryKey<ConfiguredFeature<?, ?>> feature){
            sproutFeature = feature;
            return this;
        }
        public Settings setCondition(List<String> modsNeeded){
            this.requiredMods = modsNeeded;
            return this;
        }
        public Settings setBaseSettings(AbstractBlock.Settings settings){
            this.baseSettings = settings;
            return this;
        }
        public Settings setSproutSupporting(TagKey<Block> settings){
            this.sproutSupportingBlocks = settings;
            return this;
        }
        public Supplier<Boolean> registerConditions(){
            return () -> requiredMods.isEmpty() || requiredMods.stream().anyMatch(id -> FabricLoader.getInstance().isModLoaded(id));
        }
    }
}
