package tsp.asuna;

import nerdhub.cardinal.components.api.event.ItemComponentCallback;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.Registry;
import tsp.asuna.api.ManaDurable;
import tsp.asuna.api.cca.ItemManaComponent;
import tsp.asuna.registry.*;
import tsp.asuna.world.WorldSetup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Asuna implements ModInitializer {

    public static final ItemGroup ASUNA_SPELLS = FabricItemGroupBuilder.build(id("spells"), () -> new ItemStack(Items.MIASMA));
    public static final String MODID = "asuna";
    public static final Map<ChunkPos, ArrayList<BlockPos>> caveAirList = new HashMap<>();

    @Override
    public void onInitialize() {
        Registry.ITEM.forEach(item -> {
            if (item instanceof ManaDurable) {
                ItemComponentCallback.event(item).register((stack, components) -> components.put(Components.MANA, new ItemManaComponent(
                        ((ManaDurable) item).getMaxMana(),
                        ((ManaDurable) item).getStartingMana()
                )));
            }
        });

        RegistryEntryAddedCallback.event(Registry.ITEM).register((i, identifier, item) -> {
            if (item instanceof ManaDurable) {
                ItemComponentCallback.event(item).register((stack, components) -> components.put(Components.MANA, new ItemManaComponent(
                        ((ManaDurable) item).getMaxMana(),
                        ((ManaDurable) item).getStartingMana()
                )));
            }
        });

        Components.init();
        Items.init();
        Blocks.init();
        Recipes.init();
        WorldSetup.setup();
        World.init();
    }

    public static Identifier id(String path) {
        return new Identifier(MODID, path);
    }
}
