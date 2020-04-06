package tsp.azuma.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import tsp.azuma.Azuma;
import tsp.azuma.api.ManaDurable;

public class ManaArmorItem extends ArmorItem implements ManaDurable {

    private static final int DURABILITY_MODIFIER = 40;
    private static final int[] BASE_DURABILITY = new int[] { 13, 15, 16, 11 };

    public ManaArmorItem(ArmorMaterial material, EquipmentSlot slot) {
        super(material, slot, new Settings().maxDamage(-1).group(Azuma.GROUP));
    }

    @Override
    public int getMaxMana() {
        return BASE_DURABILITY[slot.getEntitySlotId()] * DURABILITY_MODIFIER;
    }
}
