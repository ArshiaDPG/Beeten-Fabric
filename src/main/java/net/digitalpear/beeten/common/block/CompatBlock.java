package net.digitalpear.beeten.common.block;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.resource.featuretoggle.FeatureSet;

public class CompatBlock extends Block {
    private final String requiredModId;

    public CompatBlock(String requiredModId, Settings settings) {
        super(settings);
        this.requiredModId = requiredModId;
    }

    @Override
    public boolean isEnabled(FeatureSet enabledFeatures) {
        return super.isEnabled(enabledFeatures) && FabricLoader.getInstance().isModLoaded(requiredModId);
    }
}

