package tsp.asuna.material;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import tsp.asuna.registry.Items;

public class ManaIronToolMaterial implements ToolMaterial {

    @Override
    public int getDurability() {
        return 1;
    }

    @Override
    public float getMiningSpeed() {
        return 6.0F;
    }

    @Override
    public float getAttackDamage() {
        return 2.0F;
    }

    @Override
    public int getMiningLevel() {
        return 2;
    }

    @Override
    public int getEnchantability() {
        return 14;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.MANA_IRON);
    }
}
