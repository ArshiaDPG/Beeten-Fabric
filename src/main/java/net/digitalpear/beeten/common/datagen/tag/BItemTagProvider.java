package net.digitalpear.beeten.common.datagen.tag;

import net.digitalpear.beeten.init.BBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.item.ItemConvertible;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagEntry;

import java.util.concurrent.CompletableFuture;

public class BItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public BItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture, new BBlockTagProvider(output, completableFuture));
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getTagBuilder(ItemTags.LOGS_THAT_BURN)
                .add(getId(BBlocks.BEETROOT_BLOCK.asItem()))
                .add(getId(BBlocks.COOKED_BEETROOT_BLOCK.asItem()))
        ;
        getTagBuilder(ItemTags.LOGS).add(getId(BBlocks.SOULROOT_BLOCK.asItem()));
        getTagBuilder(ItemTags.LEAVES).add(getId(BBlocks.BEETROOT_LEAVES.asItem())).add(getId(BBlocks.SOULROOT_LEAVES.asItem()));
    }
    public static TagEntry getId(ItemConvertible block){
        return TagEntry.create(Registries.ITEM.getId(block.asItem()));
    }
}
