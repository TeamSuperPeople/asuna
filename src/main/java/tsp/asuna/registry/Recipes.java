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
                new AltarRecipeBuilder(Asuna.id("thunderband"), AltarRecipeTier.SIMPLE)
                        .centerStack(net.minecraft.item.Items.GOLDEN_SWORD)
                        .withFirstRingOf(new Item[]{Items.THUNDERING_SPIRIT,Items.THUNDERING_SPIRIT,Items.THUNDERING_SPIRIT,Items.THUNDERING_SPIRIT})
                        .withOutput(new ItemStack(Items.THUNDERBAND))
                        .withManaRequirement(64)
                        .build()
        );

        AltarRecipeRegistry.registerRecipe(
                new AltarRecipeBuilder(Asuna.id("bolganone"), AltarRecipeTier.SIMPLE)
                        .centerStack(net.minecraft.item.Items.BOOK)
                        .withFirstRingOf(new Item[]{Items.BLAZING_CORE,Items.BLAZING_CORE,Items.BLAZING_CORE,Items.BLAZING_CORE,})
                        .withOutput(new ItemStack(Items.BOLGANONE))
                        .withManaRequirement(64)
                        .build()
        );

        AltarRecipeRegistry.registerRecipe(
                new AltarRecipeBuilder(Asuna.id("manadiamond"), AltarRecipeTier.SIMPLE)
                        .centerStack(net.minecraft.item.Items.DIAMOND)
                        .withFirstRingOf(new Item[]{Items.MANA_SHARD})
                        .withOutput(new ItemStack(Items.MANA_DIAMOND))
                        .withManaRequirement(16)
                        .build()
        );

        AltarRecipeRegistry.registerRecipe(
                new AltarRecipeBuilder(Asuna.id("manairon"), AltarRecipeTier.SIMPLE)
                        .centerStack(net.minecraft.item.Items.IRON_INGOT)
                        .withFirstRingOf(new Item[]{Items.MANA_SHARD})
                        .withOutput(new ItemStack(Items.MANA_IRON))
                        .withManaRequirement(16)
                        .build()
        );

        AltarRecipeRegistry.registerRecipe(
                new AltarRecipeBuilder(Asuna.id("miasma"), AltarRecipeTier.MEDIUM)
                        .centerStack(net.minecraft.item.Items.BOOK)
                        .withFirstRingOf(new Item[]{net.minecraft.item.Items.FERMENTED_SPIDER_EYE,net.minecraft.item.Items.FERMENTED_SPIDER_EYE,net.minecraft.item.Items.FERMENTED_SPIDER_EYE,net.minecraft.item.Items.FERMENTED_SPIDER_EYE})
                        .withSecondRingOf(new Item[]{net.minecraft.item.Items.POISONOUS_POTATO})
                        .withOutput(new ItemStack(Items.MIASMA))
                        .withManaRequirement(64)
                        .build()
        );

        AltarRecipeRegistry.registerRecipe(
                new AltarRecipeBuilder(Asuna.id("thoron"), AltarRecipeTier.MEDIUM)
                        .centerStack(net.minecraft.item.Items.BOOK)
                        .withFirstRingOf(new Item[]{Items.THUNDERING_SPIRIT,Items.THUNDERING_SPIRIT,Items.THUNDERING_SPIRIT,Items.THUNDERING_SPIRIT})
                        .withSecondRingOf(new Item[]{net.minecraft.item.Items.PRISMARINE_CRYSTALS,net.minecraft.item.Items.PRISMARINE_CRYSTALS,net.minecraft.item.Items.PRISMARINE_CRYSTALS,net.minecraft.item.Items.PRISMARINE_CRYSTALS})
                        .withOutput(new ItemStack(Items.THORON))
                        .withManaRequirement(64)
                        .build()
        );

        AltarRecipeRegistry.registerRecipe(
                new AltarRecipeBuilder(Asuna.id("thunder"), AltarRecipeTier.SIMPLE)
                        .centerStack(net.minecraft.item.Items.BOOK)
                        .withFirstRingOf(new Item[]{Items.THUNDERING_SPIRIT,Items.THUNDERING_SPIRIT,Items.THUNDERING_SPIRIT,Items.THUNDERING_SPIRIT})
                        .withOutput(new ItemStack(Items.THUNDER))
                        .withManaRequirement(64)
                        .build()
        );

        AltarRecipeRegistry.registerRecipe(
                new AltarRecipeBuilder(Asuna.id("regrowth"), AltarRecipeTier.SIMPLE)
                        .centerStack(net.minecraft.item.Items.BOOK)
                        .withFirstRingOf(new Item[]{Items.TERRA_ORB,Items.TERRA_ORB,Items.TERRA_ORB,Items.TERRA_ORB})
                        .withOutput(new ItemStack(Items.REGROWTH))
                        .withManaRequirement(64)
                        .build()
        );
                 AltarRecipeRegistry.registerRecipe(
                new AltarRecipeBuilder(Asuna.id("heal"), AltarRecipeTier.SIMPLE)
                        .centerStack(net.minecraft.item.Items.BOOK)
                        .withFirstRingOf(new Item[]{net.minecraft.item.Items.GLISTERING_MELON_SLICE,net.minecraft.item.Items.GLISTERING_MELON_SLICE,net.minecraft.item.Items.GLISTERING_MELON_SLICE,net.minecraft.item.Items.GLISTERING_MELON_SLICE})
                        .withOutput(new ItemStack(Items.HEAL))
                        .withManaRequirement(64)
                        .build()
         );

        AltarRecipeRegistry.registerRecipe(
                new AltarRecipeBuilder(Asuna.id("nosferatu"), AltarRecipeTier.SIMPLE)
                        .centerStack(net.minecraft.item.Items.BOOK)
                        .withFirstRingOf(new Item[]{net.minecraft.item.Items.GLISTERING_MELON_SLICE,net.minecraft.item.Items.GLISTERING_MELON_SLICE,net.minecraft.item.Items.IRON_SWORD,net.minecraft.item.Items.IRON_SWORD})
                        .withOutput(new ItemStack(Items.NOSFERATU))
                        .withManaRequirement(64)
                        .build()
        );

        AltarRecipeRegistry.registerRecipe(
                new AltarRecipeBuilder(Asuna.id("blazing_core"), AltarRecipeTier.MEDIUM)
                        .centerStack(net.minecraft.item.Items.ENDER_PEARL)
                        .withFirstRingOf(new Item[]{Items.MANA_SHARD,Items.MANA_SHARD,Items.MANA_SHARD,Items.MANA_SHARD})
                        .withSecondRingOf(new Item[]{net.minecraft.item.Items.LAVA_BUCKET,net.minecraft.item.Items.BLAZE_POWDER,net.minecraft.item.Items.MAGMA_CREAM,net.minecraft.item.Items.NETHER_BRICK})
                        .withOutput(new ItemStack(Items.BLAZING_CORE))
                        .withManaRequirement(32)
                        .build()
        );

        AltarRecipeRegistry.registerRecipe(
                new AltarRecipeBuilder(Asuna.id("oceanic_pearl"), AltarRecipeTier.MEDIUM)
                        .centerStack(net.minecraft.item.Items.ENDER_PEARL)
                        .withFirstRingOf(new Item[]{Items.MANA_SHARD,Items.MANA_SHARD,Items.MANA_SHARD,Items.MANA_SHARD})
                        .withSecondRingOf(new Item[]{net.minecraft.item.Items.WATER_BUCKET,net.minecraft.item.Items.PRISMARINE_SHARD,net.minecraft.item.Items.SALMON,net.minecraft.item.Items.NAUTILUS_SHELL})
                        .withOutput(new ItemStack(Items.OCEANIC_PEARL))
                        .withManaRequirement(32)
                        .build()
        );


        AltarRecipeRegistry.registerRecipe(
                new AltarRecipeBuilder(Asuna.id("thundering_spirit"), AltarRecipeTier.MEDIUM)
                        .centerStack(net.minecraft.item.Items.ENDER_PEARL)
                        .withFirstRingOf(new Item[]{Items.MANA_SHARD,Items.MANA_SHARD,Items.MANA_SHARD,Items.MANA_SHARD})
                        .withSecondRingOf(new Item[]{net.minecraft.item.Items.IRON_BARS,net.minecraft.item.Items.IRON_INGOT,net.minecraft.item.Items.IRON_BLOCK,net.minecraft.item.Items.IRON_NUGGET})
                        .withOutput(new ItemStack(Items.THUNDERING_SPIRIT))
                        .withManaRequirement(32)
                        .build()
        );


        AltarRecipeRegistry.registerRecipe(
                new AltarRecipeBuilder(Asuna.id("terra_orb"), AltarRecipeTier.MEDIUM)
                        .centerStack(net.minecraft.item.Items.ENDER_PEARL)
                        .withFirstRingOf(new Item[]{Items.MANA_SHARD,Items.MANA_SHARD,Items.MANA_SHARD,Items.MANA_SHARD})
                        .withSecondRingOf(new Item[]{net.minecraft.item.Items.GRASS,net.minecraft.item.Items.OAK_SAPLING,net.minecraft.item.Items.WHEAT_SEEDS,net.minecraft.item.Items.DIRT})
                        .withOutput(new ItemStack(Items.TERRA_ORB))
                        .withManaRequirement(32)
                        .build()
        );









    }




    private Recipes() {
        // NO-OP
    }
}
