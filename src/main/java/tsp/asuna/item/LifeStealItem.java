package tsp.asuna.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import tsp.asuna.entity.LifeStealEntity;

public class LifeStealItem extends ManaUtilizerItem {

    public LifeStealItem(Settings settings) {
        super(settings, 8);
    }

    @Override
    public TypedActionResult<ItemStack> manaUse(World world, PlayerEntity user, Hand hand) {
        assert world != null;

        user.getItemCooldownManager().set(this, 20);
        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_ENDER_DRAGON_SHOOT, SoundCategory.NEUTRAL, 0.5F, 0.4F / (RANDOM.nextFloat() * 0.4F + 0.8F));

        if (!world.isClient) {
            LifeStealEntity entity = new LifeStealEntity(world, user);
            entity.setProperties(user, user.pitch, user.yaw, 5F, 2F, 3F);
            entity.setPos(user.getX(), user.getY()+2, user.getZ());
            world.spawnEntity(entity);
        }

        return TypedActionResult.success(itemStack);
    }
}

