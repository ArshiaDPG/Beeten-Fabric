package net.digitalpear.beeten.common.datagen.worldgen;

import net.digitalpear.beeten.init.worldgen.BPlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.gen.feature.PlacedFeature;

import java.util.concurrent.CompletableFuture;

public class BPlacedFeatureProvider extends FabricDynamicRegistryProvider {

    public BPlacedFeatureProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        BPlacedFeatures.placedFeatures.forEach(configuredFeatureRegistryKey -> {
            add(registries, entries, configuredFeatureRegistryKey);
        });
    }
    private void add(RegistryWrapper.WrapperLookup registries, Entries entries, RegistryKey<PlacedFeature> resourceKey) {
        RegistryWrapper.Impl<PlacedFeature> configuredFeatureRegistryLookup = registries.getOrThrow(RegistryKeys.PLACED_FEATURE);

        entries.add(resourceKey, configuredFeatureRegistryLookup.getOrThrow(resourceKey).value());
    }
    @Override
    public String getName() {
        return "worldgen/placed_feature";
    }
}
