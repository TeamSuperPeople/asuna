package tsp.asuna.registry;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import tsp.asuna.Asuna;
import tsp.asuna.recipe.AltarRecipeBuilder;
import tsp.asuna.recipe.AltarRecipeRegistry;
import tsp.asuna.recipe.AltarRecipeTier;

public class Recipes {
    
    public static void init() {
        AltarRecipeRegistry.registerRecipe(
                    new AltarRecipeBuilder(Asuna.id("diamond"), AltarRecipeTier.SIMPLE)
                        .centerStack(net.minecraft.item.Items.DIAMOND_ORE)
                        .withFirstRingOf(new Item[]{net.minecraft.item.Items.COAL})
                        .withOutput(new ItemStack(net.minecraft.item.Items.DIAMOND))
                        .build()
        );

        AltarRecipeRegistry.registerRecipe(
                new AltarRecipeBuilder(Asuna.id("blaze"), AltarRecipeTier.ADVANCED)
                        .centerStack(net.minecraft.item.Items.DIAMOND_ORE)
                        .withFirstRingOf(new Item[]{net.minecraft.item.Items.COAL})
                        .withSecondRingOf(new Item[]{net.minecraft.item.Items.MAGMA_BLOCK})
                        .withThirdRingOf(new Item[]{net.minecraft.item.Items.BLAZE_ROD})
                        .withOutput(new ItemStack(net.minecraft.item.Items.BLAZE_POWDER))
                        .build()
        );

        AltarRecipeRegistry.registerRecipe(
                new AltarRecipeBuilder(Asuna.id("emerald"), AltarRecipeTier.MEDIUM)
                        .centerStack(net.minecraft.item.Items.DIAMOND_ORE)
                        .withFirstRingOf(new Item[]{net.minecraft.item.Items.COAL})
                        .withSecondRingOf(new Item[]{net.minecraft.item.Items.LAPIS_BLOCK})
                        .withOutput(new ItemStack(net.minecraft.item.Items.EMERALD))
                        .build()
        );

        AltarRecipeRegistry.registerRecipe(
                new AltarRecipeBuilder(Asuna.id("thunderband"), AltarRecipeTier.SIMPLE)
                        .centerStack(net.minecraft.item.Items.GOLDEN_SWORD)
                        .withFirstRingOf(new Item[]{Items.THUNDERING_SPIRIT,Items.THUNDERING_SPIRIT,Items.THUNDERING_SPIRIT,Items.THUNDERING_SPIRIT})
                        .withOutput(new ItemStack(Items.THUNDERBAND))
                        .build()
        );
    }

    private Recipes() {
        // NO-OP
    }
}
