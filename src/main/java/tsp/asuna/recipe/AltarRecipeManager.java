package tsp.asuna.recipe;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.resource.JsonDataLoader;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class AltarRecipeManager extends JsonDataLoader {

    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();
    private static final Logger LOGGER = LogManager.getLogger();
    private Map<Identifier, AltarRecipe> recipes = ImmutableMap.of();

    public AltarRecipeManager() {
        super(GSON, "altar_recipes");
    }

    @Override
    protected void apply(Map<Identifier, JsonObject> map, ResourceManager resourceManager, Profiler profiler) {
        Map<Identifier, AltarRecipe> recipeMap = Maps.newHashMap();

        for (Map.Entry<Identifier, JsonObject> entry : map.entrySet()) {
            Identifier identifier = entry.getKey();

            try {
                AltarRecipe recipe = GSON.fromJson(entry.getValue(), AltarRecipe.class);
                recipeMap.put(entry.getKey(), recipe);
            } catch (IllegalArgumentException | JsonParseException exception) {
                LOGGER.error("Parsing error loading recipe {}", identifier, exception);
            }
        }

        recipes = recipeMap;
        LOGGER.info("Loaded {} recipes", recipeMap.size());
    }

    public Map<Identifier, AltarRecipe> getRecipes() {
        return recipes;
    }
}