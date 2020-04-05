package tsp.asuna.cilent.renderer;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import tsp.asuna.entity.ManaBombEntity;
import tsp.asuna.registry.Items;

public class ManaBombEntityRenderer extends EntityRenderer<ManaBombEntity> {

    private static final ItemStack RENDER_STACK = new ItemStack(Items.MANA_BOMB);

    public ManaBombEntityRenderer(EntityRenderDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public Identifier getTexture(ManaBombEntity entity) {
        return null;
    }

    @Override
    public void render(ManaBombEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();
        matrices.scale(1, 1, 1);
        matrices.multiply(this.renderManager.getRotation());
        MinecraftClient.getInstance().getItemRenderer().renderItem(RENDER_STACK, ModelTransformation.Mode.GROUND, light, OverlayTexture.DEFAULT_UV, matrices, vertexConsumers);
        matrices.pop();
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }
}