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

        AltarRecipeRegistry.registerRecipe(
                new AltarRecipeBuilder(Asuna.id("bolganone"), AltarRecipeTier.SIMPLE)
                        .centerStack(net.minecraft.item.Items.BOOK)
                        .withFirstRingOf(new Item[]{Items.BLAZING_CORE,Items.BLAZING_CORE,Items.BLAZING_CORE,Items.BLAZING_CORE,})
                        .withOutput(new ItemStack(Items.BOLGANONE))
                        .build()
        );

        AltarRecipeRegistry.registerRecipe(
                new AltarRecipeBuilder(Asuna.id("manadiamond"), AltarRecipeTier.SIMPLE)
                        .centerStack(net.minecraft.item.Items.DIAMOND)
                        .withFirstRingOf(new Item[]{Items.MANA_SHARD})
                        .withOutput(new ItemStack(Items.MANA_DIAMOND))
                        .build()
        );

        AltarRecipeRegistry.registerRecipe(
                new AltarRecipeBuilder(Asuna.id("manairon"), AltarRecipeTier.SIMPLE)
                        .centerStack(net.minecraft.item.Items.IRON_INGOT)
                        .withFirstRingOf(new Item[]{Items.MANA_SHARD})
                        .withOutput(new ItemStack(Items.MANA_IRON))
                        .build()
        );

        AltarRecipeRegistry.registerRecipe(
                new AltarRecipeBuilder(Asuna.id("miasma"), AltarRecipeTier.MEDIUM)
                        .centerStack(net.minecraft.item.Items.BOOK)
                        .withFirstRingOf(new Item[]{net.minecraft.item.Items.FERMENTED_SPIDER_EYE,net.minecraft.item.Items.FERMENTED_SPIDER_EYE,net.minecraft.item.Items.FERMENTED_SPIDER_EYE,net.minecraft.item.Items.FERMENTED_SPIDER_EYE})
                        .withSecondRingOf(new Item[]{net.minecraft.item.Items.POISONOUS_POTATO})
                        .withOutput(new ItemStack(Items.MIASMA))
                        .build()
        );

        AltarRecipeRegistry.registerRecipe(
                new AltarRecipeBuilder(Asuna.id("thoron"), AltarRecipeTier.MEDIUM)
                        .centerStack(net.minecraft.item.Items.BOOK)
                        .withFirstRingOf(new Item[]{Items.THUNDERING_SPIRIT,Items.THUNDERING_SPIRIT,Items.THUNDERING_SPIRIT,Items.THUNDERING_SPIRIT})
                        .withSecondRingOf(new Item[]{net.minecraft.item.Items.PRISMARINE_CRYSTALS,net.minecraft.item.Items.PRISMARINE_CRYSTALS,net.minecraft.item.Items.PRISMARINE_CRYSTALS,net.minecraft.item.Items.PRISMARINE_CRYSTALS})
                        .withOutput(new ItemStack(Items.THORON))
                        .build()
        );

        AltarRecipeRegistry.registerRecipe(
                new AltarRecipeBuilder(Asuna.id("regrowth"), AltarRecipeTier.SIMPLE)
                        .centerStack(net.minecraft.item.Items.BOOK)
                        .withFirstRingOf(new Item[]{Items.THUNDERING_SPIRIT,Items.THUNDERING_SPIRIT,Items.THUNDERING_SPIRIT,Items.THUNDERING_SPIRIT})
                        .withOutput(new ItemStack(Items.THUNDER))
                        .build()
        );

        AltarRecipeRegistry.registerRecipe(
                new AltarRecipeBuilder(Asuna.id("regrowth"), AltarRecipeTier.SIMPLE)
                        .centerStack(net.minecraft.item.Items.BOOK)
                        .withFirstRingOf(new Item[]{net.minecraft.item.Items.WHEAT_SEEDS,net.minecraft.item.Items.GRASS,net.minecraft.item.Items.OAK_SAPLING,net.minecraft.item.Items.GRASS})
                        .withOutput(new ItemStack(Items.REGROWTH))
                        .build()
        );
                 AltarRecipeRegistry.registerRecipe(
                new AltarRecipeBuilder(Asuna.id("heal"), AltarRecipeTier.SIMPLE)
                        .centerStack(net.minecraft.item.Items.BOOK)
                        .withFirstRingOf(new Item[]{net.minecraft.item.Items.GLISTERING_MELON_SLICE,net.minecraft.item.Items.GLISTERING_MELON_SLICE,net.minecraft.item.Items.GLISTERING_MELON_SLICE,net.minecraft.item.Items.GLISTERING_MELON_SLICE})
                        .withOutput(new ItemStack(Items.HEAL))
                        .build()
         );

        AltarRecipeRegistry.registerRecipe(
                new AltarRecipeBuilder(Asuna.id("nosferatu"), AltarRecipeTier.SIMPLE)
                        .centerStack(net.minecraft.item.Items.BOOK)
                        .withFirstRingOf(new Item[]{net.minecraft.item.Items.GLISTERING_MELON_SLICE,net.minecraft.item.Items.GLISTERING_MELON_SLICE,net.minecraft.item.Items.IRON_SWORD,net.minecraft.item.Items.IRON_SWORD})
                        .withOutput(new ItemStack(Items.NOSFERATU))
                        .build()
        );




    }




    private Recipes() {
        // NO-OP
    }
}
