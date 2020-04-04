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

    public ManaPylonBlockEntityRenderer(BlockEntityRenderDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void render(ManaPylonBlockEntity blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        LabelRenderer.renderLabelIfPresent(blockEntity, "Mana: " + blockEntity.getMana(), 0, matrices, vertexConsumers, 15728880);
        blockEntity.incrementAnimationProgress();

        matrices.push();
        matrices.translate(.5f, 4, .5f);
        matrices.translate(0, Math.sin(blockEntity.getAnimationProgress() / 40f) / 5, 0);
        matrices.scale(5, 5, 5);
        MinecraftClient.getInstance().getItemRenderer().renderItem(RELAY, ModelTransformation.Mode.FIXED, light, overlay, matrices, vertexConsumers);
        matrices.pop();
    }
}
