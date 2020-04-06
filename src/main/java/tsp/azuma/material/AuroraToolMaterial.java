package tsp.azuma.material;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import tsp.azuma.registry.Items;

public class AuroraToolMaterial implements ToolMaterial {

    @Override
    public int getDurability() {
        return 1;
    }

    @Override
    public float getMiningSpeed() {
        return 10.0F;
    }

    @Override
    public float getAttackDamage() {
        return 5.0F;
    }

    @Override
    public int getMiningLevel() {
        return 5;
    }

    @Override
    public int getEnchantability() {
        return 20;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.AURORA_INGOT);
    }
}


