package tsp.azuma.block;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tools.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.entity.EntityContext;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class ManaCrystalBlock extends Block {

    private static final VoxelShape SHAPE = Block.createCuboidShape(2, 0, 2, 14, 10, 14);

    public ManaCrystalBlock() {
        super(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.GLASS).nonOpaque().hardness(3.0f).breakByTool(FabricToolTags.PICKAXES, 1).build());
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, EntityContext context) {
        return SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView view, BlockPos pos, EntityContext context) {
        return SHAPE;
    }
}