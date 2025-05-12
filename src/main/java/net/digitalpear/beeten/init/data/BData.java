package net.digitalpear.beeten.init.data;

import net.digitalpear.beeten.init.BBlocks;
import net.digitalpear.beeten.init.BItems;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
import net.minecraft.block.Blocks;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.village.TradeOffers;

public class BData {

    public static void registerFlammables(){
        FlammableBlockRegistry registry = FlammableBlockRegistry.getDefaultInstance();
        registry.add(BBlocks.HEART_BEETS, registry.get(Blocks.BEETROOTS).getBurnChance(), registry.get(Blocks.BEETROOTS).getSpreadChance());
        registry.add(BBlocks.BEETROOT_BLOCK, 5, 5);
        registry.add(BBlocks.BEETROOT_TILES, 5, 5);
        registry.add(BBlocks.COOKED_BEETROOT_BLOCK, 5, 10);
        registry.add(BBlocks.COOKED_BEETROOT_TILES, 5, 10);
        registry.add(BBlocks.BEETROOT_LEAVES, 30, 60);
        registry.add(BBlocks.BEETROOT_SPROUT, 30, 60);
        registry.add(BBlocks.BEET_ROOTS, 30, 60);
    }
    public static void registerComposting(){
        CompostingChanceRegistry registry = CompostingChanceRegistry.INSTANCE;
        registry.add(BBlocks.BEET_ROOTS, 0.3F);
        registry.add(BBlocks.SOUL_ROOTS, 0.3F);
        registry.add(BBlocks.BEETROOT_LEAVES, 0.5F);
        registry.add(BBlocks.SOULROOT_LEAVES, 0.5F);
        registry.add(BBlocks.BEETROOT_SPROUT, 0.65F);
        registry.add(BBlocks.SOULROOT_SPROUT, 0.65F);
        registry.add(BItems.HEART_BEET, 0.65F);
    }

    public static void init(){
        registerFlammables();
        registerComposting();

        TradeOfferHelper.registerWanderingTraderOffers(wanderingTraderOffersBuilder -> {
            wanderingTraderOffersBuilder.addOffersToPool(TradeOfferHelper.WanderingTraderOffersBuilder.SELL_COMMON_ITEMS_POOL,
                    new TradeOffers.SellItemFactory(
                            BBlocks.BEETROOT_SPROUT,
                            3,
                            1,
                            5,
                            4
                    )
            );
        });
        LootTableEvents.MODIFY.register(Event.DEFAULT_PHASE, (registryKey, builder, lootTableSource, wrapperLookup) -> {
            if (registryKey.getValue().equals(LootTables.PIGLIN_BARTERING_GAMEPLAY.getValue())){
                builder.modifyPools(builder1 -> builder1.with(ItemEntry.builder(BBlocks.SOULROOT_SPROUT).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 3))).weight(5)));
            }
        });
    }
}
