package tsp.asuna.recipe;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class AltarRecipeBuilder {

    private Identifier id;
    private AltarRecipeTier tier;
    private Item centerStack;
    private int manaRequirement = 0;
    private Item[] firstRingIngredients;
    private Item[] secondRingIngredients;
    private Item[] thirdRingIngredients;
    private ItemStack output;

    public AltarRecipeBuilder(Identifier id, AltarRecipeTier tier) {
        this.id = id;
        this.tier = tier;
    }

    public AltarRecipeBuilder centerStack(Item centerStack) {
        this.centerStack = centerStack;
        return this;
    }

    public AltarRecipeBuilder withManaRequirement(int manaRequirement) {
        this.manaRequirement = manaRequirement;
        return this;
    }

    public AltarRecipeBuilder withFirstRingOf(Item[] ingredients) {
        this.firstRingIngredients = ingredients;
        return this;
    }

    public AltarRecipeBuilder withSecondRingOf(Item[] ingredients) {
        if(tier == AltarRecipeTier.SIMPLE) {
            throw new IllegalStateException("Second ring ingredients were specified in a recipe  (" + id.toString() + ") + with a tier of " + tier.name() + "!");
        } else {
            this.secondRingIngredients = ingredients;
        }

        return this;
    }

    public AltarRecipeBuilder withThirdRingOf(Item[] ingredients) {
        if(tier != AltarRecipeTier.ADVANCED) {
            throw new IllegalStateException("Third ring ingredients were specified in a recipe  (" + id.toString() + ") + with a tier of " + tier.name() + "!");
        } else {
            this.thirdRingIngredients = ingredients;
        }

        return this;
    }

    public AltarRecipeBuilder withOutput(ItemStack output) {
        this.output = output;
        return this;
    }

    public AltarRecipe build() {
        return new AltarRecipe(id, tier, centerStack, manaRequirement, firstRingIngredients, secondRingIngredients, thirdRingIngredients, output);
    }
}