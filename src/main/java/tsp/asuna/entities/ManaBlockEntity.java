package tsp.asuna.entities;

import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Tickable;
import tsp.asuna.api.ManaStorage;

public abstract class ManaBlockEntity extends BlockEntity implements BlockEntityClientSerializable, Tickable, ManaStorage {

    private final int maxMana;
    private final int maxOutput;
    private final int maxInput;
    protected int storedMana;

    public ManaBlockEntity(BlockEntityType<?> type, int maxMana, int maxOutput, int maxInput) {
        super(type);
        this.maxMana = maxMana;
        this.maxOutput = maxOutput;
        this.maxInput = maxInput;
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        tag.putInt("StoredMana", storedMana);

        return super.toTag(tag);
    }

    @Override
    public void fromTag(CompoundTag tag) {
        this.storedMana = tag.getInt("StoredMana");

        super.fromTag(tag);
    }

    @Override
    public void fromClientTag(CompoundTag tag) {
        fromTag(tag);
    }

    @Override
    public CompoundTag toClientTag(CompoundTag tag) {
        return toTag(tag);
    }

    @Override
    public int getMana() {
        return storedMana;
    }

    @Override
    public int getMaxMana() {
        return maxMana;
    }

    @Override
    public int getMaxManaInput() {
        return maxInput;
    }

    @Override
    public int getMaxManaOutput() {
        return maxOutput;
    }

    @Override
    public int insertMana(int amount) {
        if(amount <= maxInput) {
            if(amount + storedMana <= maxMana) {
                this.storedMana += amount;
                sync();
                return 0;
            } else {
                int remainder = amount + storedMana - maxMana;
                storedMana = maxMana;
                sync();
                return remainder;
            }
        } else {
            int remainder = amount - maxInput;
            remainder += insertMana(maxInput);
            sync();
            return remainder;
        }
    }

    @Override
    public int extract(int amount) {
        if(amount <= getMaxManaOutput()) {
            if(amount <= storedMana) {
                this.storedMana -= amount;
                sync();
                return amount;
            } else {
                int returnAmount = storedMana;
                this.storedMana = 0;
                sync();
                return returnAmount;
            }
        } else {
            sync();
            return this.extract(getMaxManaOutput());
        }
    }
}
