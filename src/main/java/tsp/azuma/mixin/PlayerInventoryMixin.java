package tsp.azuma.mixin;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import tsp.azuma.api.ManaDurable;
import tsp.azuma.api.cca.ItemManaComponent;
import tsp.azuma.registry.Components;

import java.util.function.Consumer;

@Mixin(PlayerInventory.class)
public class PlayerInventoryMixin {

    // todo: passing 1 because I don't know how to access "i" from target loop.
    // May cause issues in the future, but I'm not sure what cases cause this.
    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;damage(ILnet/minecraft/entity/LivingEntity;Ljava/util/function/Consumer;)V"), method = "damageArmor")
    private void onDamageArmor(ItemStack itemStack, int amount, LivingEntity entity, Consumer<PlayerEntity> breakCallback) {
        if(itemStack.getItem() instanceof ManaDurable) {
            ItemManaComponent itemManaComponent = (ItemManaComponent) Components.MANA.get(itemStack);
            itemManaComponent.decrement(1);
        } else {
            itemStack.damage(amount, entity, (playerEntity -> {
                playerEntity.sendEquipmentBreakStatus(EquipmentSlot.fromTypeIndex(EquipmentSlot.Type.ARMOR, 1));
            }));
        }
    }
}
