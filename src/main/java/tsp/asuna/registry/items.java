package tsp.asuna.registry;

import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;
import tsp.asuna.Asuna;

import static net.minecraft.util.registry.Registry.register;
import static tsp.asuna.Asuna.ASUNA_SPELLS;


public class items {

    // Spells
    public static final Item MIASMA = register("miasma", new Item(new Item.Settings().group(Asuna.ASUNA_SPELLS)));












    public static Item register(String name, Item instance) {
        return Registry.register(Registry.ITEM, Asuna.id(name), instance);
    }














}
