package tsp.asuna.entities;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.entity.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.PotionItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.hit.HitResult.Type;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import tsp.asuna.registry.Entities;

import java.util.Iterator;

public class LifeStealEntity extends ThrownItemEntity  {
    public LifeStealEntity(World world) {
        super(Entities.LIFESTEAL_ENTITY, world);
    }



    public LifeStealEntity(World world, LivingEntity owner) {
        super(Entities.LIFESTEAL_ENTITY, owner, world);
    }

    public LifeStealEntity(World world, double x, double y, double z) {
        super(Entities.LIFESTEAL_ENTITY, x, y, z, world);
    }




    protected Item getDefaultItem() {
        return Items.SNOWBALL;
    }

    double gravity = 0.03;
    @Override
    public void tick() {
        super.tick();
        if (!(gravity <=0)) {
            gravity = gravity - 0.001;
        }
    }


    @Override
    protected float getGravity() {
        return (float) 0.03;
    }

    @Environment(EnvType.CLIENT)
    private ParticleEffect getParticleParameters() {
        ItemStack itemStack = this.getItem();
        return (ParticleEffect)(itemStack.isEmpty() ? ParticleTypes.CLOUD : new ItemStackParticleEffect(ParticleTypes.ITEM, itemStack));
    }

    @Environment(EnvType.CLIENT)
    public void handleStatus(byte status) {
        if (status == 3) {
            ParticleEffect particleEffect = this.getParticleParameters();

            for(int i = 0; i < 8; ++i) {
                this.world.addParticle(particleEffect, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }

    }

    Potion potion = Potions.POISON;
    @Override
    protected void onCollision(HitResult hitResult) {
        if (hitResult.getType() == Type.ENTITY) {
            Entity entity = ((EntityHitResult)hitResult).getEntity();
            int i = entity instanceof BlazeEntity ? 3 : 0;
            entity.damage(DamageSource.thrownProjectile(this, this.getOwner()), (float)5);
            // get positions of people
            double z = getZ();
            double x = getX();
            double y = getY();

            if (entity instanceof LivingEntity) {
                ((LivingEntity) entity).heal(2.5F);
                double z2 = entity.getZ();
                double x2 = entity.getX();
                double y2 = entity.getY();

                // paritlces

                ParticleEffect effect = ParticleTypes.SMOKE;
                world.addParticle(effect, x, y, z,0,0,0);

                double distancex = x - x2;
                double distancey = y - y2;
                double distancez = z - z2;

                double slope = Math.sqrt(Math.pow(distancex,2) + Math.pow(distancey,2) + Math.pow(distancez,2));

                Vec3d playerPos = entity.getPos();

                double divdedx =  distancex/slope;
                double divdedy =  distancey/slope;
                double divdedz =  distancez/slope;

                for (int b = 0; b < slope; b++) {

                    Vec3d particlePos = playerPos.add(divdedx,divdedy,divdedz);
                    world.addParticle(effect,particlePos.x,particlePos.y,particlePos.z,0,0,0);
                }
            }
        }


        if (!this.world.isClient) {
            this.world.sendEntityStatus(this, (byte)3);
            this.remove();
        }

    }
}
