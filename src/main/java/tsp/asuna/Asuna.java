package tsp.asuna;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import tsp.asuna.registry.items;

public class Asuna implements ModInitializer {



    public static final ItemGroup ASUNA_SPELLS = FabricItemGroupBuilder.build(id("spells"), () -> new ItemStack(items.MIASMA));


    @Override
    public void onInitialize() {

    }
    public static Identifier id(String path) {
        return new Identifier(MODID, path);
    }
}
