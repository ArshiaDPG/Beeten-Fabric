package net.digitalpear.beeten.common.block.compat;

import net.minecraft.block.PillarBlock;
import net.minecraft.resource.featuretoggle.FeatureSet;

import java.util.List;

public class CompatPillarBlock extends PillarBlock implements CompatRequired {
    private final List<String> requiredModIds;

    public CompatPillarBlock(String requiredModId, Settings settings) {
        this(List.of(requiredModId), settings);
    }
    public CompatPillarBlock(List<String> requiredModIds, Settings settings) {
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
