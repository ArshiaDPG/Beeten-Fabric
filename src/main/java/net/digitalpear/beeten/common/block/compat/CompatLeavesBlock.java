package net.digitalpear.beeten.common.block.compat;

import net.digitalpear.beeten.common.block.BeetrootLeavesBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.resource.featuretoggle.FeatureSet;

import java.util.List;

public class CompatLeavesBlock extends BeetrootLeavesBlock implements CompatRequired {
    private final List<String> requiredModIds;

    public CompatLeavesBlock(String requiredModId, Settings settings) {
        this(List.of(requiredModId), settings);
    }
    public CompatLeavesBlock(List<String> requiredModIds, Settings settings) {
        super(0.01f, settings);
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
