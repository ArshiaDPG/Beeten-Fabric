package net.digitalpear.beeten.common.block.compat;

import net.minecraft.block.Block;
import net.minecraft.resource.featuretoggle.FeatureSet;

import java.util.List;

public class CompatBlock extends Block implements CompatRequired {
    private final List<String> requiredModIds;

    public CompatBlock(String requiredModId, Settings settings) {
        this(List.of(requiredModId), settings);
    }
    public CompatBlock(List<String> requiredModIds, Settings settings) {
        super(settings);
        this.requiredModIds = requiredModIds;
    }

    @Override
    public boolean isEnabled(FeatureSet enabledFeatures) {
        return super.isEnabled(enabledFeatures) && hasRequiredMods();
    }

    @Override
    public List<String> requiredMods() {
        return requiredModIds;
    }
}