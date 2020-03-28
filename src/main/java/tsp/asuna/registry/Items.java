package tsp.asuna.registry;

import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;
import tsp.asuna.Asuna;
import tsp.asuna.item.MiasmaItem;
import tsp.asuna.item.ManaPickaxeItem;
import tsp.asuna.materials.ManaIronToolMaterial;

import static tsp.asuna.Asuna.ASUNA_SPELLS;


public class Items {

    // Spells
    public static final Item MIASMA = register("miasma", new MiasmaItem(new Item.Settings().group(Asuna.ASUNA_SPELLS)));
    public static final Item TOME_OF_LIFESTEAL = register("tome_of_lifesteal", new Item(new Item.Settings().group(Asuna.ASUNA_SPELLS)));

    // tools
    public static final Item MANAIRON_PICKAXE = register("manairon_pickaxe", new ManaPickaxeItem(new ManaIronToolMaterial(), 256));

    // materials
    public static final Item MANA_IRON = register("manairon", new Item(new Item.Settings().group(ASUNA_SPELLS)));

    public static Item register(String name, Item instance) {
        return Registry.register(Registry.ITEM, Asuna.id(name), instance);
    }

    public static void init() {

    }

    private Items() {

    }
}
