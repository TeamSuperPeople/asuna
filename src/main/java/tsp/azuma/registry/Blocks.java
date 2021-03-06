package tsp.azuma.registry;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tools.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import tsp.azuma.Azuma;
import tsp.azuma.block.*;

public class Blocks {

    public static final Block MANA_CRYSTAL = register("mana_crystal", new ManaCrystalBlock(), new Item.Settings().rarity(Rarity.RARE).group(Azuma.GROUP));
    public static final Block MANA_RELAY = register("mana_relay", new ManaRelayBlock(), new Item.Settings().rarity(Rarity.RARE).group(Azuma.GROUP));
    public static final Block MANA_PYLON = register("mana_pylon", new ManaPylonBlock(), new Item.Settings().rarity(Rarity.RARE).group(Azuma.GROUP));
    public static final Block INFERNAL_ABSORBER = register("infernal_absorber", new InfernalAbsorberBlock(), new Item.Settings().group(Azuma.GROUP));
    public static final Block INFERNAL_YEETER = register("infernal_yeeter", new InfernalYeeterBlock(), new Item.Settings().group(Azuma.GROUP));
    public static final Block RUBY_ORE = register("ruby_ore", new Block(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).hardness(2.2f).breakByHand(false).breakByTool(FabricToolTags.PICKAXES,3).build()), new Item.Settings().group(Azuma.GROUP));
    public static final Block INFUSION_ALTAR_CORE = register("infusion_altar_core", new InfusionAltarCoreBlock(), new Item.Settings().group(Azuma.GROUP));
    public static final Block INFUSION_ALTAR_PEDESTAL = register("infusion_altar_pedestal", new InfusionAltarPedestalBlock(), new Item.Settings().group(Azuma.GROUP));
    public static final Block BURNING_MANA_GENERATOR = register("burning_mana_generator", new BurningManaGeneratorBlock(), new Item.Settings().group(Azuma.GROUP));
    public static final Block AERIAL_ACCELERATOR = register("aerial_accelerator", new AerialAcceleratorBlock(), new Item.Settings().group(Azuma.GROUP));


    public static Block register(String name, Block block, Item.Settings settings) {
        block = Registry.register(Registry.BLOCK, Azuma.id(name), block);
        Registry.register(Registry.ITEM, Azuma.id(name), new BlockItem(block, settings));
        return block;
    }

    public static Block register(String name, Block block) {
        return Registry.register(Registry.BLOCK, Azuma.id(name), block);
    }

    public static void init() {
        // no-op
    }
}
