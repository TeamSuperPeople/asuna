package tsp.azuma.cilent.renderer;

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
import tsp.azuma.Azuma;
import tsp.azuma.entity.MiasmaEntity;
import tsp.azuma.registry.Items;

public class MiasmaEntityRenderer extends EntityRenderer<MiasmaEntity> {
    float angle = 180;
    private static final ItemStack MIASMA_STACK = new ItemStack(Items.MIASMA_EFFECT);

    public MiasmaEntityRenderer(EntityRenderDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void render(MiasmaEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {

        if (angle < 360) {
            angle = angle + 2;
        }
        else if (angle >= 360) {
            angle = 0;
        }



        matrices.push();
        matrices.scale(1, 1, 1);
        matrices.multiply(this.renderManager.getRotation());
        matrices.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(angle));
        MinecraftClient.getInstance().getItemRenderer().renderItem(MIASMA_STACK, ModelTransformation.Mode.GROUND, light, OverlayTexture.DEFAULT_UV, matrices, vertexConsumers);
        matrices.pop();
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override
    public Identifier getTexture(MiasmaEntity entity) {
        return Azuma.id("textures/entity/miasma_effect.png");
    }
}
