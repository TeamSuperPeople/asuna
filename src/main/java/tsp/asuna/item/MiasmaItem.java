package tsp.asuna.item;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import tsp.asuna.entity.MiasmaEntity;

import java.util.List;

public class MiasmaItem extends Item {

    public MiasmaItem(Settings settings) {
        super(settings);
    }

    @Environment(EnvType.CLIENT)

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        assert world != null;

        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_ENDER_DRAGON_SHOOT, SoundCategory.NEUTRAL, 0.5F, 0.4F / (RANDOM.nextFloat() * 0.4F + 0.8F));
        user.getItemCooldownManager().set(this, 20);

        if (!world.isClient) {
            MiasmaEntity miasmaEntity = new MiasmaEntity(world, user);
            miasmaEntity.setProperties(user, user.pitch, user.yaw, 0F, 1.5F, 1F);
            world.spawnEntity(miasmaEntity);
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        return TypedActionResult.success(itemStack);
    }
}
