package net.digitalpear.beeten.client;

import net.digitalpear.beeten.init.BBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.render.BlockRenderLayer;

public class BeetenClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.putBlocks(BlockRenderLayer.CUTOUT,
                BBlocks.BEETROOT_SPROUT,
                BBlocks.SOULROOT_SPROUT,
                BBlocks.BEETROOT_LEAVES,
                BBlocks.SOULROOT_LEAVES,
                BBlocks.BEET_ROOTS,
                BBlocks.SOUL_ROOTS,
                BBlocks.HEART_BEETS
        );
    }
}
