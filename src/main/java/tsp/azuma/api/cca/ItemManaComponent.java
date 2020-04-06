package tsp.azuma.api.cca;

import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.component.Component;
import nerdhub.cardinal.components.api.component.extension.CopyableComponent;
import net.minecraft.nbt.CompoundTag;
import tsp.azuma.registry.Components;

public class ItemManaComponent implements ManaComponent, CopyableComponent<ManaComponent> {

    private int mana;
    private int maxMana;

    public ItemManaComponent(int maxMana, int startingMana) {
        this.maxMana = maxMana;
        this.mana = startingMana;
    }

    @Override
    public int getMana() {
        return mana;
    }

    @Override
    public int getMaxMana() {
        return maxMana;
    }

    @Override
    public void decrement() {
        this.mana = Math.max(0, mana - 1);
    }

    @Override
    public void decrement(int amount) {
        this.mana = Math.max(0, mana - amount);
    }

    @Override
    public void increment() {
        this.mana = Math.min(maxMana, mana + 1);
    }

    @Override
    public void increment(int amount) {
        this.mana = Math.min(maxMana, mana + amount);
    }

    @Override
    public void setMana(int mana) {
        this.mana = mana;
    }

    public boolean isDamaged() {
        return mana < maxMana;
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

    @Override
    public ComponentType<ManaComponent> getComponentType() {
        return Components.MANA;
    }

    @Override
    public boolean isComponentEqual(Component other) {
        if (other instanceof ItemManaComponent) {
            ItemManaComponent otherManaComponent = (ItemManaComponent) other;
            return otherManaComponent.getMaxMana() == this.getMaxMana() && otherManaComponent.getMana() == this.getMana();
        }

        return false;
    }
}
