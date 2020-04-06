package tsp.azuma.registry;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.Item;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import tsp.azuma.Azuma;
import tsp.azuma.item.*;
import tsp.azuma.material.AzumaArmorMaterials;
import tsp.azuma.material.ManaDiamondToolMaterial;
import tsp.azuma.material.ManaIronToolMaterial;
import tsp.azuma.material.ToolMaterials;

import static tsp.azuma.Azuma.GROUP;


public class Items {

    // Spells
    public static final Item MIASMA = register("miasma", new MiasmaItem(new Item.Settings().group(Azuma.GROUP).maxCount(1)));
    public static final Item NOSFERATU = register("nosferatu", new LifeStealItem(new Item.Settings().group(Azuma.GROUP).maxCount(1)));
    public static final Item HEAL = register("heal", new HealItem(new Item.Settings().group(GROUP).maxCount(1)));
    public static final Item THORON = register("thoron", new ThoronItem(new Item.Settings().group(GROUP).maxCount(1)));
    public static final Item REGROWTH = register("regrowth", new RegrowthItem(new Item.Settings().group(GROUP).maxCount(1)));
    public static final Item THUNDER = register("thunder", new ThunderItem(new Item.Settings().group(GROUP).maxCount(1)));
    public static final Item BOLGANONE = register("bolganone", new BolganoneItem(new Item.Settings().group(GROUP).maxCount(1)));

    // swoards
    public static final Item THUNDERBAND =  register("thunderband", new ThunderbandItem(ToolMaterials.THUNDERGEM,2,2F,new Item.Settings().group(GROUP)));

    // cheating
    public static final Item MIASMA_EFFECT = register("miasma_effect", new Item(new Item.Settings()));
    public static final Item THORON_EFFECT = register("thoron_effect", new Item(new Item.Settings()));

    // tools
    public static final Item CRYSTAL_LINKER = register("crystal_linker", new CrystalLinkerItem(new Item.Settings().group(GROUP).maxCount(1).rarity(Rarity.UNCOMMON)));
    public static final Item ALL_SEEING_GLASSES = register("all_seeing_glasses", new ArmorItem(ArmorMaterials.IRON, EquipmentSlot.HEAD, new Item.Settings().group(GROUP).maxDamage(-1).maxCount(1)));


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

    public static final Item RUBY_PICKAXE = register("ruby_pickaxe", new RubyPickaxeItem(ToolMaterials.RUBY, -2,-2F,new Item.Settings().group(GROUP)));
    public static final Item RUBY_AXE = register("ruby_axe", new RubyAxeItem(ToolMaterials.RUBY, 5,-3F,new Item.Settings().group(GROUP)));
    public static final Item RUBY_SHOVEL = register("ruby_shovel", new RubyShovelItem(ToolMaterials.RUBY, -2,-2F,new Item.Settings().group(GROUP)));
    public static final Item RUBY_SWORD = register("ruby_sword", new RubySwordItem(ToolMaterials.RUBY, 3,-2.4F,new Item.Settings().group(GROUP)));

    // armor
    public static final Item AURORA_HELMET = register("aurora_helmet", new ManaArmorItem(AzumaArmorMaterials.AURORA, EquipmentSlot.HEAD));
    public static final Item AURORA_CHESTPLATE = register("aurora_chestplate", new ManaArmorItem(AzumaArmorMaterials.AURORA, EquipmentSlot.CHEST));
    public static final Item AURORA_LEGGINGS = register("aurora_leggings", new ManaArmorItem(AzumaArmorMaterials.AURORA, EquipmentSlot.LEGS));
    public static final Item AURORA_BOOTS = register("aurora_boots", new ManaArmorItem(AzumaArmorMaterials.AURORA, EquipmentSlot.FEET));

    // materials
    public static final Item MANA_IRON = register("manairon", new ColoredNameItem(new Item.Settings().group(GROUP), Formatting.AQUA));
    public static final Item AURORA_INGOT = register("aurora_ingot", new Item(new Item.Settings().group(GROUP)));
    public static final Item RUBY = register("ruby", new Item(new Item.Settings().group(GROUP)));
    public static final Item MANA_DIAMOND = register("manadiamond", new Item(new Item.Settings().group(GROUP)));
    public static final Item MANA_SHARD = register("mana_shard", new Item(new Item.Settings().rarity(Rarity.RARE).group(GROUP)));

    // elemental materials
    public static final Item BLAZING_CORE = register("blazing_core", new ColoredNameItem(new Item.Settings().group(GROUP), Formatting.RED));
    public static final Item THUNDERING_SPIRIT = register("thundering_spirit", new ColoredNameItem(new Item.Settings().group(GROUP), Formatting.YELLOW));
    public static final Item OCEANIC_PEARL = register("oceanic_pearl", new ColoredNameItem(new Item.Settings().group(GROUP), Formatting.BLUE));
    public static final Item TERRA_ORB = register("terra_orb", new ColoredNameItem(new Item.Settings().group(GROUP), Formatting.DARK_GREEN));

    // utilities
    public static final Item MANA_BOMB = register("mana_bomb", new ManaBombItem(4.0f));
    public static final Item REINFORCED_MANA_BOMB = register("reinforced_mana_bomb", new ManaBombItem(16.0f));
    public static final Item ULTIMATE_MANA_BOMB = register("ultimate_mana_bomb", new ManaBombItem(50.0f));
    public static final Item OVERFLOWING_GOBLET = register("overflowing_goblet", new OverflowingGobletItem());
    public static final Item MANA_PEARL = register("mana_pearl",new ManaPearlItem(new Item.Settings().group(GROUP).maxCount(1)));

    // pendants and accessories
    public static final Item MANA_PENDANT = register("mana_pendant", new ManaPendantItem());
    public static final Item RUBY_PENDANT = register("ruby_pendant", new RubyManaPendantItem());
    public static final Item MANARUBY_PENDANT = register("manaruby_pendant", new ManaRubyManaPendantItem());
    public static final Item MANADIAMONDRUBY_PENDANT = register("manadiamondruby_pendant", new ManaDiamondRubyManaPendantItem());

    public static Item register(String name, Item instance) {
        return Registry.register(Registry.ITEM, Azuma.id(name), instance);
    }

    public static void init() {

    }

    private Items() {

    }
}
