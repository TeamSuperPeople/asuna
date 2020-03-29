package tsp.asuna.registry;

import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;
import tsp.asuna.Asuna;
import tsp.asuna.item.*;
import tsp.asuna.materials.ManaDiamondToolMaterial;
import tsp.asuna.materials.ManaGoldToolMaterial;
import tsp.asuna.materials.ManaIronToolMaterial;

import static tsp.asuna.Asuna.ASUNA_SPELLS;


public class Items {

    // Spells
    public static final Item MIASMA = register("miasma", new MiasmaItem(new Item.Settings().group(Asuna.ASUNA_SPELLS).maxCount(1)));
    public static final Item NOSFERATU = register("nosferatu", new LifeStealItem(new Item.Settings().group(Asuna.ASUNA_SPELLS).maxCount(1)));
    public static final Item HEAL = register("heal", new HealItem(new Item.Settings().group(ASUNA_SPELLS).maxCount(1)));

    // tools
    public static final Item MANAIRON_PICKAXE = register("manairon_pickaxe", new ManaPickaxeItem(new ManaIronToolMaterial(), 1, 256));
    public static final Item MANAIRON_SWORD = register("manairon_sword", new ManaSwordItem(new ManaIronToolMaterial(), 3, 256));
    public static final Item MANAIRON_AXE = register("manairon_axe", new ManaAxeItem(new ManaIronToolMaterial(), 6.0F, 256));
    public static final Item MANAIRON_SHOVEL = register("manairon_shovel", new ManaShovelItem(new ManaIronToolMaterial(), 1.5F, 256));
    public static final Item MANAIRON_HOE = register("manairon_hoe", new ManaHoeItem(new ManaIronToolMaterial(), 1, 256));

    public static final Item MANAGOLD_PICKAXE = register("managold_pickaxe", new ManaPickaxeItem(new ManaGoldToolMaterial(), 1, 64));
    public static final Item MANAGOLD_SWORD = register("managold_sword", new ManaSwordItem(new ManaGoldToolMaterial(), 3, 64));
    public static final Item MANAGOLD_AXE = register("managold_axe", new ManaAxeItem(new ManaGoldToolMaterial(), 6.0F, 64));
    public static final Item MANAGOLD_SHOVEL = register("managold_shovel", new ManaShovelItem(new ManaGoldToolMaterial(), 1.5F, 64));
    public static final Item MANAGOLD_HOE = register("managold_hoe", new ManaHoeItem(new ManaGoldToolMaterial(), 1, 64));

    public static final Item MANADIAMOND_PICKAXE = register("manadiamond_pickaxe", new ManaPickaxeItem(new ManaDiamondToolMaterial(), 1, 2048));
    public static final Item MANADIAMOND_SWORD = register("manadiamond_sword", new ManaSwordItem(new ManaDiamondToolMaterial(), 5, 2048));
    public static final Item MANADIAMOND_AXE = register("manadiamond_axe", new ManaAxeItem(new ManaDiamondToolMaterial(), 6.0F, 2048));
    public static final Item MANADIAMOND_SHOVEL = register("manadiamond_shovel", new ManaShovelItem(new ManaDiamondToolMaterial(), 1.5F, 2048));
    public static final Item MANADIAMOND_HOE = register("manadiamond_hoe", new ManaHoeItem(new ManaDiamondToolMaterial(), 1, 2048));


    // materials
    public static final Item MANA_IRON = register("manairon", new Item(new Item.Settings().group(ASUNA_SPELLS)));
    public static final Item MANA_GOLD = register("managold", new Item(new Item.Settings().group(ASUNA_SPELLS)));
    public static final Item MANA_DIAMOND = register("manadiamond", new Item(new Item.Settings().group(ASUNA_SPELLS)));

    public static Item register(String name, Item instance) {
        return Registry.register(Registry.ITEM, Asuna.id(name), instance);
    }

    public static void init() {

    }

    private Items() {

    }
}
