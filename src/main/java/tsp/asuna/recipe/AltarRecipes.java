package tsp.asuna.recipe;

import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class AltarRecipes {

    private static final Map<Identifier, InfusionAltarRecipe> recipes = new HashMap<>();

    public static void registerRecipe(InfusionAltarRecipe recipe) {
        if(recipes.containsKey(recipe.getId())) {

        } else {
            recipes.put(recipe.getId(), recipe);
        }
    }

    public static Map<Identifier, InfusionAltarRecipe> getRecipes() {
        return recipes;
    }
}
