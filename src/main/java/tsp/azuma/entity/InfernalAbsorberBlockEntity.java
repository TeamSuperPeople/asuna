package tsp.azuma.entity;

import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Tickable;
import tsp.azuma.api.ManaStorage;
import tsp.azuma.registry.Entities;

public class InfernalAbsorberBlockEntity extends BlockEntity implements BlockEntityClientSerializable, Tickable, ManaStorage {

    private static final int MAX_MANA = 500;
    private static final int MAX_LAVA = 500;
    private final static int MAX_OUTPUT = 8;
    private final static int MAX_INPUT = 100;

    private int heldMana = 0; // 1 lava -> 100 mana
    private int storedLava = 0; // 1 lava -> 100 mb for equal conversion

    public InfernalAbsorberBlockEntity() {
        super(Entities.INFERNAL_ABSORBER);
    }

    @Override
    public void tick() {
        if(world != null && !world.isClient) {
            if(heldMana < MAX_MANA && storedLava > 0) {
                heldMana++;
                storedLava--;
                sync();
            }
        }
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        tag.putInt("StoredMana", heldMana);
        tag.putInt("StoredLava", storedLava);

        return super.toTag(tag);
    }

    @Override
    public void fromTag(CompoundTag tag) {
        super.fromTag(tag);

        this.heldMana = tag.getInt("StoredMana");
        this.storedLava = tag.getInt("StoredLava");
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
        return heldMana;
    }

    @Override
    public int getMaxMana() {
        return MAX_MANA;
    }

    @Override
    public int getMaxManaInput() {
        return 0;
    }

    @Override
    public int getMaxManaOutput() {
        return MAX_OUTPUT;
    }

    @Override
    public int insertMana(int amount) {
        return amount;
    }

    @Override
    public int extract(int amount) {
        if(amount <= MAX_OUTPUT) {
            if(amount <= heldMana) {
                this.heldMana -= amount;
                return amount;
            } else {
                int returnAmount = heldMana;
                this.heldMana = 0;
                return returnAmount;
            }
        } else {
            return this.extract(MAX_OUTPUT);
        }
    }

    @Override
    public boolean canInsert() {
        return false;
    }

    @Override
    public boolean canExtract() {
        return true;
    }

    public int getLava() {
        return storedLava;
    }

    public boolean canAcceptLava() {
        return storedLava <= MAX_LAVA - 100;
    }

    public void insertLava(int amount) {
        this.storedLava = Math.min(MAX_LAVA, this.storedLava + amount);

        if(!world.isClient) {
            this.sync();
        }
    }
}
