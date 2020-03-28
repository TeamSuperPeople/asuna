package tsp.asuna;

import nerdhub.cardinal.components.api.event.ItemComponentCallback;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import tsp.asuna.api.ItemManaComponent;
import tsp.asuna.api.ManaCharged;
import tsp.asuna.registry.Components;
import tsp.asuna.registry.Items;

public class Asuna implements ModInitializer {

    public static final ItemGroup ASUNA_SPELLS = FabricItemGroupBuilder.build(id("spells"), () -> new ItemStack(Items.MIASMA));
    public static final String MODID = "asuna";

    @Override
    public void onInitialize() {
        RegistryEntryAddedCallback.event(Registry.ITEM).register((i, identifier, item) -> {
            if(item instanceof ManaCharged) {
                ItemComponentCallback.event(item).register((stack, components) -> components.put(Components.MANA, new ItemManaComponent(((ManaCharged) item).getMaxMana())));
            }
        });

        Components.init();
        Items.init();
    }

    public static Identifier id(String path) {
        return new Identifier(MODID, path);
    }
}
