package tsp.asuna.registry;

import net.minecraft.item.Item;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import tsp.asuna.Asuna;
import tsp.asuna.item.*;
import tsp.asuna.material.ManaDiamondToolMaterial;
import tsp.asuna.material.ManaIronToolMaterial;

import static tsp.asuna.Asuna.ASUNA_SPELLS;


public class Items {

    // Spells
    public static final Item MIASMA = register("miasma", new MiasmaItem(new Item.Settings().group(Asuna.ASUNA_SPELLS).maxCount(1)));
    public static final Item NOSFERATU = register("nosferatu", new LifeStealItem(new Item.Settings().group(Asuna.ASUNA_SPELLS).maxCount(1)));
    public static final Item HEAL = register("heal", new HealItem(new Item.Settings().group(ASUNA_SPELLS).maxCount(1)));
    public static final Item THORON = register("thoron", new ThoronItem(new Item.Settings().group(ASUNA_SPELLS).maxCount(1)));
    public static final Item REGROWTH = register("regrowth", new RegrowthItem(new Item.Settings().group(ASUNA_SPELLS).maxCount(1)));
    public static final Item THUNDER = register("thunder", new ThunderItem(new Item.Settings().group(ASUNA_SPELLS).maxCount(1)));
    public static final Item BOLGANONE = register("bolganone", new BolganoneItem(new Item.Settings().group(ASUNA_SPELLS).maxCount(1)));

    // swoards
    public static final Item THUNDERBAND =  register("thunderband", new ThunderbandItem(ToolMaterials.THUNDERGEM,2,2F,new Item.Settings().group(ASUNA_SPELLS)));

    // cheating
    public static final Item MIASMA_EFFECT = register("miasma_effect", new Item(new Item.Settings()));
    public static final Item THORON_EFFECT = register("thoron_effect", new Item(new Item.Settings()));


    // tools
    public static final Item CRYSTAL_LINKER = register("crystal_linker", new CrystalLinkerItem(new Item.Settings().group(ASUNA_SPELLS).maxCount(1).rarity(Rarity.UNCOMMON)));

    public static final Item MANAIRON_PICKAXE = register("manairon_pickaxe", new ManaPickaxeItem(new ManaIronToolMaterial(), 1, 256));
    public static final Item MANAIRON_SWORD = register("manairon_sword", new ManaSwordItem(new ManaIronToolMaterial(), 3, 256));
    public static final Item MANAIRON_AXE = register("manairon_axe", new ManaAxeItem(new ManaIronToolMaterial(), 6.0F, 256));
    public static final Item MANAIRON_SHOVEL = register("manairon_shovel", new ManaShovelItem(new ManaIronToolMaterial(), 1.5F, 256));
    public static final Item MANAIRON_HOE = register("manairon_hoe", new ManaHoeItem(new ManaIronToolMaterial(), 1, 256));

    public static final Item MANADIAMOND_PICKAXE = register("manadiamond_pickaxe", new ManaPickaxeItem(new ManaDiamondToolMaterial(), 1, 2048));
    public static final Item MANADIAMOND_SWORD = register("manadiamond_sword", new ManaSwordItem(new ManaDiamondToolMaterial(), 5, 2048));
    public static final Item MANADIAMOND_AXE = register("manadiamond_axe", new ManaAxeItem(new ManaDiamondToolMaterial(), 6.0F, 2048));
    public static final Item MANADIAMOND_SHOVEL = register("manadiamond_shovel", new ManaShovelItem(new ManaDiamondToolMaterial(), 1.5F, 2048));
    public static final Item MANADIAMOND_HOE = register("manadiamond_hoe", new ManaHoeItem(new ManaDiamondToolMaterial(), 1, 2048));

    public static final Item RUBY_PICKAXE = register("ruby_pickaxe", new RubyPickaxeItem(ToolMaterials.RUBY, -2,-2F,new Item.Settings().group(ASUNA_SPELLS)));
    public static final Item RUBY_AXE = register("ruby_axe", new RubyAxeItem(ToolMaterials.RUBY, 5,-3F,new Item.Settings().group(ASUNA_SPELLS)));
    public static final Item RUBY_SHOVEL = register("ruby_shovel", new RubyShovelItem(ToolMaterials.RUBY, -2,-2F,new Item.Settings().group(ASUNA_SPELLS)));
    public static final Item RUBY_SWORD = register("ruby_sword", new RubySwordItem(ToolMaterials.RUBY, 3,-2.4F,new Item.Settings().group(ASUNA_SPELLS)));

    // materials
    public static final Item MANA_IRON = register("manairon", new Item(new Item.Settings().group(ASUNA_SPELLS)));
    public static final Item AURORA_INGOT = register("aurora_ingot", new Item(new Item.Settings().group(ASUNA_SPELLS)));
    public static final Item RUBY = register("ruby", new Item(new Item.Settings().group(ASUNA_SPELLS)));
    public static final Item MANA_DIAMOND = register("manadiamond", new Item(new Item.Settings().group(ASUNA_SPELLS)));
    public static final Item MANA_SHARD = register("mana_shard", new Item(new Item.Settings().rarity(Rarity.RARE).group(ASUNA_SPELLS)));

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    // elemental materials
    public static final Item BLAZING_CORE = register("blazing_core", new Item(new Item.Settings().group(ASUNA_SPELLS)));
    public static final Item THUNDERING_SPIRIT = register("thundering_spirit", new Item(new Item.Settings().group(ASUNA_SPELLS)));
    public static final Item OCEANIC_PEARL = register("oceanic_pearl", new Item(new Item.Settings().group(ASUNA_SPELLS)));
    public static final Item TERRA_ORB = register("terra_orb", new Item(new Item.Settings().group(ASUNA_SPELLS)));

    // blocks


    public static Item register(String name, Item instance) {
        return Registry.register(Registry.ITEM, Asuna.id(name), instance);
    }

    public static void init() {

    }

    private Items() {

    }
}
