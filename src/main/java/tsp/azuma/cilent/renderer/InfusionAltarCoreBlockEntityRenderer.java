package tsp.azuma.cilent.renderer;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.entity.EquipmentSlot;
import tsp.azuma.cilent.LabelRenderer;
import tsp.azuma.entity.InfusionAltarCoreBlockEntity;
import tsp.azuma.registry.Items;

public class InfusionAltarCoreBlockEntityRenderer extends BlockEntityRenderer<InfusionAltarCoreBlockEntity> {

    public InfusionAltarCoreBlockEntityRenderer(BlockEntityRenderDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void render(InfusionAltarCoreBlockEntity blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        if (MinecraftClient.getInstance().player.getEquippedStack(EquipmentSlot.HEAD).getItem().equals(Items.ALL_SEEING_GLASSES)) {
            LabelRenderer.renderLabelIfPresent(blockEntity, "Mana: " + blockEntity.getMana(), 0, matrices, vertexConsumers, light);
        }

        matrices.push();

        matrices.translate(.5f, 1.5f, .5f);
        matrices.translate(0, Math.sin(blockEntity.getWorld().getTime() / 10f) / 16, 0);
        matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(blockEntity.getWorld().getTime()));

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
