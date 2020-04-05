package tsp.asuna.cilent.renderer;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import tsp.asuna.cilent.LabelRenderer;
import tsp.asuna.entity.InfernalAbsorberBlockEntity;

public class InfernalAbsorberBlockEntityRenderer extends BlockEntityRenderer<InfernalAbsorberBlockEntity> {

    public InfernalAbsorberBlockEntityRenderer(BlockEntityRenderDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void render(InfernalAbsorberBlockEntity blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
//        LabelRenderer.renderLabelIfPresent(blockEntity, "▮▯▯▯▯▯▯▯▯▯", 0x29d0e3, 0, matrices, vertexConsumers, 15728880);
//        LabelRenderer.renderLabelIfPresent(blockEntity, "▮▯▯▯▯▯▯▯▯▯", 0xf74d14, .5f, matrices, vertexConsumers, 15728880);

        LabelRenderer.renderLabelIfPresent(blockEntity, getCursedDisplay(blockEntity.getMaxMana(), blockEntity.getMana()), 0x29d0e3, 0, matrices, vertexConsumers, 15728880);
        LabelRenderer.renderLabelIfPresent(blockEntity,  getCursedDisplay(blockEntity.getMaxMana(), blockEntity.getLava()), 0xf74d14, .5f, matrices, vertexConsumers, 15728880);
    }

    public String getCursedDisplay(int max, int amount) {
        String returnDisplay = "";
        float manaProgress = amount / (float) max; // 0.0 -> 1.0
        manaProgress *= 10; // 0.0 -> 10.0
        int progress = (int) Math.floor(manaProgress);

        for(int i = 0; i < progress; i++) {
            returnDisplay += "▮";
        }

        for(int i = 0; i < (10 - progress); i++) {
            returnDisplay += "▯";
        }

        return returnDisplay;
    }
}
