package tsp.asuna;

import nerdhub.cardinal.components.api.event.ItemComponentCallback;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import tsp.asuna.api.cca.ItemManaComponent;
import tsp.asuna.api.ManaDurable;
import tsp.asuna.recipe.AltarRecipes;
import tsp.asuna.recipe.InfusionAltarRecipe;
import tsp.asuna.registry.Blocks;
import tsp.asuna.registry.Components;
import tsp.asuna.registry.Items;

import java.util.Random;

public class Asuna implements ModInitializer {

    public static final ItemGroup ASUNA_SPELLS = FabricItemGroupBuilder.build(id("spells"), () -> new ItemStack(Items.MIASMA));
    public static final String MODID = "asuna";

    @Override
    public void onInitialize() {
        RegistryEntryAddedCallback.event(Registry.ITEM).register((i, identifier, item) -> {
            if (item instanceof ManaDurable) {
                ItemComponentCallback.event(item).register((stack, components) -> components.put(Components.MANA, new ItemManaComponent(((ManaDurable) item).getMaxMana())));
            }
        });

        Components.init();
        Items.init();
        Blocks.init();

        AltarRecipes.registerRecipe(
                new InfusionAltarRecipe.Builder(id("diamond"), InfusionAltarRecipe.RecipeTier.SIMPLE)
                        .centerStack(net.minecraft.item.Items.DIAMOND_ORE)
                        .withFirstRingOf(new Item[]{net.minecraft.item.Items.COAL})
                        .withOutput(new ItemStack(net.minecraft.item.Items.DIAMOND))
                        .build()
        );

        AltarRecipes.registerRecipe(
                new InfusionAltarRecipe.Builder(id("blaze"), InfusionAltarRecipe.RecipeTier.ADVANCED)
                        .centerStack(net.minecraft.item.Items.DIAMOND_ORE)
                        .withFirstRingOf(new Item[]{net.minecraft.item.Items.COAL})
                        .withSecondRingOf(new Item[]{net.minecraft.item.Items.MAGMA_BLOCK})
                        .withThirdRingOf(new Item[]{net.minecraft.item.Items.BLAZE_ROD})
                        .withOutput(new ItemStack(net.minecraft.item.Items.BLAZE_POWDER))
                        .build()
        );

        AltarRecipes.registerRecipe(
                new InfusionAltarRecipe.Builder(id("emerald"), InfusionAltarRecipe.RecipeTier.MEDIUM)
                        .centerStack(net.minecraft.item.Items.DIAMOND_ORE)
                        .withFirstRingOf(new Item[]{net.minecraft.item.Items.COAL})
                        .withSecondRingOf(new Item[]{net.minecraft.item.Items.LAPIS_BLOCK})
                        .withOutput(new ItemStack(net.minecraft.item.Items.EMERALD))
                        .build()
        );

        AltarRecipes.registerRecipe(
                new InfusionAltarRecipe.Builder(id("thunderband"), InfusionAltarRecipe.RecipeTier.SIMPLE)
                        .centerStack(net.minecraft.item.Items.GOLDEN_SWORD)
                        .withFirstRingOf(new Item[]{Items.THUNDERING_SPIRIT,Items.THUNDERING_SPIRIT,Items.THUNDERING_SPIRIT,Items.THUNDERING_SPIRIT})
                        .withOutput(new ItemStack(Items.THUNDERBAND))
                        .build()
        );



        Registry.BIOME.forEach(this::handleBiome);
        RegistryEntryAddedCallback.event(Registry.BIOME).register((i, identifier, biome) -> handleBiome(biome));

        }

    private void handleBiome(Biome biome) {
        if (biome.getCategory() != Biome.Category.NETHER && biome.getCategory() != Biome.Category.THEEND) {
            biome.addFeature(
                    GenerationStep.Feature.UNDERGROUND_ORES,
                    Feature.ORE.configure(
                            new OreFeatureConfig(
                                    OreFeatureConfig.Target.NATURAL_STONE,
                                    Blocks.RUBY_ORE.getDefaultState(),
                                  8//Ore vein size
                            )).createDecoratedFeature(
                            Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(
                                            2, //Number of veins per chunk
                                            0, //Bottom Offset
                                            2, //Min y level
                                            16 //Max y level
                                    )
                            )
                    )
            );
        }
    }
    public static Identifier id(String path) {
        return new Identifier(MODID, path);
    }
}
