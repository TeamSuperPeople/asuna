package tsp.azuma.command;

import com.mojang.brigadier.tree.ArgumentCommandNode;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.fabricmc.fabric.api.registry.CommandRegistry;
import net.minecraft.command.arguments.IdentifierArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.CommandSource;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import tsp.azuma.Azuma;
import tsp.azuma.recipe.AltarRecipe;

public class AltarRecipeCommand {

    public static void init() {
        CommandRegistry.INSTANCE.register(false, dispatcher -> {
            LiteralCommandNode<ServerCommandSource> altarRecipeNode = CommandManager
                    .literal("altarrecipe")
                    .requires(source -> source.hasPermissionLevel(2))
                    .build();

            ArgumentCommandNode<ServerCommandSource, Identifier> altarRecipeID = CommandManager
                    .argument("id", IdentifierArgumentType.identifier())
                    .suggests((context, builder) -> CommandSource.suggestIdentifiers(Azuma.ALTAR_RECIPE_MANAGER.getRecipes().keySet(), builder))
                    .executes(context -> {
                        Identifier id = IdentifierArgumentType.getIdentifier(context, "id");
                        ServerPlayerEntity playerEntity = context.getSource().getPlayer();
                        AltarRecipe recipe = Azuma.ALTAR_RECIPE_MANAGER.getRecipes().get(id);

                        if(recipe != null) {
                            playerEntity.sendMessage(new LiteralText("Center: " + new TranslatableText(recipe.getCenterItem().getTranslationKey()).asFormattedString()));
                            playerEntity.sendMessage(new LiteralText("Output: " + new TranslatableText(recipe.getOutput().getTranslationKey()).asFormattedString()));
                            playerEntity.sendMessage(new LiteralText("Mana Requirement: " + recipe.getManaRequirement()));
                        }

                        return 1;
                    })
                    .build();

            dispatcher.getRoot().addChild(altarRecipeNode);
            altarRecipeNode.addChild(altarRecipeID);
        });
    }

    private AltarRecipeCommand() {
        // NO-OP
    }
}
