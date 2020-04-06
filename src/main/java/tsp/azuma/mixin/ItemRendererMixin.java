package tsp.azuma.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tsp.azuma.api.cca.ItemManaComponent;
import tsp.azuma.api.ManaDurable;
import tsp.azuma.registry.Components;

@Environment(EnvType.CLIENT)
@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {

    @Shadow protected abstract void renderGuiQuad(BufferBuilder buffer, int x, int y, int width, int height, int red, int green, int blue, int alpha);

    @Inject(
            method = "renderGuiItemOverlay(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/item/ItemStack;IILjava/lang/String;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemStack;isDamaged()Z"),
            cancellable = true)
    private void go(TextRenderer fontRenderer, ItemStack stack, int x, int y, String amountText, CallbackInfo ci) {
        if(stack.getItem() instanceof ManaDurable) {
            renderCustomDurability(stack, x, y);
            finishMethod(stack, x, y);
            ci.cancel();
        }
    }

    @Unique
    private void renderCustomDurability(ItemStack stack, int x, int y) {
        ItemManaComponent manaComponent = (ItemManaComponent) Components.MANA.get(stack);

        if(!manaComponent.isDamaged()) {
            return;
        }

        RenderSystem.disableDepthTest();
        RenderSystem.disableTexture();
        RenderSystem.disableAlphaTest();
        RenderSystem.disableBlend();

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();

        float stackManaDamage = (float) manaComponent.getMaxMana() - (float) manaComponent.getMana();
        float stackMaxMana = (float) manaComponent.getMaxMana();
        float normalizedDamage = Math.max(0.0F, (stackMaxMana - stackManaDamage) / stackMaxMana);
        int colorWidth = Math.round(13.0F - stackManaDamage * 13.0F / stackMaxMana);
        int barColor = MathHelper.hsvToRgb(.47f + (.07f * normalizedDamage), 1.0F, 1.0F); // .47 -> .54

        this.renderGuiQuad(bufferBuilder, x + 2, y + 13, 13, 2, 0, 0, 0, 255); // background black bar
        this.renderGuiQuad(bufferBuilder, x + 2, y + 13, colorWidth, 1, barColor >> 16 & 255, barColor >> 8 & 255, barColor & 255, 255); // color

        RenderSystem.enableBlend();
        RenderSystem.enableAlphaTest();
        RenderSystem.enableTexture();
        RenderSystem.enableDepthTest();
    }

    @Unique
    private void finishMethod(ItemStack stack, int x, int y) {
        ClientPlayerEntity clientPlayerEntity = MinecraftClient.getInstance().player;
        float k = clientPlayerEntity == null ? 0.0F : clientPlayerEntity.getItemCooldownManager().getCooldownProgress(stack.getItem(), MinecraftClient.getInstance().getTickDelta());
        if (k > 0.0F) {
            RenderSystem.disableDepthTest();
            RenderSystem.disableTexture();
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            Tessellator tessellator2 = Tessellator.getInstance();
            BufferBuilder bufferBuilder2 = tessellator2.getBuffer();
            this.renderGuiQuad(bufferBuilder2, x, y + MathHelper.floor(16.0F * (1.0F - k)), 16, MathHelper.ceil(16.0F * k), 255, 255, 255, 127);
            RenderSystem.enableTexture();
            RenderSystem.enableDepthTest();
        }
    }
}
