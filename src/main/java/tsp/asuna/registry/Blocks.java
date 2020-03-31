package tsp.asuna.registry;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;
import tsp.asuna.Asuna;
import tsp.asuna.block.InfernalAbsorberBlock;
import tsp.asuna.block.InfernalYeeterBlock;
import tsp.asuna.block.ManaPylonBlock;
import tsp.asuna.block.ManaRelayBlock;

public class Blocks {

    public static final Block MANA_RELAY = register("mana_relay", new ManaRelayBlock(), new Item.Settings().group(Asuna.ASUNA_SPELLS));
    public static final Block MANA_PYLON = register("mana_pylon", new ManaPylonBlock(), new Item.Settings().group(Asuna.ASUNA_SPELLS));
    public static final Block INFERNAL_ABSORBER = register("infernal_absorber", new InfernalAbsorberBlock(), new Item.Settings().group(Asuna.ASUNA_SPELLS));
    public static final Block INFERNAL_YEETER = register("infernal_yeeter", new InfernalYeeterBlock(), new Item.Settings().group(Asuna.ASUNA_SPELLS));

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
