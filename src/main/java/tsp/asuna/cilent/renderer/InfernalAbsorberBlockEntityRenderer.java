package tsp.asuna.cilent.renderer;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import tsp.asuna.cilent.LabelRenderer;
import tsp.asuna.entities.InfernalAbsorberBlockEntity;
import tsp.asuna.entities.ManaPylonBlockEntity;

public class InfernalAbsorberBlockEntityRenderer extends BlockEntityRenderer<InfernalAbsorberBlockEntity> {

    public InfernalAbsorberBlockEntityRenderer(BlockEntityRenderDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void render(InfernalAbsorberBlockEntity blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        LabelRenderer.renderLabelIfPresent(blockEntity, "Mana: " + blockEntity.getMana(), 0, matrices, vertexConsumers, 15728880);
        LabelRenderer.renderLabelIfPresent(blockEntity, "Lava: " + blockEntity.getLava(), .5f, matrices, vertexConsumers, 15728880);
    }
}
