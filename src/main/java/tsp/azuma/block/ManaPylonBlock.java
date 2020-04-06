package tsp.azuma.block;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tools.FabricToolTags;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.BlockView;
import tsp.azuma.entity.ManaPylonBlockEntity;

public class ManaPylonBlock extends AbstractGlassBlock implements BlockEntityProvider {

    public ManaPylonBlock() {
        super(FabricBlockSettings.of(Material.STONE).nonOpaque().sounds(BlockSoundGroup.STONE).hardness(1.2f).breakByHand(false).breakByTool(FabricToolTags.PICKAXES,2).build());
    }

    @Override
    public BlockEntity createBlockEntity(BlockView view) {
        return new ManaPylonBlockEntity();
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.INVISIBLE;
    }
}
