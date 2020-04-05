package tsp.asuna.recipe;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class AltarRecipe {

    private final Identifier id;
    private final AltarRecipeTier tier;
    private final Item centerStack;
    private final int manaRequirement;
    private final Item[] firstRingIngredients;
    private final Item[] secondRingIngredients;
    private final Item[] thirdRingIngredients;
    private final ItemStack output;

    public AltarRecipe(Identifier id, AltarRecipeTier tier, Item centerStack, int manaRequirement, Item[] firstRingIngredients, Item[] secondRingIngredients, Item[] thirdRingIngredients, ItemStack output) {
        this.id = id;
        this.tier = tier;
        this.centerStack = centerStack;
        this.manaRequirement = manaRequirement;
        this.firstRingIngredients = firstRingIngredients;
        this.secondRingIngredients = secondRingIngredients;
        this.thirdRingIngredients = thirdRingIngredients;
        this.output = output;
    }

    public AltarRecipeTier getTier() {
        return tier;
    }

    public Item getCenterItem() {
        return centerStack;
    }

    public int getManaRequirement() {
        return manaRequirement;
    }

    public Item[] getFirstRingIngredients() {
        return firstRingIngredients;
    }

    public Item[] getSecondRingIngredients() {
        return secondRingIngredients;
    }

    public Item[] getThirdRingIngredients() {
        return thirdRingIngredients;
    }

    public ItemStack getOutput() {
        return output;
    }

    public Identifier getId() {
        return id;
    }
}
