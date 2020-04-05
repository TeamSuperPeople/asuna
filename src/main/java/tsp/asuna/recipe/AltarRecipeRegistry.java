package tsp.asuna.recipe;

import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class AltarRecipeRegistry {

    private static final Map<Identifier, AltarRecipe> recipes = new HashMap<>();

    public static void registerRecipe(AltarRecipe recipe) {
        if(recipes.containsKey(recipe.getId())) {

        } else {
            recipes.put(recipe.getId(), recipe);
        }
    }

    public static Map<Identifier, AltarRecipe> getRecipes() {
        return recipes;
    }
}
