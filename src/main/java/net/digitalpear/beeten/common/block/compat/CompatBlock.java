package net.digitalpear.beeten.common.block.compat;

import net.minecraft.block.Block;
import net.minecraft.resource.featuretoggle.FeatureSet;

import java.util.List;
import java.util.function.Supplier;

public class CompatBlock extends Block implements CompatRequired {
    private final Supplier<Boolean> hasRequiredMods;


    public CompatBlock(Supplier<Boolean> hasRequiredMods, Settings settings) {
        super(settings);
        this.hasRequiredMods = hasRequiredMods;
    }

    @Override
    public boolean isEnabled(FeatureSet enabledFeatures) {
        return super.isEnabled(enabledFeatures) && hasRequiredMods();
    }

    @Override
    public boolean hasRequiredMods() {
        return hasRequiredMods.get();
    }
}