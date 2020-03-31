package tsp.asuna.item;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RubyShovelItem extends ShovelItem {

    public RubyShovelItem(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        assert miner != null;

        StatusEffectInstance fireproof = new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 600);
        miner.addStatusEffect(fireproof);
        return super.postMine(stack, world, state, pos, miner);
    }
}
