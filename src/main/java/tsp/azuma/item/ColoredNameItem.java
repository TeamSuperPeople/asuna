package tsp.azuma.item;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class ColoredNameItem extends Item {

    private final Formatting formatting;

    public ColoredNameItem(Settings settings, Formatting formatting) {
        super(settings);
        this.formatting = formatting;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public Text getName() {
        return super.getName().formatted(formatting);
    }

    @Override
    public Text getName(ItemStack stack) {
        return super.getName(stack).formatted(formatting);
    }
}
