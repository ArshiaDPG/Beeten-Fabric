package net.digitalpear.beeten.client;

import net.digitalpear.beeten.init.BBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class BeetenClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
                BBlocks.BEETROOT_SPROUT,
                BBlocks.BEETROOT_LEAVES,
                BBlocks.BEET_ROOTS,
                BBlocks.HEART_BEETS
        );
    }
}
