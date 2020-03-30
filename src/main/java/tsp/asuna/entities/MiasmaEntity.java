package tsp.asuna.entities;

import io.netty.buffer.Unpooled;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.Entity;
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
import net.minecraft.world.World;
import tsp.asuna.Asuna;
import tsp.asuna.registry.Entities;

public class MiasmaEntity extends ThrownItemEntity  {

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
        if (!(gravity <=0)) {
            gravity = gravity - 0.001;
        }
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


        }
        else if (hitResult.getType() == Type.BLOCK) {
            AreaEffectCloudEntity areaEffectCloudEntity = new AreaEffectCloudEntity(this.world, this.getX(), this.getY(), this.getZ());
            areaEffectCloudEntity.setOwner(this.getOwner());
            areaEffectCloudEntity.setRadius(3.0F);
            areaEffectCloudEntity.setRadiusOnUse(-0.5F);
            areaEffectCloudEntity.setWaitTime(3);
            areaEffectCloudEntity.setRadiusGrowth(-areaEffectCloudEntity.getRadius() / (float)areaEffectCloudEntity.getDuration());
            areaEffectCloudEntity.setPotion(potion);
            this.world.spawnEntity(areaEffectCloudEntity);


        }

        if (!this.world.isClient) {
            this.world.sendEntityStatus(this, (byte)3);
            this.remove();
        }

    }
}
