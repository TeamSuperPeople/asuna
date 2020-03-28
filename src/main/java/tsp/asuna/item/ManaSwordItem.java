package tsp.asuna.item;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import tsp.asuna.Asuna;
import tsp.asuna.api.ItemManaComponent;
import tsp.asuna.api.ManaCharged;
import tsp.asuna.registry.Components;

public class ManaSwordItem extends SwordItem implements ManaCharged {

    private final int maxMana;

    public ManaSwordItem(ToolMaterial material, int attackDamage, int maxMana) {
        super(material, attackDamage, -2.4f, new Settings().maxDamage(-1).group(Asuna.ASUNA_SPELLS));
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
        itemManaComponent.decrement(2);

        return true;
    }

    @Override
    public int getMaxMana() {
        return maxMana;
    }
}
