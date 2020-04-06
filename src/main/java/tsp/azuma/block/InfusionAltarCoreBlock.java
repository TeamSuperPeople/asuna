package tsp.azuma.block;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tools.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import tsp.azuma.entity.InfusionAltarCoreBlockEntity;

public class InfusionAltarCoreBlock extends Block implements BlockEntityProvider {

    private static final VoxelShape SHAPE = Block.createCuboidShape(3, 0, 3, 13, 14, 13);

    public InfusionAltarCoreBlock() {
        super(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).hardness(1.2f).breakByHand(false).breakByTool(FabricToolTags.PICKAXES,2).build());
    }

    @Override
    public BlockEntity createBlockEntity(BlockView view) {
        return new InfusionAltarCoreBlockEntity();
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        assert world != null;

        if(hand == Hand.MAIN_HAND) {
            BlockEntity blockEntity = world.getBlockEntity(pos);

            if (blockEntity instanceof InfusionAltarCoreBlockEntity) {
                InfusionAltarCoreBlockEntity altarCoreBE = (InfusionAltarCoreBlockEntity) blockEntity;
                ActionResult result = altarCoreBE.onUse(state, world, pos, player, hand, hit);

                if(result != ActionResult.PASS) {
                    return result;
                }
            }
        }

        return super.onUse(state, world, pos, player, hand, hit);
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
