package tsp.azuma.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.DefaultedList;
import net.minecraft.world.World;
import tsp.azuma.Azuma;
import tsp.azuma.api.ItemManaProvider;
import tsp.azuma.api.cca.ManaComponent;
import tsp.azuma.registry.Components;

import java.util.List;

public class ManaPendantItem extends Item implements ItemManaProvider {

    public ManaPendantItem() {
        super(new Item.Settings().group(Azuma.GROUP).maxCount(1));
    }

    @Override
    public int getMaxMana() {
        return 128;
    }

    @Override
    public int getStartingMana() {
        return 0;
    }

    @Override
    public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
        if (this.isIn(group)) {
            ItemStack thisStack = new ItemStack(this);
            ManaComponent manaComponent = Components.MANA.get(thisStack);
            manaComponent.setMana(manaComponent.getMaxMana());
            stacks.add(thisStack);
        }

        // add default (no-mana) stack
        super.appendStacks(group, stacks);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
    }
}
