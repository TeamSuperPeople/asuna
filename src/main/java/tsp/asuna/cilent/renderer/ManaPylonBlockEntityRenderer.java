package tsp.asuna.cilent.renderer;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.registry.Registry;
import tsp.asuna.Asuna;
import tsp.asuna.cilent.LabelRenderer;
import tsp.asuna.entities.ManaPylonBlockEntity;

public class ManaPylonBlockEntityRenderer extends BlockEntityRenderer<ManaPylonBlockEntity> {

    private static final ItemStack RELAY = new ItemStack(Registry.ITEM.get(Asuna.id("mana_pylon")));
    private int animationProgress = 0;

    public ManaPylonBlockEntityRenderer(BlockEntityRenderDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void render(ManaPylonBlockEntity blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        LabelRenderer.renderLabelIfPresent(blockEntity, "Mana: " + blockEntity.getMana(), 0, matrices, vertexConsumers, 15728880);
        animationProgress++;

        matrices.push();
        matrices.translate(0, 4, 0);
        matrices.translate(0, Math.sin(animationProgress / 20f) / 20, 0);
        matrices.scale(3, 3, 3);
        MinecraftClient.getInstance().getItemRenderer().renderItem(RELAY, ModelTransformation.Mode.FIXED, light, overlay, matrices, vertexConsumers);
        matrices.pop();
    }
}
