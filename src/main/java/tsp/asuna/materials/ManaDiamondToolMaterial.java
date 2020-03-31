package tsp.asuna.materials;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import tsp.asuna.registry.Items;

import javax.tools.Tool;

public class ManaDiamondToolMaterial implements ToolMaterial {

    @Override
    public int getDurability() {
        return 1;
    }

    @Override
    public float getMiningSpeed() {
        return 8.0F;
    }

    @Override
    public float getAttackDamage() {
        return 3.0F;
    }

    @Override
    public int getMiningLevel() {
        return 3;
    }

    @Override
    public int getEnchantability() {
        return 14;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.MANA_DIAMOND);
    }
}
