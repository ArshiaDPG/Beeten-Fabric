package net.digitalpear.beeten.common.block.compat;

import net.minecraft.block.LeavesBlock;
import net.minecraft.resource.featuretoggle.FeatureSet;

import java.util.List;

public class CompatLeavesBlock extends LeavesBlock implements CompatRequired {
    private final List<String> requiredModIds;

    public CompatLeavesBlock(String requiredModId, Settings settings) {
        this(List.of(requiredModId), settings);
    }
    public CompatLeavesBlock(List<String> requiredModIds, Settings settings) {
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
