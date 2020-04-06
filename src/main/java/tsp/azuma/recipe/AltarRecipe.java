package tsp.azuma.recipe;

import com.google.gson.annotations.SerializedName;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;

public class AltarRecipe {

    @SerializedName("center")
    private final String centerItem;

    @SerializedName("mana_requirement")
    private final int manaRequirement;

    @SerializedName("first_tier_ingredients")
    private final List<String> firstRingIngredients;

    @SerializedName("second_tier_ingredients")
    private final List<String> secondRingIngredients;

    @SerializedName("third_tier_ingredients")
    private final List<String> thirdRingIngredients;

    @SerializedName("output")
    private final String outputItem;

    public AltarRecipe(String centerItem, int manaRequirement, List<String> firstRingIngredients, List<String> secondRingIngredients, List<String> thirdRingIngredients, String outputItem) {
        this.centerItem = centerItem;
        this.manaRequirement = manaRequirement;
        this.firstRingIngredients = firstRingIngredients;
        this.secondRingIngredients = secondRingIngredients;
        this.thirdRingIngredients = thirdRingIngredients;
        this.outputItem = outputItem;
    }

    public Item getCenterItem() {
        return Registry.ITEM.get(new Identifier(centerItem));
    }

    public int getManaRequirement() {
        return manaRequirement;
    }

    public List<Item> getFirstRingIngredients() {
        if(firstRingIngredients == null) {
            return null;
        }

        ArrayList<Item> ingredients = new ArrayList<>();

        for(String s : firstRingIngredients) {
            ingredients.add(Registry.ITEM.get(new Identifier(s)));
        }

        return ingredients;
    }

    public List<Item> getSecondRingIngredients() {
        if(secondRingIngredients == null) {
            return null;
        }

        ArrayList<Item> ingredients = new ArrayList<>();

        for(String s : secondRingIngredients) {
            ingredients.add(Registry.ITEM.get(new Identifier(s)));
        }

        return ingredients;
    }

    public List<Item> getThirdRingIngredients() {
        if(thirdRingIngredients == null) {
            return null;
        }

        ArrayList<Item> ingredients = new ArrayList<>();

        for(String s : thirdRingIngredients) {
            ingredients.add(Registry.ITEM.get(new Identifier(s)));
        }

        return ingredients;
    }

    public ItemStack getOutput() {
        return new ItemStack(Registry.ITEM.get(new Identifier(outputItem)));
    }
}
