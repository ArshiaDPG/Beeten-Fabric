package net.digitalpear.beeten.common.datagen.worldgen;

import net.digitalpear.beeten.init.worldgen.BConfiguredFeatures;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import java.util.concurrent.CompletableFuture;

public class BConfiguredFeatureProvider extends FabricDynamicRegistryProvider {

    public BConfiguredFeatureProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        BConfiguredFeatures.features.forEach(configuredFeatureRegistryKey -> {
            add(registries, entries, configuredFeatureRegistryKey);
        });
    }
    private void add(RegistryWrapper.WrapperLookup registries, FabricDynamicRegistryProvider.Entries entries, RegistryKey<ConfiguredFeature<?, ?>> resourceKey) {
        RegistryWrapper.Impl<ConfiguredFeature<?, ?>> configuredFeatureRegistryLookup = registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE);

        entries.add(resourceKey, configuredFeatureRegistryLookup.getOrThrow(resourceKey).value());
    }
    @Override
    public String getName() {
        return "worldgen/configured_feature";
    }
}
