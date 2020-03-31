package tsp.asuna.entities;

import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import tsp.asuna.block.InfernalYeeterBlock;
import tsp.asuna.registry.Entities;

public class InfernalYeeterBlockEntity extends ManaBlockEntity {

    public InfernalYeeterBlockEntity() {
        super(Entities.INFERNAL_YEETER, 500, 0, 10);
    }

    @Override
    public void tick() {

    }

    public void pulse(int receivedRedstonePower) {
        if (world == null) {
            return;
        }

        if (world instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) world;

            Direction direction = getCachedState().get(InfernalYeeterBlock.FACING);
            serverWorld.spawnParticles(ParticleTypes.FLAME, pos.getX(), pos.getY(), pos.getZ(), 50, direction.getOffsetX() * 3, direction.getOffsetY() * 3, direction.getOffsetZ() * 3, 0.1);

            int usedMana = Math.min(getMana(), getManaForRedstone(receivedRedstonePower + 1));
            this.storedMana = Math.max(0, getMana() - usedMana);
            sync();
        }
    }

    public int getManaForRedstone(int redstone) {
        return Math.max(1, redstone * 4);
    }

    @Override
    public boolean canInsert() {
        return true;
    }

    @Override
    public boolean canExtract() {
        return false;
    }
}
