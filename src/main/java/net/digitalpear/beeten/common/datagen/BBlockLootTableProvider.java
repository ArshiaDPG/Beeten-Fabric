package net.digitalpear.beeten.common.datagen;

import net.digitalpear.beeten.init.BBlocks;
import net.digitalpear.beeten.init.BItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.BeetrootsBlock;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.TableBonusLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.LimitCountLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.operator.BoundedIntUnaryOperator;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class BBlockLootTableProvider extends FabricBlockLootTableProvider {
    public BBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {

        RegistryWrapper.Impl<Enchantment> enchantmentImpl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);

        addDrop(BBlocks.BEETROOT_BLOCK);
        addDrop(BBlocks.BEETROOT_TILES);

        addDrop(BBlocks.COOKED_BEETROOT_BLOCK);
        addDrop(BBlocks.COOKED_BEETROOT_TILES);

        addDrop(BBlocks.BEETROOT_LEAVES, beetrootLeavesDrops(BBlocks.BEETROOT_LEAVES, BBlocks.BEETROOT_SPROUT, SAPLING_DROP_CHANCE));
        addDrop(BBlocks.BEETROOT_SPROUT);
        addDrop(BBlocks.BEET_ROOTS);

        addDrop(BBlocks.HEART_BEET_CRATE);

        LootCondition.Builder builder = BlockStatePropertyLootCondition.builder(BBlocks.HEART_BEETS).properties(StatePredicate.Builder.create().exactMatch(BeetrootsBlock.AGE, 3));
        addDrop(BBlocks.BEETROOT_HEART, (block) -> dropsWithSilkTouch(block, applyExplosionDecay(block, ItemEntry.builder(BItems.HEART_BEET).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))).apply(ApplyBonusLootFunction.uniformBonusCount(enchantmentImpl.getOrThrow(Enchantments.FORTUNE))).apply(LimitCountLootFunction.builder(BoundedIntUnaryOperator.createMax(9))))));
        addDrop(BBlocks.HEART_BEETS, cropDrops(BBlocks.HEART_BEETS, BItems.HEART_BEET, Items.BEETROOT_SEEDS, builder));
    }

    public LootTable.Builder beetrootLeavesDrops(Block leaves, Block sapling, float... saplingChance) {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        return this.leavesDrops(leaves, sapling, saplingChance).pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F)).conditionally(this.createWithoutShearsOrSilkTouchCondition()).with((this.addSurvivesExplosionCondition(leaves, ItemEntry.builder(Items.BEETROOT))).conditionally(TableBonusLootCondition.builder(impl.getOrThrow(Enchantments.FORTUNE), 0.005F, 0.0055555557F, 0.00625F, 0.008333334F, 0.025F))));
    }
}
