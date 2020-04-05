package tsp.asuna.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Tickable;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import tsp.asuna.registry.Entities;

// todo: rename
// heated mana generator?
public class BurningManaGeneratorBlockEntity extends ManaBlockEntity implements Tickable {

    private static final int MAX_MANA = 200;
    private static final int MAX_OUTPUT = 0;
    private static final int MAX_INPUT = 2;

    private ItemStack fuelStack = ItemStack.EMPTY;

    public BurningManaGeneratorBlockEntity() {
        super(Entities.BURNING_MANA_GENERATOR, MAX_MANA, MAX_OUTPUT, MAX_INPUT);
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack playerStack = player.getStackInHand(hand);

        //  insert item
        if(fuelStack.isEmpty() && !playerStack.isEmpty()) {
            if(!world.isClient) {
                fuelStack = playerStack.copy();
                player.setStackInHand(hand, ItemStack.EMPTY);
                sync();
            }

            return ActionResult.SUCCESS;
        }

        // extract to empty hand
        if(!fuelStack.isEmpty() && playerStack.isEmpty()) {
            if(!world.isClient) {
                player.setStackInHand(hand, fuelStack.copy());
                fuelStack = ItemStack.EMPTY;
                sync();
            }

            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    @Override
    public void tick() {

    }

    @Override
    public boolean canInsert() {
        return false;
    }

    @Override
    public boolean canExtract() {
        return true;
    }
}
