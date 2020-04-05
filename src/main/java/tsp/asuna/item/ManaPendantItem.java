package tsp.asuna.item;

import net.minecraft.item.Item;
import tsp.asuna.Asuna;
import tsp.asuna.api.ItemManaProvider;

public class ManaPendantItem extends Item implements ItemManaProvider {

    public ManaPendantItem() {
        super(new Item.Settings().group(Asuna.ASUNA_SPELLS).maxCount(1));
    }

    @Override
    public int getMaxMana() {
        return 128;
    }

    @Override
    public int getStartingMana() {
        return 0;
    }
}
