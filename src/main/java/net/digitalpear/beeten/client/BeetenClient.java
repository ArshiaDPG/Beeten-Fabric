package net.digitalpear.beeten.client;

import net.digitalpear.beeten.init.BBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import org.jetbrains.annotations.Nullable;

public class BeetenClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
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
