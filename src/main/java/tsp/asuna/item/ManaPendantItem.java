package tsp.asuna.item;

import net.minecraft.item.Item;
import tsp.asuna.Asuna;
import tsp.asuna.api.ManaDurable;

public class ManaPendantItem extends Item implements ManaDurable {

    public ManaPendantItem() {
        super(new Item.Settings().group(Asuna.ASUNA_SPELLS));
    }

    @Override
    public int getMaxMana() {
        return 128;
    }
}
