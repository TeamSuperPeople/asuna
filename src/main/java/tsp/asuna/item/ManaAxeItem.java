package tsp.asuna.item;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import tsp.asuna.Asuna;
import tsp.asuna.api.cca.ItemManaComponent;
import tsp.asuna.api.ManaDurable;
import tsp.asuna.registry.Components;

public class ManaAxeItem extends AxeItem implements ManaDurable {

    private final int maxMana;

    public ManaAxeItem(ToolMaterial material, float attackDamage, int maxMana) {
        super(material, attackDamage, -3.0F, new Settings().maxDamage(-1).group(Asuna.ASUNA_SPELLS));
        this.maxMana = maxMana;
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        ItemManaComponent itemManaComponent = (ItemManaComponent) Components.MANA.get(stack);
        itemManaComponent.decrement();

        return true;
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        ItemManaComponent itemManaComponent = (ItemManaComponent) Components.MANA.get(stack);
        itemManaComponent.decrement();

        return true;
    }

    @Override
    public int getMaxMana() {
        return maxMana;
    }
}
