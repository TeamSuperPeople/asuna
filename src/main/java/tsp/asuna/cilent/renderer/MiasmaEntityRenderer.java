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
import tsp.asuna.Asuna;
import tsp.asuna.entities.MiasmaEntity;
import tsp.asuna.registry.Items;

public class MiasmaEntityRenderer extends EntityRenderer<MiasmaEntity> {

    private static final ItemStack MIASMA_STACK = new ItemStack(Items.MIASMA_EFFECT);

    public MiasmaEntityRenderer(EntityRenderDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void render(MiasmaEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();
        matrices.scale(1, 1, 1);
        matrices.multiply(this.renderManager.getRotation());
        matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(180.0F));
        MinecraftClient.getInstance().getItemRenderer().renderItem(MIASMA_STACK, ModelTransformation.Mode.GROUND, light, OverlayTexture.DEFAULT_UV, matrices, vertexConsumers);
        matrices.pop();
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override
    public Identifier getTexture(MiasmaEntity entity) {
        return Asuna.id("textures/entity/miasma_effect.png");
    }
}
