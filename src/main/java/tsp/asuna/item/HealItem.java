package tsp.asuna.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import tsp.asuna.entities.MiasmaEntity;

public class HealItem extends Item {

    public HealItem(Settings settings) {
        super(settings);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.getItemCooldownManager().set(this, 100);
        ItemStack itemStack = user.getStackInHand(hand);

        if (!world.isClient) {
           user.heal(4);
        }

        return TypedActionResult.success(itemStack);
    }
}

