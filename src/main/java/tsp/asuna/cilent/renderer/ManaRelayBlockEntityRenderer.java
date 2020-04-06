package tsp.asuna.cilent.renderer;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import tsp.asuna.cilent.LabelRenderer;
import tsp.asuna.entity.ManaRelayBlockEntity;
import tsp.asuna.registry.Items;

public class ManaRelayBlockEntityRenderer extends BlockEntityRenderer<ManaRelayBlockEntity> {

    public ManaRelayBlockEntityRenderer(BlockEntityRenderDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void render(ManaRelayBlockEntity blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        if (MinecraftClient.getInstance().player.getEquippedStack(EquipmentSlot.HEAD).getItem().equals(Items.ALL_SEEING_GLASSES)) {
            LabelRenderer.renderLabelIfPresent(blockEntity, "Mana: " + blockEntity.getMana(), 0, matrices, vertexConsumers, light);
        }

        if (MinecraftClient.getInstance().crosshairTarget instanceof BlockHitResult) {
            if (((BlockHitResult) MinecraftClient.getInstance().crosshairTarget).getBlockPos().equals(blockEntity.getPos())) {
                blockEntity.getManaTargets().forEach(manaConnectable -> {
                    if (manaConnectable instanceof BlockEntity) {
                        BlockEntity be = (BlockEntity) manaConnectable;

                        Vec3d fromPos = new Vec3d(blockEntity.getPos()).add(.5, .5, .5);
                        Vec3d targetPos = new Vec3d(be.getPos()).add(.5, .5, .5);

                        double distance = Math.sqrt(getSquaredDistance(fromPos, targetPos)) * 10;
                        Vec3d per = new Vec3d((targetPos.getX() - fromPos.getX()) / distance, (targetPos.getY() - fromPos.getY()) / distance, (targetPos.getZ() - fromPos.getZ()) / distance);
                        Vec3d current = fromPos;

                        for (int i = 0; i < distance; i++) {
                            blockEntity.getWorld().addParticle(ParticleTypes.CRIT, current.x, current.y, current.z, 0, 0, 0);
                            current = current.add(per);
                        }
                    }
                });
            }
        }
    }

    public double getSquaredDistance(Vec3d from, Vec3d to) {
        double e = (double) from.getX() - to.getX();
        double f = (double) from.getY() - to.getY();
        double g = (double) from.getZ() - to.getZ();
        return e * e + f * f + g * g;
    }
}
