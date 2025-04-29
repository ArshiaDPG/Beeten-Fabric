package net.digitalpear.beeten.init.data;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModCompat {

    public static final String FD_ID =  "farmersdelight";
    public static boolean isFDLoaded(){
        return FabricLoader.getInstance().isModLoaded(FD_ID);
    }

    public static RegistryKey<ItemGroup> farmersDelightItemGroup(){
        return RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of(FD_ID, FD_ID));
    }
}
