package tsp.asuna.entities;

import io.netty.buffer.Unpooled;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
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
import net.minecraft.util.Identifier;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.hit.HitResult.Type;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import tsp.asuna.Asuna;
import tsp.asuna.registry.Entities;

public class LifeStealEntity extends ThrownItemEntity  {

    public static final Identifier ENTITY_ID = Asuna.id("life_steal");
    private double gravity = 0.03;

    public LifeStealEntity(World world, double x, double y, double z) {
        super(Entities.LIFESTEAL_ENTITY, world);
        this.updatePosition(x, y, z);
        this.updateTrackedPosition(x, y, z);
    }

    public LifeStealEntity(World world, LivingEntity owner) {
        super(Entities.LIFESTEAL_ENTITY, owner, world);
    }

    public LifeStealEntity(World world) {
        super(Entities.LIFESTEAL_ENTITY, world);
    }

    @Override
    public Item getDefaultItem() {
        return Items.SNOWBALL;
    }

    @Override
    public void tick() {
        super.tick();

        if (!(gravity <= 0)) {
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
        return itemStack.isEmpty() ? ParticleTypes.CLOUD : new ItemStackParticleEffect(ParticleTypes.ITEM, itemStack);
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
                    this.world.addParticle(effect,particlePos.x,particlePos.y,particlePos.z,0,0,0);
                }
            }
        }


        if (!this.world.isClient) {
            this.world.sendEntityStatus(this, (byte)3);
            this.remove();
        }

    }
}
