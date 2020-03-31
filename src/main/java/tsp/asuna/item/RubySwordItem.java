package tsp.asuna.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class RubySwordItem extends SwordItem {
    public RubySwordItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        assert attacker != null;

        StatusEffectInstance fireproof = new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 600);
        attacker.addStatusEffect(fireproof);
        return super.postHit(stack, target, attacker);
    }
}
