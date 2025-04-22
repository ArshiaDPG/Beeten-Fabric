package net.digitalpear.beeten;

import net.digitalpear.beeten.common.datagen.*;
import net.digitalpear.beeten.common.datagen.tag.BBiomeTagProvider;
import net.digitalpear.beeten.common.datagen.tag.BBlockTagProvider;
import net.digitalpear.beeten.common.datagen.tag.BItemTagProvider;
import net.digitalpear.beeten.common.datagen.worldgen.BConfiguredFeatureProvider;
import net.digitalpear.beeten.common.datagen.worldgen.BPlacedFeatureProvider;
import net.digitalpear.beeten.init.worldgen.BConfiguredFeatures;
import net.digitalpear.beeten.init.worldgen.BPlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class BeetenDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(BBlockTagProvider::new);
        pack.addProvider(BItemTagProvider::new);
        pack.addProvider(BBiomeTagProvider::new);

        pack.addProvider(BConfiguredFeatureProvider::new);
        pack.addProvider(BPlacedFeatureProvider::new);

        pack.addProvider(BModelProvider::new);
        pack.addProvider(BLanguageProvider::new);

        pack.addProvider(BBlockLootTableProvider::new);
        pack.addProvider(BRecipeProvider::new);

        pack.addProvider(BAdvancementProvider::new);

    }

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, BConfiguredFeatures::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, BPlacedFeatures::bootstrap);
    }
}
