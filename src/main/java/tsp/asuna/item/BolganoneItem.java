package tsp.asuna.item;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.debug.DebugRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class BolganoneItem extends ManaUtilizerItem {

    public BolganoneItem(Settings settings) {
        super(settings, 8);
    }

    @Environment(EnvType.CLIENT)

    @Override
    public TypedActionResult<ItemStack> manaUse(World world, PlayerEntity user, Hand hand) {
        assert world != null;

        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_ENDER_DRAGON_SHOOT, SoundCategory.NEUTRAL, 0.5F, 0.4F / (RANDOM.nextFloat() * 0.4F + 0.8F));
        user.getItemCooldownManager().set(this, 20);

        if (!world.isClient) {
            Optional<Entity> optionalEntioty = DebugRenderer.getTargetedEntity(user, 100);

            if (optionalEntioty.isPresent() && optionalEntioty.get() instanceof LivingEntity) {
                LivingEntity entity = (LivingEntity) optionalEntioty.get();
                entity.damage(DamageSource.player(user),5);

                Vec3d pos = entity.getPos();
                for (AtomicInteger animationProgress = new AtomicInteger(); animationProgress.get() < 50; animationProgress.incrementAndGet()) {
                    int height = animationProgress.get();

                    ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
                    scheduledExecutorService.schedule(() -> {
                        world.getServer().execute(() -> {
                            ((ServerWorld) world).spawnParticles(ParticleTypes.FLAME, pos.getX() + Math.sin(height), entity.getY() + height / 10f, pos.getZ() + Math.cos(height), 5, 0f, 0f, 0f, .01);
                        });
                    }, 0, TimeUnit.MILLISECONDS);
                    // i * 10 = 1 per 10 ms, 500 entities = 500 * 10 ms total = 5 seconds
                }


                entity.setOnFireFor(5);
                user.getItemCooldownManager().set(this, 20);
            }
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        return TypedActionResult.success(itemStack);
    }
}

