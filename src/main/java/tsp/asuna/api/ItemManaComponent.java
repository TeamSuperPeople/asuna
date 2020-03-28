package tsp.asuna.api;

import net.minecraft.nbt.CompoundTag;

public class ItemManaComponent implements ManaComponent {

    private int mana;
    private int maxMana;

    @Override
    public int getMana() {
        return mana;
    }

    @Override
    public int getMaxMana() {
        return maxMana;
    }

    @Override
    public void fromTag(CompoundTag tag) {
        this.mana = tag.getInt("Mana");
        this.maxMana = tag.getInt("MaxMana");
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        tag.putInt("Mana", mana);
        tag.putInt("MaxMana", maxMana);

        return tag;
    }
}
