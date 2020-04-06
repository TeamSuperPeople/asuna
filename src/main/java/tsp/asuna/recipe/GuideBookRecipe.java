package tsp.asuna.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.PacketByteBuf;
import tsp.asuna.Asuna;

/**
 * Thanks to Composing for the original recipe implementation:
 * https://github.com/fabric-community/Composing/blob/master/src/main/java/io/teamblue/composing/recipe/GuideBookRecipe.java
 */
public class GuideBookRecipe extends ShapelessRecipe {

    public GuideBookRecipe(Identifier id, String group, ItemStack output, DefaultedList<Ingredient> input) {
        super(id, group, output, input);
    }

    @Override
    public ItemStack craft(CraftingInventory craftingInventory) {
        ItemStack ret = super.craft(craftingInventory);
        ret.getOrCreateTag().putString("patchouli:book", "asuna:guidebook");
        return ret;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Asuna.BOOK_RECIPE;
    }

    public static class Serializer implements RecipeSerializer<GuideBookRecipe> {

        @Override
        public GuideBookRecipe read(Identifier identifier, JsonObject jsonObject) {
            String string = JsonHelper.getString(jsonObject, "group", "");
            DefaultedList<Ingredient> defaultedList = getIngredients(JsonHelper.getArray(jsonObject, "ingredients"));
            if (defaultedList.isEmpty()) {
                throw new JsonParseException("No ingredients for shapeless recipe");
            } else if (defaultedList.size() > 9) {
                throw new JsonParseException("Too many ingredients for shapeless recipe");
            } else {
                ItemStack itemStack = ShapedRecipe.getItemStack(JsonHelper.getObject(jsonObject, "result"));
                return new GuideBookRecipe(identifier, string, itemStack, defaultedList);
            }
        }

        private static DefaultedList<Ingredient> getIngredients(JsonArray json) {
            DefaultedList<Ingredient> defaultedList = DefaultedList.of();

            for(int i = 0; i < json.size(); ++i) {
                Ingredient ingredient = Ingredient.fromJson(json.get(i));
                if (!ingredient.isEmpty()) {
                    defaultedList.add(ingredient);
                }
            }

            return defaultedList;
        }

        @Override
        public GuideBookRecipe read(Identifier identifier, PacketByteBuf packetByteBuf) {
            String string = packetByteBuf.readString(32767);
            int i = packetByteBuf.readVarInt();
            DefaultedList<Ingredient> defaultedList = DefaultedList.ofSize(i, Ingredient.EMPTY);

            for(int j = 0; j < defaultedList.size(); ++j) {
                defaultedList.set(j, Ingredient.fromPacket(packetByteBuf));
            }

            ItemStack itemStack = packetByteBuf.readItemStack();
            return new GuideBookRecipe(identifier, string, itemStack, defaultedList);
        }

        @Override
        public void write(PacketByteBuf packetByteBuf, GuideBookRecipe shapelessRecipe) {
            packetByteBuf.writeString(shapelessRecipe.getGroup());
            packetByteBuf.writeVarInt(shapelessRecipe.getPreviewInputs().size());

            for (Ingredient ingredient : shapelessRecipe.getPreviewInputs()) {
                ingredient.write(packetByteBuf);
            }

            packetByteBuf.writeItemStack(shapelessRecipe.getOutput());
        }
    }
}
