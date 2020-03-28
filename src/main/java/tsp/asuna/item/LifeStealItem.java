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

public class LifeStealItem extends Item {
    public LifeStealItem(Settings settings) {
        super(settings);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound((PlayerEntity)null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (RANDOM.nextFloat() * 0.4F + 0.8F));
        if (!world.isClient) {
            MiasmaEntity miasmaEntity = new MiasmaEntity(world, user);
            miasmaEntity.setItem(itemStack);
            miasmaEntity.setProperties(user, user.pitch, user.yaw, 5F, 2F, 3F);
            world.spawnEntity(miasmaEntity);
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!user.abilities.creativeMode) {

        }

        return TypedActionResult.success(itemStack);
    }
}

