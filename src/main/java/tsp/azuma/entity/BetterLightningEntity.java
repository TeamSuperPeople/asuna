package tsp.azuma.entity;

import net.minecraft.advancement.criterion.Criterions;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.List;

public class BetterLightningEntity extends LightningEntity {
    public int ambientTick;
    public final boolean cosmetic;
    public int remainingActions;
    private ServerPlayerEntity channeller;
    public int spreadAttempts = 0;

    public BetterLightningEntity(World world, double x, double y, double z, boolean cosmetic) {
        super(world, x, y, z, cosmetic);
        this.cosmetic = cosmetic;
        this.ambientTick = 2;
        this.remainingActions = this.random.nextInt(3) + 1;
    }
    public void setChanneller(@Nullable ServerPlayerEntity channeller) {
        this.channeller = channeller;
    }
    @Override
    public void tick() {
        super.tick();
        if (this.ambientTick == 2) {
            this.world.playSound((PlayerEntity)null, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER, SoundCategory.WEATHER, 1.0F, 0.8F + this.random.nextFloat() * 0.2F);
            this.world.playSound((PlayerEntity)null, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT, SoundCategory.WEATHER, 1.0F, 0.5F + this.random.nextFloat() * 0.2F);
        }

        --this.ambientTick;
        if (this.ambientTick < 0) {
            if (this.remainingActions == 0) {
                this.remove();
            } else if (this.ambientTick < -this.random.nextInt(10)) {
                --this.remainingActions;
                this.ambientTick = 1;
                this.seed = this.random.nextLong();
            }
        }

        if (this.ambientTick >= 0) {
            if (this.world.isClient) {
                this.world.setLightningTicksLeft(2);
            } else if (!this.cosmetic) {
                double d = 3.0D;
                List<Entity> list = this.world.getEntities(this, new Box(this.getX() - 3.0D, this.getY() - 3.0D, this.getZ() - 3.0D, this.getX() + 3.0D, this.getY() + 6.0D + 3.0D, this.getZ() + 3.0D), Entity::isAlive);
                Iterator var4 = list.iterator();

                while(var4.hasNext()) {
                    Entity entity = (Entity)var4.next();
                    entity.onStruckByLightning(this);
                }

                if (this.channeller != null) {
                    Criterions.CHANNELED_LIGHTNING.trigger(this.channeller, list);
                }
            }
        }

    }

    private void spawnFire(int spreadAttempts) {

    }

}
