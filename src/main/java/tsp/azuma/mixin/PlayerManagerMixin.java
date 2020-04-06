package tsp.azuma.mixin;

import net.minecraft.item.ItemStack;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tsp.azuma.registry.Components;
import vazkii.patchouli.common.base.Patchouli;
import vazkii.patchouli.common.item.PatchouliItems;

@Mixin(PlayerManager.class)
public class PlayerManagerMixin {

    @Inject(at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/server/network/ServerPlayNetworkHandler;sendPacket(Lnet/minecraft/network/Packet;)V",
            ordinal = 0
    ), method = "onPlayerConnect")
    private void onConnect(ClientConnection connection, ServerPlayerEntity player, CallbackInfo ci) {
        if(!Components.HAS_JOINED.get(player).hasJoined()) {
            Components.HAS_JOINED.get(player).setHasJoined(true);

            ItemStack stack = new ItemStack(PatchouliItems.book);
            stack.getOrCreateTag().putString("patchouli:book", "azuma:guidebook");

            player.giveItemStack(stack);
        }
    }
}
