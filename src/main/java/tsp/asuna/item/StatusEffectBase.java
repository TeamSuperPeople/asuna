package tsp.asuna.item;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;

public class StatusEffectBase extends StatusEffect {
    protected StatusEffectBase(StatusEffectType type, int color) {
        super(type, color);
    }
}
