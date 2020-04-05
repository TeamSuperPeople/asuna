package tsp.asuna.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.inventory.BasicInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.SmeltingRecipe;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import tsp.asuna.block.InfernalYeeterBlock;
import tsp.asuna.registry.Entities;

import java.util.List;
import java.util.Optional;

public class InfernalYeeterBlockEntity extends ManaBlockEntity {

    private static final int MANA_PER_COOK = 1;

    public InfernalYeeterBlockEntity() {
        super(Entities.INFERNAL_YEETER, 500, 0, 10);
    }

    public void pulse(int receivedRedstonePower) {
        if (world == null) {
            return;
        }

        if (world instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) world;

            Direction direction = getCachedState().get(InfernalYeeterBlock.FACING);

            List<ItemEntity> smeltableEntities = world.getEntities(EntityType.ITEM, new Box(pos.offset(getCachedState().get(InfernalYeeterBlock.FACING))), entity -> {
                Optional<SmeltingRecipe> cooked = world.getRecipeManager().getFirstMatch(RecipeType.SMELTING, new BasicInventory(entity.getStack()), world);
                return cooked.isPresent();
            });

            smeltableEntities.forEach(entity -> {
                if(getMana() - MANA_PER_COOK * entity.getStack().getCount() >= 0) {
                    serverWorld.spawnParticles(ParticleTypes.FLAME, pos.getX(), pos.getY(), pos.getZ(), 50, direction.getOffsetX() * 3, direction.getOffsetY() * 3, direction.getOffsetZ() * 3, 0.1);
                    this.storedMana -= MANA_PER_COOK * entity.getStack().getCount();

                    Optional<SmeltingRecipe> cooked = world.getRecipeManager().getFirstMatch(
                            RecipeType.SMELTING,
                            new BasicInventory(entity.getStack()),
                            world
                    );

                    int cachedCount = entity.getStack().getCount();
                    cooked.ifPresent(smeltingRecipe -> {
                        ItemStack result = smeltingRecipe.getOutput();
                        result.setCount(cachedCount);
                        entity.setStack(result);
                    });
                }
            });

            sync();
        }
    }

    @Override
    public boolean canInsert() {
        return true;
    }

    @Override
    public boolean canExtract() {
        return false;
    }
}
