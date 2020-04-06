package tsp.azuma.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import tsp.azuma.Azuma;
import tsp.azuma.entity.ManaBombEntity;

public class ManaBombItem extends Item {

    private final float strength;

    public ManaBombItem(float strength) {
        super(new Item.Settings().group(Azuma.GROUP).maxCount(16));
        this.strength = strength;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        assert world != null;

        if(!world.isClient) {
            ManaBombEntity manaBomb = new ManaBombEntity(world, user);
            manaBomb.setProperties(user, user.pitch, user.yaw, 0F, 1.25f, 0);
            manaBomb.setPos(user.getX(), user.getY() + 2, user.getZ());
            manaBomb.setStrength(strength);
            world.spawnEntity(manaBomb);
        }

        if(!user.isCreative()) {
            user.getStackInHand(hand).decrement(1);
        }

        user.getItemCooldownManager().set(this, (int) (20 * (strength / 4f)));

        return super.use(world, user, hand);
    }

    @Override
    public boolean hasEnchantmentGlint(ItemStack stack) {
        return strength > 30;
    }
}
