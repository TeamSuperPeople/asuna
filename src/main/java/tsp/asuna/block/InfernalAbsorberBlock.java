package tsp.asuna.block;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tools.FabricToolTags;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import tsp.asuna.entity.InfernalAbsorberBlockEntity;

public class InfernalAbsorberBlock extends FacingBlock implements BlockEntityProvider {

    public InfernalAbsorberBlock() {
        super(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).hardness(1.2f).breakByHand(false).breakByTool(FabricToolTags.PICKAXES,2).build());
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return (BlockState)this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite());
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack stack = player.getStackInHand(hand);

        if(stack.getItem().equals(Items.LAVA_BUCKET)) {
            InfernalAbsorberBlockEntity entity = getBlockEntity(world, pos);

            if(entity != null) {
                if(entity.canAcceptLava()) {
                    if(!world.isClient) {
                        entity.insertLava(100);
                    }

                    player.setStackInHand(hand, new ItemStack(Items.BUCKET));
                    return ActionResult.PASS;
                }
            }
        }

        return ActionResult.FAIL;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder.add(FACING));
    }

    public InfernalAbsorberBlockEntity getBlockEntity(World world, BlockPos pos) {
        BlockEntity blockEntity = world.getBlockEntity(pos);

        if (blockEntity instanceof InfernalAbsorberBlockEntity) {
            return  (InfernalAbsorberBlockEntity) blockEntity;
        }

        return null;
    }

    @Override
    public BlockEntity createBlockEntity(BlockView view) {
        return new InfernalAbsorberBlockEntity();
    }
}
