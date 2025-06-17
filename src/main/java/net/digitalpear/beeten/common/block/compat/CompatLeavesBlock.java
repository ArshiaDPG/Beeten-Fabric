package net.digitalpear.beeten.common.block.compat;

import net.digitalpear.beeten.common.block.BeetrootLeavesBlock;
import net.minecraft.resource.featuretoggle.FeatureSet;

import java.util.function.Supplier;

public class CompatLeavesBlock extends BeetrootLeavesBlock implements CompatRequired {
    private final Supplier<Boolean> hasRequiredMods;

    public CompatLeavesBlock(Supplier<Boolean> hasRequiredMods, Settings settings) {
        super(0.01f, settings);
        this.hasRequiredMods = hasRequiredMods;
    }

    @Override
    public boolean isEnabled(FeatureSet enabledFeatures) {
        return super.isEnabled(enabledFeatures) && hasRequiredMods();
    }

    @Override
    public boolean hasRequiredMods() {
        return false;
    }
}
