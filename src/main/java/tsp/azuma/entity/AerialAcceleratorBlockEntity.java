package tsp.azuma.entity;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import tsp.azuma.block.AerialAcceleratorBlock;
import tsp.azuma.registry.Entities;

public class AerialAcceleratorBlockEntity extends BlockEntity implements Tickable {

    public AerialAcceleratorBlockEntity() {
        super(Entities.AERIAL_ACCELERATOR);
    }

    @Override
    public void tick() {
        Direction facing = getCachedState().get(AerialAcceleratorBlock.FACING);
        Box checkBox = new Box(pos, pos.offset(facing, 10).up().offset(facing.rotateYClockwise()));

        if(world != null && !world.isClient) {
            world.getEntities(null, checkBox).forEach(entity -> {
                entity.setVelocity(facing.getOffsetX(), facing.getOffsetY(), facing.getOffsetZ());
                entity.velocityModified = true;
            });
        }
    }
}
