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
import tsp.asuna.entities.ThoronEntity;
import tsp.asuna.registry.Items;

public class ThoronEntityRenderer extends EntityRenderer<ThoronEntity> {
    public ThoronEntityRenderer(EntityRenderDispatcher dispatcher) {
        super(dispatcher);
    }
    private static final ItemStack THORON_STACK = new ItemStack(Items.THORON_EFFECT);

    @Override
    public Identifier getTexture(ThoronEntity entity) {
        return Asuna.id("textures/item/laser.png");
    }

    @Override
    public void render(ThoronEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();
        matrices.scale(1, 1, 1);
        matrices.multiply(this.renderManager.getRotation());
        matrices.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(180));
        MinecraftClient.getInstance().getItemRenderer().renderItem(THORON_STACK, ModelTransformation.Mode.GROUND, light, OverlayTexture.DEFAULT_UV, matrices, vertexConsumers);
        matrices.pop();
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }


}
