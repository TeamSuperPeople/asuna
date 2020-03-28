package tsp.asuna.registry;

import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;
import tsp.asuna.Asuna;
import tsp.asuna.item.*;
import tsp.asuna.materials.ManaIronToolMaterial;

import static tsp.asuna.Asuna.ASUNA_SPELLS;


public class Items {

    // Spells
    public static final Item MIASMA = register("miasma", new MiasmaItem(new Item.Settings().group(Asuna.ASUNA_SPELLS)));
    public static final Item TOME_OF_LIFESTEAL = register("tome_of_lifesteal", new LifeStealItem(new Item.Settings().group(Asuna.ASUNA_SPELLS)));
    public static final Item HEAL = register("heal", new HealItem(new Item.Settings().group(ASUNA_SPELLS)));

    // tools
    public static final Item MANAIRON_PICKAXE = register("manairon_pickaxe", new ManaPickaxeItem(new ManaIronToolMaterial(), 1, 256));
    public static final Item MANAIRON_SWORD = register("manairon_sword", new ManaSwordItem(new ManaIronToolMaterial(), 3, 256));
    public static final Item MANAIRON_AXE = register("manairon_axe", new ManaAxeItem(new ManaIronToolMaterial(), 6.0F, 256));
    public static final Item MANAIRON_SHOVEL = register("manairon_shovel", new ManaShovelItem(new ManaIronToolMaterial(), 1.5F, 256));
    public static final Item MANAIRON_HOE = register("manairon_hoe", new ManaHoeItem(new ManaIronToolMaterial(), 1, 256));

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
