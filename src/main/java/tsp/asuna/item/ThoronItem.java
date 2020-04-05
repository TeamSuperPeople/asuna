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
import tsp.asuna.entity.ThoronEntity;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThoronItem extends Item {
    public ThoronItem(Settings settings) {
        super(settings);
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        tooltip.add(new TranslatableText("Conjure a ball of venomous gas from poisonous particles").formatted(Formatting.GRAY));
        tooltip.add(new TranslatableText("from the atmosphere to launch at your foes").formatted(Formatting.GRAY));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        assert world != null;

        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_ENDER_DRAGON_SHOOT, SoundCategory.NEUTRAL, 0.5F, 0.4F / (RANDOM.nextFloat() * 0.4F + 0.8F));
        user.getItemCooldownManager().set(this, 20);

        if (!world.isClient) {
            // DO NOT KEEP THIS AT 500
            // STOPSHIP: 4/1/2020 DOO NOT KEEP THIS
            for (int i = 0; i < 5; i++) {
                ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
                scheduledExecutorService.schedule(() -> {
                    world.getServer().execute(() -> {
                        ThoronEntity thoronEntity = new ThoronEntity(world, user);
                        thoronEntity.setProperties(user, user.pitch, user.yaw, 0F, 2, 0);
                        thoronEntity.setPos(user.getX(),user.getY()+2,user.getZ());
                        world.spawnEntity(thoronEntity);
                    });
                }, i * 100, TimeUnit.MILLISECONDS);
                // i * 10 = 1 per 10 ms, 500 entities = 500 * 10 ms total = 5 seconds
            }
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        return TypedActionResult.success(itemStack);
    }
}



