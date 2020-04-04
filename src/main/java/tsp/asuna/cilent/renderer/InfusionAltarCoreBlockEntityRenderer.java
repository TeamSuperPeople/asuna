package tsp.asuna.cilent.renderer;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import tsp.asuna.entities.InfusionAltarCoreBlockEntity;

public class InfusionAltarCoreBlockEntityRenderer extends BlockEntityRenderer<InfusionAltarCoreBlockEntity> {

    public InfusionAltarCoreBlockEntityRenderer(BlockEntityRenderDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void render(InfusionAltarCoreBlockEntity blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        matrices.push();

        matrices.translate(.5f, 1.5f, .5f);
        MinecraftClient.getInstance().getItemRenderer().renderItem(
                blockEntity.getHeldStack(),
                ModelTransformation.Mode.FIXED,
                light,
                overlay,
                matrices,
                vertexConsumers
        );

        matrices.pop();
    }
}