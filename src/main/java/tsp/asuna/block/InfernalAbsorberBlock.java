package tsp.asuna.block;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import tsp.asuna.entity.InfernalAbsorberBlockEntity;

public class InfernalAbsorberBlock extends Block implements BlockEntityProvider {

    public InfernalAbsorberBlock() {
        super(FabricBlockSettings.of(Material.STONE).build());
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
