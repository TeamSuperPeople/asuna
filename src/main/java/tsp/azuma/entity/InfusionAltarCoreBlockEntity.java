package tsp.azuma.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.Tickable;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import tsp.azuma.Azuma;
import tsp.azuma.api.ManaDurable;
import tsp.azuma.recipe.AltarRecipe;
import tsp.azuma.recipe.AltarState;
import tsp.azuma.recipe.AltarRecipeUtils;
import tsp.azuma.registry.Components;
import tsp.azuma.registry.Entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InfusionAltarCoreBlockEntity extends ManaBlockEntity implements Tickable {

    private static final int MAX_MANA = 2000;
    private static final int MAX_OUTPUT = 0;
    private static final int MAX_INPUT = 100;

    private ItemStack heldStack = ItemStack.EMPTY;
    private AltarRecipe cachedRecipe;

    private boolean isInfusing = false;
    private int infusionProgress = 0;

    public InfusionAltarCoreBlockEntity() {
        super(Entities.INFUSION_ALTAR_CORE, MAX_MANA, MAX_OUTPUT, MAX_INPUT);
    }

    @Override
    public void tick() {
        if(world != null && !world.isClient && !heldStack.isEmpty()) {
            // charge items
            if(!isInfusing && heldStack.getItem() instanceof ManaDurable) {
                if(this.getMana() > 0) {
                    Components.MANA.get(heldStack).increment(1);
                    this.storedMana --;
                    sync();
                }
            }

            if(isInfusing) {
                infusionProgress++;

                if(infusionProgress >= 100) {
                    isInfusing = false;
                    infusionProgress = 0;
                    lockPedestals(false);
                    heldStack = cachedRecipe.getOutput().copy();
                    AltarRecipeUtils.takeItems(this.cachedRecipe, getAltarState());
                    this.cachedRecipe = null;
                    sync();
                }
            } else {
                for (Map.Entry<Identifier, AltarRecipe> entry : Azuma.ALTAR_RECIPE_MANAGER.getRecipes().entrySet()) {
                    if (entry.getValue().getCenterItem() == heldStack.getItem()) {
                        if (AltarRecipeUtils.canCraft(entry.getValue(), getAltarState())) {
                            if (entry.getValue().getManaRequirement() <= this.getMana()) {
                                isInfusing = true;
                                lockPedestals(true);
                                cachedRecipe = entry.getValue();

                                // instantly take required mana
                                this.storedMana -= entry.getValue().getManaRequirement();

                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    private AltarState getAltarState() {
        return new AltarState(this, getFirstRing(), getSecondRing(), getThirdRing());
    }

    private List<InfusionAltarPedestalBlockEntity> getFirstRing() {
        assert world != null;
        ArrayList<InfusionAltarPedestalBlockEntity> found = new ArrayList<>();

        for(Direction direction : Direction.values()) {
            if(direction.getAxis() != Direction.Axis.Y) {
                BlockPos checkPos = this.pos.offset(direction, 4);

                if(world.getBlockEntity(checkPos) instanceof InfusionAltarPedestalBlockEntity) {
                    found.add((InfusionAltarPedestalBlockEntity) world.getBlockEntity((checkPos)));
                } else {
                    return null;
                }
            }
        }

        return found;
    }

    private List<InfusionAltarPedestalBlockEntity> getSecondRing() {
        assert world != null;
        ArrayList<InfusionAltarPedestalBlockEntity> found = new ArrayList<>();

        for(Direction direction : Direction.values()) {
            if(direction.getAxis() != Direction.Axis.Y) {
                BlockPos checkPos = this.pos.offset(direction, 3).offset(direction.rotateYClockwise(), 3);

                if(world.getBlockEntity(checkPos) instanceof InfusionAltarPedestalBlockEntity) {
                    found.add((InfusionAltarPedestalBlockEntity) world.getBlockEntity((checkPos)));
                } else {
                    return null;
                }
            }
        }

        return found;
    }

    private List<InfusionAltarPedestalBlockEntity> getThirdRing() {
        assert world != null;
        ArrayList<InfusionAltarPedestalBlockEntity> found = new ArrayList<>();

        for(Direction direction : Direction.values()) {
            if(direction.getAxis() != Direction.Axis.Y) {
                // add straight out
                BlockPos checkPos = this.pos.offset(direction, 7).up();

                if(world.getBlockEntity(checkPos) instanceof InfusionAltarPedestalBlockEntity) {
                    found.add((InfusionAltarPedestalBlockEntity) world.getBlockEntity((checkPos)));
                } else {
                    return null;
                }

                // add corner
                checkPos = this.pos.offset(direction, 5).offset(direction.rotateYClockwise(), 5).up();

                if(world.getBlockEntity(checkPos) instanceof InfusionAltarPedestalBlockEntity) {
                    found.add((InfusionAltarPedestalBlockEntity) world.getBlockEntity((checkPos)));
                } else {
                    return null;
                }
            }
        }

        return found;
    }

    private void lockPedestals(boolean lock) {
        List<InfusionAltarPedestalBlockEntity> first = getFirstRing();
        List<InfusionAltarPedestalBlockEntity> second = getSecondRing();
        List<InfusionAltarPedestalBlockEntity> third = getThirdRing();

        if(first != null) {
            first.forEach(pedestal -> pedestal.setLocked(lock));
        }

        if(second != null) {
            second.forEach(pedestal -> pedestal.setLocked(lock));
        }

        if(third != null) {
            third.forEach(pedestal -> pedestal.setLocked(lock));
        }
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack playerStack = player.getStackInHand(hand);

        // insert item into pedestal
        if(heldStack.isEmpty() && !playerStack.isEmpty()) {
            if(!world.isClient) {
                ItemStack taken = playerStack.copy();
                taken.setCount(1);
                heldStack = taken;
                playerStack.decrement(1);
                sync();
            }
            return ActionResult.SUCCESS;
        }

        // take item from pedestal
        else if (!heldStack.isEmpty() && player.getStackInHand(hand).isEmpty()) {
            if(!world.isClient) {
                player.setStackInHand(hand, heldStack.copy());
                this.heldStack = ItemStack.EMPTY;
                sync();
            }

            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    @Override
    public boolean canInsert() {
        return true;
    }

    @Override
    public boolean canExtract() {
        return false;
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
}
