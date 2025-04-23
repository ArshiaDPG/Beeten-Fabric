package net.digitalpear.beeten.init;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
import net.minecraft.block.Blocks;
import net.minecraft.village.TradeOffers;

public class BData {

    public static void registerFlammables(){
        FlammableBlockRegistry registry = FlammableBlockRegistry.getDefaultInstance();
        registry.add(BBlocks.HEART_BEETS, registry.get(Blocks.BEETROOTS).getBurnChance(), registry.get(Blocks.BEETROOTS).getSpreadChance());
        registry.add(BBlocks.BEETROOT_BLOCK, 5, 5);
        registry.add(BBlocks.BEETROOT_TILES, 5, 5);
        registry.add(BBlocks.COOKED_BEETROOT_BLOCK, 5, 5);
        registry.add(BBlocks.COOKED_BEETROOT_TILES, 5, 5);
        registry.add(BBlocks.BEETROOT_LEAVES, 30, 60);
        registry.add(BBlocks.BEETROOT_SPROUT, 30, 60);
    }
    public static void registerComposting(){
        CompostingChanceRegistry registry = CompostingChanceRegistry.INSTANCE;
        registry.add(BBlocks.BEET_ROOTS, 0.3F);
        registry.add(BBlocks.BEETROOT_LEAVES, 0.5F);
        registry.add(BBlocks.BEETROOT_SPROUT, 0.65F);
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
    }
}
