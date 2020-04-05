package tsp.asuna.cilent.renderer;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import tsp.asuna.cilent.LabelRenderer;
import tsp.asuna.entity.InfernalYeeterBlockEntity;

public class InfernalYeeterBlockEntityRenderer extends BlockEntityRenderer<InfernalYeeterBlockEntity> {

    public InfernalYeeterBlockEntityRenderer(BlockEntityRenderDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void render(InfernalYeeterBlockEntity blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        LabelRenderer.renderLabelIfPresent(blockEntity, "Mana: " + blockEntity.getMana(), 0, matrices, vertexConsumers, 15728880);
    }
}
