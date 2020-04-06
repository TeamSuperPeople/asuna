package tsp.azuma.item;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import tsp.azuma.Azuma;
import tsp.azuma.api.cca.ItemManaComponent;
import tsp.azuma.api.ManaDurable;
import tsp.azuma.registry.Components;

public class ManaShovelItem extends ShovelItem implements ManaDurable {

    private final int maxMana;

    public ManaShovelItem(ToolMaterial material, float attackDamage, int maxMana) {
        super(material, attackDamage, -3.0F, new Settings().maxDamage(-1).group(Azuma.GROUP));
        this.maxMana = maxMana;
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        ItemManaComponent itemManaComponent = (ItemManaComponent) Components.MANA.get(stack);
        itemManaComponent.decrement(2);

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
