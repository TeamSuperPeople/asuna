package tsp.azuma.cilent.renderer;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import tsp.azuma.cilent.LabelRenderer;
import tsp.azuma.entity.InfernalYeeterBlockEntity;
import tsp.azuma.registry.Items;

public class InfernalYeeterBlockEntityRenderer extends BlockEntityRenderer<InfernalYeeterBlockEntity> {

    public InfernalYeeterBlockEntityRenderer(BlockEntityRenderDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void render(InfernalYeeterBlockEntity blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        if(MinecraftClient.getInstance().player.getEquippedStack(EquipmentSlot.HEAD).getItem().equals(Items.ALL_SEEING_GLASSES)) {
            LabelRenderer.renderLabelIfPresent(blockEntity, "Mana: " + blockEntity.getMana(), 0, matrices, vertexConsumers, 15728880);
        }
    }
}
