package tsp.asuna.entity;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.Packet;
import net.minecraft.util.Identifier;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import tsp.asuna.Asuna;
import tsp.asuna.registry.Entities;
import tsp.asuna.registry.Items;

public class ManaBombEntity extends ThrownItemEntity {

    public static final Identifier ENTITY_ID = Asuna.id("mana_bomb");
    private float strength = 4.0f;

    public ManaBombEntity(World world) {
        super(Entities.MANA_BOMB, world);
    }

    public ManaBombEntity(World world, double x, double y, double z, Entity owner) {
        super(Entities.MANA_BOMB, world);
        this.updatePosition(x, y, z);
        this.owner = (LivingEntity) owner;
        this.updateTrackedPosition(x, y, z);
    }

    public ManaBombEntity(World world, PlayerEntity user) {
        super(Entities.MANA_BOMB, world);
        this.owner = user;
    }

    @Override
    protected Item getDefaultItem() {
        return Items.MANA_BOMB;
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        world.createExplosion(this, hitResult.getPos().x, hitResult.getPos().y, hitResult.getPos().z, strength, Explosion.DestructionType.BREAK);
        this.remove();
    }

    @Override
    public Packet<?> createSpawnPacket() {
        PacketByteBuf packet = new PacketByteBuf(Unpooled.buffer());

        packet.writeDouble(this.getX());
        packet.writeDouble(this.getY());
        packet.writeDouble(this.getZ());

        packet.writeInt(this.getEntityId());
        packet.writeInt(this.owner.getEntityId());

        return ServerSidePacketRegistry.INSTANCE.toPacket(ENTITY_ID, packet);
    }

    public void setStrength(float strength) {
        this.strength = strength;
    }
}
