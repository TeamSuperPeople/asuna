package tsp.asuna.entity;

import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Tickable;
import tsp.asuna.api.ManaConnectable;
import tsp.asuna.api.ManaStorage;
import tsp.asuna.registry.Entities;

import java.util.ArrayList;
import java.util.List;

public class ManaRelayBlockEntity extends BlockEntity implements BlockEntityClientSerializable, Tickable, ManaStorage, ManaConnectable {

    private final static int MAX_MANA = 1000;
    private final static int MAX_OUTPUT = 100;
    private final static int MAX_INPUT = 100;

    private int heldMana = 0;
    private final List<ManaConnectable> manaTargets = new ArrayList<>();

    public ManaRelayBlockEntity() {
        super(Entities.MANA_RELAY);
    }

    @Override
    public void tick() {
        if(world == null || world.isClient) {
            return;
        }

        // check storage below
        if(world.getBlockEntity(pos.down()) instanceof ManaStorage) {
            ManaStorage manaStorage = (ManaStorage) world.getBlockEntity(pos.down());

            // power block we're connected to (fuel machines)
            if(manaStorage.canInsert()) {
                this.heldMana = manaStorage.insertMana(Math.min(this.getMaxManaOutput(), this.getMana()));
            }

            // extract from block if needed (take from generators)
            else if (manaStorage.canExtract()) {
                int extractionAmount = Math.min(this.getMaxManaInput(), manaStorage.getMaxManaOutput());
                if(extractionAmount + heldMana > MAX_MANA) {
                    extractionAmount = MAX_MANA - heldMana;
                }

                manaStorage.insertMana(this.insertMana(manaStorage.extract(extractionAmount)));
                sync();
            }
        }



        // deliver energy to relays
        int amountPerConnection = (int) Math.floor(Math.min(MAX_OUTPUT, getMana()) / (float) manaTargets.size());
        int amountActuallyTaken = 0;

        for (ManaConnectable connection : manaTargets) {
            if (connection instanceof ManaStorage) {
                ManaStorage storage = (ManaStorage) connection;
                int denied = storage.insertMana(amountPerConnection);
                amountActuallyTaken += amountPerConnection - denied;

                if(storage instanceof BlockEntityClientSerializable) {
                    ((BlockEntityClientSerializable) storage).sync();
                }
            }
        }

        this.heldMana -= amountActuallyTaken;
        sync();
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        tag.putInt("HeldMana", heldMana);
        return super.toTag(tag);
    }

    @Override
    public void fromTag(CompoundTag tag) {
        this.heldMana = tag.getInt("HeldMana");
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
        return heldMana;
    }

    @Override
    public int getMaxMana() {
        return MAX_MANA;
    }

    @Override
    public int getMaxManaInput() {
        return MAX_INPUT;
    }

    @Override
    public int getMaxManaOutput() {
        return MAX_OUTPUT;
    }

    @Override
    public int insertMana(int amount) {
        if(amount <= MAX_INPUT) {
            if(amount + heldMana <= MAX_MANA) {
                this.heldMana += amount;
                return 0;
            } else {
                int remainder = amount + heldMana - MAX_MANA;
                heldMana = MAX_MANA;
                return remainder;
            }
        } else {
            int remainder = amount - MAX_INPUT;
            remainder += insertMana(MAX_INPUT);
            return remainder;
        }
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
        return false;
    }

    @Override
    public void addTarget(ManaConnectable target) {
        this.manaTargets.add(target);
    }
}
