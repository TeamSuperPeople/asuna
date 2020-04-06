package tsp.azuma.entity;

import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import tsp.azuma.registry.Entities;

public class InfusionAltarPedestalBlockEntity extends BlockEntity implements BlockEntityClientSerializable {

    private boolean locked = false;
    private ItemStack heldStack = ItemStack.EMPTY;

    public InfusionAltarPedestalBlockEntity() {
        super(Entities.INFUSION_PEDESTAL);
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(locked) {
            return ActionResult.FAIL;
        }

        ItemStack playerStack = player.getStackInHand(hand);

        // insert item into pedestal
        if(heldStack.isEmpty() && !playerStack.isEmpty()) {
            ItemStack taken = playerStack.copy();
            taken.setCount(1);
            heldStack = taken;
            playerStack.decrement(1);
            sync();
            return ActionResult.SUCCESS;
        }

        // take item from pedestal
        else if (!heldStack.isEmpty()) {
            player.inventory.offerOrDrop(world, heldStack);
            heldStack = ItemStack.EMPTY;
            sync();
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean isLocked() {
        return locked;
    }

    public ItemStack getHeldStack() {
        return heldStack;
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        CompoundTag itemTag = new CompoundTag();
        heldStack.toTag(itemTag);
        tag.put("Inventory", itemTag);

        return super.toTag(tag);
    }

    @Override
    public void fromTag(CompoundTag tag) {
        CompoundTag itemTag = tag.getCompound("Inventory");
        this.heldStack = ItemStack.fromTag(itemTag);

        super.fromTag(tag);
    }

    @Override
    public void fromClientTag(CompoundTag tag) {
        fromTag(tag);
    }

    @Override
    public CompoundTag toClientTag(CompoundTag tag) {
        return toTag(tag);
    }

    public void clearItem() {
        this.heldStack = ItemStack.EMPTY;
        sync();
    }
}
