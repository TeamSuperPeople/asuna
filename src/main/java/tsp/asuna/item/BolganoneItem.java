package tsp.asuna.item;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import tsp.asuna.entities.MiasmaEntity;
import tsp.asuna.entities.ThoronEntity;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BolganoneItem extends Item {

    public BolganoneItem(Settings settings) {
        super(settings);
    }

    @Environment(EnvType.CLIENT)

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        assert world != null;

        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_ENDER_DRAGON_SHOOT, SoundCategory.NEUTRAL, 0.5F, 0.4F / (RANDOM.nextFloat() * 0.4F + 0.8F));
        user.getItemCooldownManager().set(this, 20);

        HitResult hitResult = user.rayTrace(50D,100F,true);
        if (!world.isClient) {
            if (hitResult.getType() == HitResult.Type.ENTITY) {


                EntityHitResult entityHitResult = ((EntityHitResult) hitResult);
                Entity entity = entityHitResult.getEntity();
                entity.setOnFireFor(5);


                user.getItemCooldownManager().set(this, 20);

                for (int animationProgress = 0; animationProgress < 5; animationProgress++) {
                    ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
                    scheduledExecutorService.schedule(() -> {
                        world.getServer().execute(() -> {
                            world.addParticle(ParticleTypes.FLAME, entity.getX() + Math.cos(animationProgress / 5f + 16), entity.getY() + animationProgress % 50 / 25f, entity.getZ() + Math.sin(animationProgress / 5f + 16), 5, 0, 0, 0);
                        });
                    }, animationProgress * 100, TimeUnit.MILLISECONDS);
                    // i * 10 = 1 per 10 ms, 500 entities = 500 * 10 ms total = 5 seconds
                }
            }
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        return TypedActionResult.success(itemStack);
    }
}

