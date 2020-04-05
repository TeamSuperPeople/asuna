package tsp.asuna.block;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;

public class ManaCrystalBlock extends Block {

    public ManaCrystalBlock() {
        super(FabricBlockSettings.of(Material.STONE).nonOpaque().build());
    }
}
