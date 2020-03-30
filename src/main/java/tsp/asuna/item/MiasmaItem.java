package tsp.asuna.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.thrown.SnowballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Settings;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import tsp.asuna.Asuna;
import tsp.asuna.entities.MiasmaEntity;

import java.util.List;

public class MiasmaItem extends Item{

    public MiasmaItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        tooltip.add(new TranslatableText("Conjure a ball of venomous gas from poisonous particles"));
        tooltip.add(new TranslatableText("from the atmosphere to launch at your foes"));
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        world.playSound((PlayerEntity)null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_ENDER_DRAGON_SHOOT, SoundCategory.NEUTRAL, 0.5F, 0.4F / (RANDOM.nextFloat() * 0.4F + 0.8F));
        user.getItemCooldownManager().set(this, 20);
        ItemStack itemStack = user.getStackInHand(hand);


        if (!world.isClient) {
            MiasmaEntity miasmaEntity = new MiasmaEntity(world, user);
            miasmaEntity.setProperties(user, user.pitch, user.yaw, 0F, 1.5F, 1F);
            world.spawnEntity(miasmaEntity);


                }


        user.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!user.abilities.creativeMode) {

    }



        return TypedActionResult.success(itemStack);
    }
}
