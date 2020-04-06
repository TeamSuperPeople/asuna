package tsp.azuma.item;

import net.minecraft.advancement.criterion.Criterions;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidFillable;
import net.minecraft.block.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.IWorld;
import net.minecraft.world.RayTraceContext;
import net.minecraft.world.World;
import tsp.azuma.Azuma;

public class OverflowingGobletItem extends Item {

    public OverflowingGobletItem() {
        super(new Item.Settings().group(Azuma.GROUP));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        HitResult hitResult = rayTrace(world, user, RayTraceContext.FluidHandling.NONE);

        if (hitResult.getType() == HitResult.Type.MISS) {
            return TypedActionResult.pass(itemStack);
        } else if (hitResult.getType() != HitResult.Type.BLOCK) {
            return TypedActionResult.pass(itemStack);
        } else {
            BlockHitResult blockHitResult = (BlockHitResult) hitResult;
            BlockPos blockPos = blockHitResult.getBlockPos();
            Direction direction = blockHitResult.getSide();
            BlockPos offsetPosition = blockPos.offset(direction);

            if (world.canPlayerModifyAt(user, blockPos) && user.canPlaceOn(offsetPosition, direction, itemStack)) {
                BlockState blockState;
                blockState = world.getBlockState(blockPos);
                BlockPos blockPos3 = blockState.getBlock() instanceof FluidFillable ? blockPos : offsetPosition;

                if (this.placeFluid(user, world, blockPos3, blockHitResult)) {
                    if (user instanceof ServerPlayerEntity) {
                        Criterions.PLACED_BLOCK.trigger((ServerPlayerEntity) user, blockPos3, itemStack);
                    }

                    user.incrementStat(Stats.USED.getOrCreateStat(this));
                    return TypedActionResult.success(itemStack);
                } else {
                    return TypedActionResult.fail(itemStack);
                }

            } else {
                return TypedActionResult.fail(itemStack);
            }
        }
    }

    public boolean placeFluid(PlayerEntity player, World world, BlockPos pos, BlockHitResult hitResult) {
        BlockState blockState = world.getBlockState(pos);
        Material material = blockState.getMaterial();

        boolean bl = blockState.canBucketPlace(Fluids.WATER);
        if (!blockState.isAir() && !bl && (!(blockState.getBlock() instanceof FluidFillable) || !((FluidFillable) blockState.getBlock()).canFillWithFluid(world, pos, blockState, Fluids.WATER))) {
            return hitResult != null && this.placeFluid(player, world, hitResult.getBlockPos().offset(hitResult.getSide()), null);
        } else {
            if (world.dimension.doesWaterVaporize()) {
                int i = pos.getX();
                int j = pos.getY();
                int k = pos.getZ();
                world.playSound(player, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (world.random.nextFloat() - world.random.nextFloat()) * 0.8F);

                for (int l = 0; l < 8; ++l) {
                    world.addParticle(ParticleTypes.LARGE_SMOKE, (double) i + Math.random(), (double) j + Math.random(), (double) k + Math.random(), 0.0D, 0.0D, 0.0D);
                }
            } else if (blockState.getBlock() instanceof FluidFillable) {
                if (((FluidFillable) blockState.getBlock()).tryFillWithFluid(world, pos, blockState, Fluids.WATER.getStill(false))) {
                    this.playEmptyingSound(player, world, pos);
                }
            } else {
                if (!world.isClient && bl && !material.isLiquid()) {
                    world.breakBlock(pos, true);
                }

                this.playEmptyingSound(player, world, pos);
                world.setBlockState(pos, Fluids.WATER.getDefaultState().getBlockState(), 11);
            }

            return true;
        }
    }

    protected void playEmptyingSound(PlayerEntity player, IWorld world, BlockPos pos) {
        world.playSound(player, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
    }
}
