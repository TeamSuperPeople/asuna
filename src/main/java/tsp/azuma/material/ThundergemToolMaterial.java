package tsp.azuma.material;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class ThundergemToolMaterial implements ToolMaterial {

        @Override
        public int getDurability() {
            return 1000;
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
            return 0;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.EMPTY;
        }
    }


