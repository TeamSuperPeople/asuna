package tsp.asuna.entities;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.entity.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.hit.HitResult.Type;
import net.minecraft.world.World;
import tsp.asuna.registry.Entities;

public class MiasmaEntity extends ThrownItemEntity {
    public MiasmaEntity(World world) {
        super(Entities.MIASMA_ENTITY, world);
    }



    public MiasmaEntity(World world, LivingEntity owner) {
        super(Entities.MIASMA_ENTITY, owner, world);
    }

    public MiasmaEntity(World world, double x, double y, double z) {
        super(Entities.MIASMA_ENTITY, x, y, z, world);
    }




    protected Item getDefaultItem() {
        return Items.SNOWBALL;
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
    @Override
    protected void onCollision(HitResult hitResult) {
        if (hitResult.getType() == Type.ENTITY) {
            Entity entity = ((EntityHitResult)hitResult).getEntity();
            int i = entity instanceof BlazeEntity ? 3 : 0;
            entity.damage(DamageSource.thrownProjectile(this, this.getOwner()), (float)5);

        }

        if (!this.world.isClient) {
            this.world.sendEntityStatus(this, (byte)3);
            this.remove();
        }

    }
}
