package net.digitalpear.beeten;

import net.digitalpear.beeten.init.*;
import net.digitalpear.beeten.init.data.BData;
import net.digitalpear.beeten.init.worldgen.BFeature;
import net.digitalpear.beeten.init.worldgen.BPlacedFeatures;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class Beeten implements ModInitializer {

    public static final String MOD_ID = "beeten";

    public static Identifier id(String name){
        return Identifier.of(MOD_ID, name);
    }

    @Override
    public void onInitialize() {
        BBlocks.init();
        BItems.init();
        BFeature.init();
        BPlacedFeatures.init();
        BData.init();
        BGameRules.init();


        ServerEntityEvents.ENTITY_LOAD.register((entity, serverWorld) -> {
            if (entity.getType() == EntityType.LIGHTNING_BOLT){
                BlockPos floorPos = entity.getBlockPos().down();
                BlockState floorState = serverWorld.getBlockState(floorPos);
                if (BBlocks.BEETROOT_COOKING_MAP.containsKey(floorState.getBlock())){
                    serverWorld.setBlockState(floorPos, BBlocks.BEETROOT_COOKING_MAP.get(floorState.getBlock()).getStateWithProperties(floorState), 3);
                }
            }
        });
    }
}
