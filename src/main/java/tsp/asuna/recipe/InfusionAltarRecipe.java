package tsp.asuna.recipe;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import tsp.asuna.entities.InfusionAltarPedestalBlockEntity;

import java.util.ArrayList;
import java.util.List;

public class
InfusionAltarRecipe {

    private final Identifier id;
    private final RecipeTier tier;
    private final Item centerStack;
    private final int manaRequirement;
    private final Item[] firstRingIngredients;
    private final Item[] secondRingIngredients;
    private final Item[] thirdRingIngredients;
    private final ItemStack output;

    private InfusionAltarRecipe(Identifier id, RecipeTier tier, Item centerStack, int manaRequirement, Item[] firstRingIngredients, Item[] secondRingIngredients, Item[] thirdRingIngredients, ItemStack output) {
        this.id = id;
        this.tier = tier;
        this.centerStack = centerStack;
        this.manaRequirement = manaRequirement;
        this.firstRingIngredients = firstRingIngredients;
        this.secondRingIngredients = secondRingIngredients;
        this.thirdRingIngredients = thirdRingIngredients;
        this.output = output;
    }

    public RecipeTier getTier() {
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

    public boolean matches(AltarState altarState) {
        boolean first = firstRingMatches(altarState.getFirstRingPedestals());
        boolean second = tier == RecipeTier.SIMPLE || (tier == RecipeTier.MEDIUM || tier == RecipeTier.ADVANCED) && secondRingMatches(altarState.getSecondRingAltars());
        boolean third = tier != RecipeTier.ADVANCED || thirdRingMatches(altarState.getThirdRingAltars());

        return
                altarState.getCore().getHeldStack().getItem() == getCenterItem()
                && first
                && second
                && third;
    }

    public void takeItems(AltarState altarState) {
        takeRingItems(altarState.getFirstRingPedestals(), getFirstRingIngredients());

        if(getSecondRingIngredients() != null) {
            takeRingItems(altarState.getSecondRingAltars(), getSecondRingIngredients());
        }

        if(getThirdRingIngredients() != null) {
            takeRingItems(altarState.getThirdRingAltars(), getThirdRingIngredients());
        }
    }

    public void takeRingItems(List<InfusionAltarPedestalBlockEntity> pedestals, Item[] ingredients) {
        if(pedestals != null) {
            for (Item item : ingredients) {
                for (InfusionAltarPedestalBlockEntity be : pedestals) {
                    if (be.getHeldStack().getItem().equals(item)) {
                        be.clearItem();
                        break;
                    }
                }
            }
        }
    }

    public boolean firstRingMatches(List<InfusionAltarPedestalBlockEntity> firstRingPedestals) {
        List<Item> currentItems = new ArrayList<>();
        firstRingPedestals.forEach(pedestal -> currentItems.add(pedestal.getHeldStack().getItem()));

        for(Item ingredient : getFirstRingIngredients()) {
            if(!currentItems.contains(ingredient)) {
                return false;
            }

            currentItems.remove(ingredient);
        }

        return true;
    }

    public boolean secondRingMatches(List<InfusionAltarPedestalBlockEntity> secondRingPedestals) {
        if(secondRingPedestals == null) {
            return false;
        }

        if(getSecondRingIngredients() == null) {
            return false;
        }

        List<Item> currentStacks = new ArrayList<>();
        secondRingPedestals.forEach(pedestal -> currentStacks.add(pedestal.getHeldStack().getItem()));

        for(Item ingredient : getSecondRingIngredients()) {
            if(!currentStacks.contains(ingredient)) {
                return false;
            }
        }

        return true;
    }

    public boolean thirdRingMatches(List<InfusionAltarPedestalBlockEntity> thirdRingPedestals) {
        if(thirdRingPedestals == null) {
            return false;
        }

        if(getThirdRingIngredients() == null) {
            return false;
        }

        List<Item> currentStacks = new ArrayList<>();
        thirdRingPedestals.forEach(pedestal -> {
            currentStacks.add(pedestal.getHeldStack().getItem());
        });

        for(Item ingredient : getThirdRingIngredients()) {
            if(!currentStacks.contains(ingredient)) {
                return false;
            }
        }

        return true;
    }

    public static class Builder {

        private Identifier id;
        private RecipeTier tier;
        private Item centerStack;
        private int manaRequirement = 0;
        private Item[] firstRingIngredients;
        private Item[] secondRingIngredients;
        private Item[] thirdRingIngredients;
        private ItemStack output;

        public Builder(Identifier id, RecipeTier tier) {
            this.id = id;
            this.tier = tier;
        }

        public Builder centerStack(Item centerStack) {
            this.centerStack = centerStack;
            return this;
        }

        public Builder withManaRequirement(int manaRequirement) {
            this.manaRequirement = manaRequirement;
            return this;
        }

        public Builder withFirstRingOf(Item[] ingredients) {
            this.firstRingIngredients = ingredients;
            return this;
        }

        public Builder withSecondRingOf(Item[] ingredients) {
            if(tier == RecipeTier.SIMPLE) {
                throw new IllegalStateException("Second ring ingredients were specified in a recipe  (" + id.toString() + ") + with a tier of " + tier.name() + "!");
            } else {
                this.secondRingIngredients = ingredients;
            }

            return this;
        }

        public Builder withThirdRingOf(Item[] ingredients) {
            if(tier != RecipeTier.ADVANCED) {
                throw new IllegalStateException("Third ring ingredients were specified in a recipe  (" + id.toString() + ") + with a tier of " + tier.name() + "!");
            } else {
                this.thirdRingIngredients = ingredients;
            }

            return this;
        }

        public Builder withOutput(ItemStack output) {
            this.output = output;
            return this;
        }

        public InfusionAltarRecipe build() {
            return new InfusionAltarRecipe(id, tier, centerStack, manaRequirement, firstRingIngredients, secondRingIngredients, thirdRingIngredients, output);
        }
    }


    public enum RecipeTier {
        SIMPLE,
        MEDIUM,
        ADVANCED
    }
}
