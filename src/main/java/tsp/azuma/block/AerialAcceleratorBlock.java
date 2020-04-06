package tsp.azuma.block;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.world.BlockView;
import tsp.azuma.entity.AerialAcceleratorBlockEntity;

public class AerialAcceleratorBlock extends FacingBlock implements BlockEntityProvider {

    public AerialAcceleratorBlock() {
        super(FabricBlockSettings.of(Material.WOOL).build());
    }

    @Override
    public BlockEntity createBlockEntity(BlockView view) {
        return new AerialAcceleratorBlockEntity();
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        assert builder != null;
        builder.add(FACING);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite());
    }
}
