package tsp.azuma.entity;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Tickable;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import tsp.azuma.block.BurningManaGeneratorBlock;
import tsp.azuma.registry.Entities;

// todo: rename
// heated mana generator?
public class BurningManaGeneratorBlockEntity extends ManaBlockEntity implements Tickable {

    private static final int MAX_MANA = 200;
    private static final int MAX_OUTPUT = 0;
    private static final int MAX_INPUT = 2;

    private static final int MANA_PER_ADDITION = 1;
    private static final int ADD_TICK_TIME = 5 * 20; // if(burningTime % addTickTime), adds manaPerAdd. 20 = n mana per second.
    // default is half burn time with mana every 5 seconds instead of 10.

    private ItemStack fuelStack = ItemStack.EMPTY;
    private boolean isBurning = false;
    private int burningTime = 0;
    private int currentMaxBurnTime = 0;


    public BurningManaGeneratorBlockEntity() {
        super(Entities.BURNING_MANA_GENERATOR, MAX_MANA, MAX_OUTPUT, MAX_INPUT);
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack playerStack = player.getStackInHand(hand);

        //  insert item
        if(fuelStack.isEmpty() && !playerStack.isEmpty()) {
            if(!world.isClient) {

                // don't allow inserting non-fuel items
                if(FuelRegistry.INSTANCE.get(playerStack.getItem()) != null) {
                    fuelStack = playerStack.copy();
                    player.setStackInHand(hand, ItemStack.EMPTY);
                    sync();
                }
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
        if(world == null || world.isClient) {
            return;
        }

        // attempt to start burning
        if(!isBurning) {
            if(getMana() < getMaxMana()) {
                Integer burnTime = FuelRegistry.INSTANCE.get(fuelStack.getItem());

                if(burnTime != null && burnTime >= 0) {
                    currentMaxBurnTime = burnTime / 2;
                    isBurning = true;
                    fuelStack.decrement(1);
                    setBurningVisuals(true);
                    sync();
                }
            }
        } else {
            burningTime++;

            if(burningTime % ADD_TICK_TIME == 0) {
                this.insertMana(MANA_PER_ADDITION);
            }

            // stop burning
            if(burningTime >= currentMaxBurnTime) {
                isBurning = false;
                burningTime = 0;
                currentMaxBurnTime = 0;
                setBurningVisuals(false);
                sync();
            }
        }
    }

    public void setBurningVisuals(boolean burning) {
        if(world != null) {
            world.setBlockState(pos, getCachedState().with(BurningManaGeneratorBlock.LIT, burning));
        }
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        CompoundTag stackTag = new CompoundTag();
        fuelStack.toTag(stackTag);
        tag.put("FuelStack", stackTag);

        return super.toTag(tag);
    }

    @Override
    public void fromTag(CompoundTag tag) {
        CompoundTag stackTag = tag.getCompound("FuelStack");
        this.fuelStack = ItemStack.fromTag(stackTag);

        super.fromTag(tag);
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
