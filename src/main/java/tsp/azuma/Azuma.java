package tsp.azuma;

import nerdhub.cardinal.components.api.event.ItemComponentCallback;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.Registry;
import tsp.azuma.api.ManaDurable;
import tsp.azuma.api.cca.ItemManaComponent;
import tsp.azuma.command.AltarRecipeCommand;
import tsp.azuma.recipe.AltarRecipeManager;
import tsp.azuma.recipe.GuideBookRecipe;
import tsp.azuma.registry.*;
import tsp.azuma.world.WorldSetup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Azuma implements ModInitializer {

    public static final ItemGroup GROUP = FabricItemGroupBuilder.build(id("group"), () -> new ItemStack(Items.MIASMA));
    public static final String MODID = "azuma";
    public static final Map<ChunkPos, ArrayList<BlockPos>> caveAirList = new HashMap<>();
    public static final AltarRecipeManager ALTAR_RECIPE_MANAGER = new AltarRecipeManager();
    public static final RecipeSerializer<GuideBookRecipe> BOOK_RECIPE = Registry.register(Registry.RECIPE_SERIALIZER, id("guidebook"), new GuideBookRecipe.Serializer());

    @Override
    public void onInitialize() {

        Registry.ITEM.forEach(item -> {
            if (item instanceof ManaDurable) {
                ItemComponentCallback.event(item).register((stack, components) -> components.put(Components.MANA, new ItemManaComponent(
                        ((ManaDurable) item).getMaxMana(),
                        ((ManaDurable) item).getStartingMana()
                )));
            }
        });

        RegistryEntryAddedCallback.event(Registry.ITEM).register((i, identifier, item) -> {
            if (item instanceof ManaDurable) {
                ItemComponentCallback.event(item).register((stack, components) -> components.put(Components.MANA, new ItemManaComponent(
                        ((ManaDurable) item).getMaxMana(),
                        ((ManaDurable) item).getStartingMana()
                )));
            }
        });

        AttackBlockCallback.EVENT.register((playerEntity, world, hand, blockPos, direction) -> {
            ItemStack heldStack = playerEntity.getStackInHand(hand);

            if(heldStack.getItem() instanceof ManaDurable) {
                if(Components.MANA.get(heldStack).getMana() <= 0) {
                    return ActionResult.FAIL;
                }
            }

            return ActionResult.PASS;
        });

        AttackEntityCallback.EVENT.register((playerEntity, world, hand, entity, entityHitResult) -> {
            ItemStack heldStack = playerEntity.getStackInHand(hand);

            if(heldStack.getItem() instanceof ManaDurable) {
                if(Components.MANA.get(heldStack).getMana() <= 0) {
                    return ActionResult.FAIL;
                }
            }

            return ActionResult.PASS;
        });

        Components.init();
        Items.init();
        Blocks.init();
        WorldSetup.setup();
        World.init();
        Entities.init();
        AltarRecipeCommand.init();
    }

    public static Identifier id(String path) {
        return new Identifier(MODID, path);
    }
}
