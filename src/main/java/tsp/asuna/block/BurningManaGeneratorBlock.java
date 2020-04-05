package tsp.asuna.block;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import tsp.asuna.entity.BurningManaGeneratorBlockEntity;

public class BurningManaGeneratorBlock extends AbstractFurnaceBlock implements BlockEntityProvider {

    public BurningManaGeneratorBlock() {
        super(FabricBlockSettings.of(Material.STONE).build());
    }

    @Override
    public BlockEntity createBlockEntity(BlockView view) {
        return new BurningManaGeneratorBlockEntity();
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        assert player != null;

        BurningManaGeneratorBlockEntity entity = getBlockEntity(world, pos);

        if (entity != null && hand == Hand.MAIN_HAND) {
            return entity.onUse(state, world, pos, player, hand, hit);
        }

        return ActionResult.FAIL;
    }

    @Override
    protected void openContainer(World world, BlockPos pos, PlayerEntity player) {

    }

    public BurningManaGeneratorBlockEntity getBlockEntity(World world, BlockPos pos) {
        BlockEntity blockEntity = world.getBlockEntity(pos);

        if (blockEntity instanceof BurningManaGeneratorBlockEntity) {
            return  (BurningManaGeneratorBlockEntity) blockEntity;
        }

        return null;
    }
}
