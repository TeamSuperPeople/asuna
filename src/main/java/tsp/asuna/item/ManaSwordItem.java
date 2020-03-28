package tsp.asuna.item;

import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import tsp.asuna.api.ManaCharged;

public class ManaSwordItem extends SwordItem implements ManaCharged {

    public ManaSwordItem(ToolMaterial material, int attackDamage, float attackSpeed) {
        super(material, attackDamage, attackSpeed, new Settings().maxDamage(-1));
    }

    @Override
    public int getMaxMana() {
        return 0;
    }
}
