package net.digitalpear.beeten.common.block.compat;

import net.fabricmc.loader.api.FabricLoader;

import java.util.List;

public interface CompatRequired {

    default boolean hasRequiredMods(){
        if (requiredMods().isEmpty()){
            return true;
        }
        return requiredMods().stream().allMatch(id -> FabricLoader.getInstance().isModLoaded(id));
    }

    List<String> requiredMods();
}
