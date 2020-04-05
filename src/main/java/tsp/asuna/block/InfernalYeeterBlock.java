package tsp.asuna.block;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tools.FabricToolTags;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import tsp.asuna.entity.InfernalYeeterBlockEntity;

public class InfernalYeeterBlock extends FacingBlock implements BlockEntityProvider {

    public InfernalYeeterBlock() {
        super(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).hardness(1.2f).breakByHand(false).breakByTool(FabricToolTags.PICKAXES,2).build());
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos neighborPos, boolean moved) {
        if(world == null) {
            return;
        }

        if(world.isReceivingRedstonePower(pos)) {
            BlockEntity entity = world.getBlockEntity(pos);

            if(world.getReceivedRedstonePower(pos) > 0) {
                if (entity instanceof InfernalYeeterBlockEntity) {
                    InfernalYeeterBlockEntity infernalYeeter = (InfernalYeeterBlockEntity) entity;
                    infernalYeeter.pulse(world.getReceivedRedstonePower(pos));
                }
            }
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        if(builder != null) {
            builder.add(FACING);
        }
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return (BlockState)this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite());
    }

    @Override
    public BlockEntity createBlockEntity(BlockView view) {
        return new InfernalYeeterBlockEntity();
    }
}
