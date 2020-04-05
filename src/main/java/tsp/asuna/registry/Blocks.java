package tsp.asuna.registry;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tools.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import tsp.asuna.Asuna;
import tsp.asuna.block.*;

public class Blocks {

    public static final Block MANA_CRYSTAL = register("mana_crystal", new ManaCrystalBlock(), new Item.Settings().rarity(Rarity.RARE).group(Asuna.ASUNA_SPELLS));
    public static final Block MANA_RELAY = register("mana_relay", new ManaRelayBlock(), new Item.Settings().rarity(Rarity.RARE).group(Asuna.ASUNA_SPELLS));
    public static final Block MANA_PYLON = register("mana_pylon", new ManaPylonBlock(), new Item.Settings().rarity(Rarity.RARE).group(Asuna.ASUNA_SPELLS));
    public static final Block INFERNAL_ABSORBER = register("infernal_absorber", new InfernalAbsorberBlock(), new Item.Settings().group(Asuna.ASUNA_SPELLS));
    public static final Block INFERNAL_YEETER = register("infernal_yeeter", new InfernalYeeterBlock(), new Item.Settings().group(Asuna.ASUNA_SPELLS));
    public static final Block RUBY_ORE = register("ruby_ore", new Block(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).hardness(2.2f).breakByHand(false).breakByTool(FabricToolTags.PICKAXES).build()), new Item.Settings().group(Asuna.ASUNA_SPELLS));
    public static final Block INFUSION_ALTAR_CORE = register("infusion_altar_core", new InfusionAltarCoreBlock(), new Item.Settings().group(Asuna.ASUNA_SPELLS));
    public static final Block INFUSION_ALTAR_PEDESTAL = register("infusion_altar_pedestal", new InfusionAltarPedestalBlock(), new Item.Settings().group(Asuna.ASUNA_SPELLS));


    public static Block register(String name, Block block, Item.Settings settings) {
        block = Registry.register(Registry.BLOCK, Asuna.id(name), block);
        Registry.register(Registry.ITEM, Asuna.id(name), new BlockItem(block, settings));
        return block;
    }

    public static Block register(String name, Block block) {
        return Registry.register(Registry.BLOCK, Asuna.id(name), block);
    }

    public static void init() {
        // no-op
    }
}
