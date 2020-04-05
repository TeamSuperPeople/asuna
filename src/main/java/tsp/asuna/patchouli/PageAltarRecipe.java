package tsp.asuna.patchouli;

import com.google.gson.annotations.SerializedName;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.item.ItemStack;
import vazkii.patchouli.client.book.BookEntry;
import vazkii.patchouli.client.book.gui.GuiBook;
import vazkii.patchouli.client.book.page.abstr.PageWithText;
import vazkii.patchouli.common.util.ItemStackUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Environment(EnvType.CLIENT)
public class PageAltarRecipe extends PageWithText {

    @SerializedName("item_groups")
    Map<String, List<String>> itemGroups;

    @SerializedName("link_recipe")
    boolean linkRecipe;

    transient HashMap<String, ArrayList<ItemStack>> itemStackGroups = new HashMap<>();

    @Override
    public void build(BookEntry entry, int pageNum) {
        itemGroups.forEach((label, itemNames) -> {
            ArrayList<ItemStack> stacks = new ArrayList<>();

            itemNames.forEach(itemName -> {
                stacks.add(ItemStackUtil.loadStackFromString(itemName));
            });

            itemStackGroups.put(label, stacks);
        });
//
//        if(linkRecipe)
//            entry.addRelevantStack(itemStack, pageNum);
    }

    @Override
    public void render(int mouseX, int mouseY, float pticks) {
        int w = 66;
        int h = 26;

        mc.getTextureManager().bindTexture(book.craftingTexture);
        RenderSystem.enableBlend();
        DrawableHelper.blit(GuiBook.PAGE_WIDTH / 2 - w / 2, 10, 0, 128 - h, w, h, 128, 128);

        int yOffset = 0;
        for (Map.Entry<String, ArrayList<ItemStack>> e : itemStackGroups.entrySet()) {
            String label = e.getKey();
            List<ItemStack> stacks = e.getValue();

            parent.drawCenteredStringNoShadow(label, GuiBook.PAGE_WIDTH / 2, yOffset, book.headerColor);

            int xOffset = 0;
            int pageCenter = GuiBook.PAGE_WIDTH / 2;
            for (ItemStack itemStack : stacks) {
                parent.renderItemStack(pageCenter + xOffset - (stacks.size() * 16) / 2, 15 + yOffset + (yOffset > 0 ? -4 : 0), mouseX, mouseY, itemStack);
                xOffset += 16;
            }

            yOffset += 38;
        }

        super.render(mouseX, mouseY, pticks);
    }

    @Override
    public int getTextHeight() {
        return 40;
    }


}