package tsp.azuma.cilent.renderer;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import tsp.azuma.cilent.LabelRenderer;
import tsp.azuma.entity.InfernalAbsorberBlockEntity;
import tsp.azuma.registry.Items;

public class InfernalAbsorberBlockEntityRenderer extends BlockEntityRenderer<InfernalAbsorberBlockEntity> {

    public InfernalAbsorberBlockEntityRenderer(BlockEntityRenderDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void render(InfernalAbsorberBlockEntity blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        if(MinecraftClient.getInstance().player.getEquippedStack(EquipmentSlot.HEAD).getItem().equals(Items.ALL_SEEING_GLASSES)) {
            LabelRenderer.renderLabelIfPresent(blockEntity, getCursedDisplay(blockEntity.getMaxMana(), blockEntity.getMana()), 0x29d0e3, 0, matrices, vertexConsumers, 15728880);
            LabelRenderer.renderLabelIfPresent(blockEntity, getCursedDisplay(blockEntity.getMaxMana(), blockEntity.getLava()), 0xf74d14, .5f, matrices, vertexConsumers, 15728880);
        }
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
