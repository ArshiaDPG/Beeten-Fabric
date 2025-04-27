package net.digitalpear.beeten.common.datagen.tag;

import net.digitalpear.beeten.init.BBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class BItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public BItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture, new BBlockTagProvider(output, completableFuture));
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN).add(
                BBlocks.BEETROOT_BLOCK.asItem(),
                BBlocks.COOKED_BEETROOT_BLOCK.asItem()
        );
        getOrCreateTagBuilder(ItemTags.LEAVES).add(BBlocks.BEETROOT_LEAVES.asItem());
    }
}
