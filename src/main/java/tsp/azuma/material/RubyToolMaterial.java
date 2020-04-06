package tsp.azuma.material;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import tsp.azuma.registry.Items;

public class RubyToolMaterial implements ToolMaterial {

    @Override
    public int getDurability() {
        return 750;
    }

    @Override
    public float getMiningSpeed() {
        return 7F;
    }

    @Override
    public float getAttackDamage() {
        return 4;
    }

    @Override
    public int getMiningLevel() {
        return 3;
    }

    @Override
    public int getEnchantability() {
        return 20;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.RUBY);
    }
}
