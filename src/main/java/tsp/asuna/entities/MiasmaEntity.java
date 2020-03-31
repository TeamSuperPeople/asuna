package tsp.asuna.entities;

import io.netty.buffer.Unpooled;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FlyingItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.Packet;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.util.Identifier;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.hit.HitResult.Type;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import tsp.asuna.Asuna;
import tsp.asuna.registry.Entities;


import java.util.Iterator;

public class MiasmaEntity extends ThrownItemEntity implements FlyingItemEntity {



    public static final Identifier ENTITY_ID = Asuna.id("miasma");
    private double gravity = 0.03;

    public MiasmaEntity(World world, double x, double y, double z) {
        super(Entities.MIASMA_ENTITY, world);
        this.updatePosition(x, y, z);
        this.updateTrackedPosition(x, y, z);
    }

    public MiasmaEntity(World world, LivingEntity owner) {
        super(Entities.MIASMA_ENTITY, owner, world);
    }

    public MiasmaEntity(World world) {
        super(Entities.MIASMA_ENTITY, world);

    }

    @Override
    protected Item getDefaultItem() {
        return Items.SNOWBALL;
    }




    @Override
    public void tick() {
        super.tick();
        ParticleEffect particle = ParticleTypes.DRAGON_BREATH;

        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();

        this.world.addParticle(particle,x,y,z,0,0,0);
        }


    @Override
    public Packet<?> createSpawnPacket() {
        PacketByteBuf packet = new PacketByteBuf(Unpooled.buffer());

        packet.writeDouble(this.getX());
        packet.writeDouble(this.getY());
        packet.writeDouble(this.getZ());

        packet.writeInt(this.getEntityId());

        return ServerSidePacketRegistry.INSTANCE.toPacket(ENTITY_ID, packet);
    }


    @Override
    protected float getGravity() {
        return (float) 0.03;
    }



    Potion potion = Potions.POISON;
    @Override
    protected void onCollision(HitResult hitResult) {
        if (hitResult.getType() == Type.ENTITY) {
            Entity entity = ((EntityHitResult)hitResult).getEntity();
            entity.damage(DamageSource.thrownProjectile(this, this.getOwner()), (float)5);
            AreaEffectCloudEntity areaEffectCloudEntity = new AreaEffectCloudEntity(this.world, entity.getX(), entity.getY(), entity.getZ());
            areaEffectCloudEntity.setOwner(this.getOwner());
            areaEffectCloudEntity.setRadius(3.0F);
            areaEffectCloudEntity.setRadiusOnUse(-0.5F);
            areaEffectCloudEntity.setWaitTime(0);
            areaEffectCloudEntity.setDuration(70);
            areaEffectCloudEntity.setRadiusGrowth(-areaEffectCloudEntity.getRadius() / (float)areaEffectCloudEntity.getDuration());
            areaEffectCloudEntity.setPotion(potion);
            areaEffectCloudEntity.setColor(9699539);
            this.world.spawnEntity(areaEffectCloudEntity);

        }
        else if (hitResult.getType() == Type.BLOCK) {
            AreaEffectCloudEntity areaEffectCloudEntity = new AreaEffectCloudEntity(this.world, this.getX(), this.getY(), this.getZ());
            areaEffectCloudEntity.setOwner(this.getOwner());
            areaEffectCloudEntity.setRadius(3.0F);
            areaEffectCloudEntity.setRadiusOnUse(-0.5F);
            areaEffectCloudEntity.setDuration(70);
            areaEffectCloudEntity.setWaitTime(3);
            areaEffectCloudEntity.setRadiusGrowth(-areaEffectCloudEntity.getRadius() / (float)areaEffectCloudEntity.getDuration());
            areaEffectCloudEntity.setPotion(potion);
            areaEffectCloudEntity.setColor(9699539);
            this.world.spawnEntity(areaEffectCloudEntity);


        }

        if (!this.world.isClient) {
            this.world.sendEntityStatus(this, (byte)3);
            this.remove();
        }

    }


}
