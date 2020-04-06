package tsp.azuma.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import tsp.azuma.util.PlayerManaUtils;

public abstract class ManaUtilizerItem extends Item {

    private final int manaRequired;

    public ManaUtilizerItem(Settings settings, int manaRequired) {
        super(settings);
        this.manaRequired = manaRequired;
    }

    @Override
    final public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(PlayerManaUtils.getManaFromPlayer(user) >= manaRequired) {
            TypedActionResult<ItemStack> result = manaUse(world, user, hand);
            PlayerManaUtils.takeManaFromPlayer(user, manaRequired);
            return result;
        }

        return TypedActionResult.pass(user.getStackInHand(hand));
    }

    abstract TypedActionResult<ItemStack> manaUse(World world, PlayerEntity user, Hand hand);
}
