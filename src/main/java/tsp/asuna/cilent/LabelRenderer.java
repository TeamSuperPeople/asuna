package tsp.asuna.cilent;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.Matrix4f;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3d;

@Environment(EnvType.CLIENT)
public class LabelRenderer {

    public static void renderLabelIfPresent(BlockEntity entity, String string, float yOffset, MatrixStack matrices, VertexConsumerProvider vertexConsumerProvider, int light) {
        double d = getSquaredDistanceToCamera(entity);

        if (d <= 4096.0D) {
            float renderHeight = 1.5f + yOffset;

            matrices.push();
            matrices.translate(0.5D, renderHeight, 0.5D);
            matrices.multiply(MinecraftClient.getInstance().getEntityRenderManager().getRotation());
            matrices.scale(-0.025F, -0.025F, 0.025F);
            Matrix4f matrix4f = matrices.peek().getModel();
            float g = MinecraftClient.getInstance().options.getTextBackgroundOpacity(0.25F);
            int k = (int)(g * 255.0F) << 24;

            TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
            float h = (float)(-textRenderer.getStringWidth(string) / 2);
            textRenderer.draw(string, h, (float) 0, 553648127, false, matrix4f, vertexConsumerProvider, false, k, light);
            textRenderer.draw(string, h, (float) 0, -1, false, matrix4f, vertexConsumerProvider, false, 0, light);

            matrices.pop();
        }
    }

    public static void renderLabelIfPresent(BlockEntity entity, String string, int color, float yOffset, MatrixStack matrices, VertexConsumerProvider vertexConsumerProvider, int light) {
        double d = getSquaredDistanceToCamera(entity);

        if (d <= 4096.0D) {
            float renderHeight = 1.5f + yOffset;

            matrices.push();
            matrices.translate(0.5D, renderHeight, 0.5D);
            matrices.multiply(MinecraftClient.getInstance().getEntityRenderManager().getRotation());
            matrices.scale(-0.025F, -0.025F, 0.025F);
            Matrix4f matrix4f = matrices.peek().getModel();
            float g = MinecraftClient.getInstance().options.getTextBackgroundOpacity(0.25F);
            int k = (int)(g * 255.0F) << 24;

            TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
            float h = (float)(-textRenderer.getStringWidth(string) / 2);
            textRenderer.draw(string, h, (float) 0, 553648127, false, matrix4f, vertexConsumerProvider, false, k, light); // black with bg
            textRenderer.draw(string, h, (float) 0, color, false, matrix4f, vertexConsumerProvider, false, 0, light); // colored text

            matrices.pop();
        }
    }

    public static double getSquaredDistanceToCamera(BlockEntity entity) {
        return MinecraftClient.getInstance().cameraEntity.getPos().squaredDistanceTo(new Vec3d(entity.getPos()));
    }
}
