package tsp.azuma.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.fabricmc.fabric.api.util.NbtType;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.LongTag;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.BlockPos;
import tsp.azuma.api.ManaConnectable;
import tsp.azuma.api.ManaStorage;
import tsp.azuma.registry.Entities;

import java.util.ArrayList;
import java.util.List;

public class ManaPylonBlockEntity extends BlockEntity implements BlockEntityClientSerializable, Tickable, ManaStorage, ManaConnectable {

    private final static int MAX_MANA = 1000;
    private final static int MAX_OUTPUT = 100;
    private final static int MAX_INPUT = 100;

    @Environment(EnvType.CLIENT)
    private int animationProgress = 0;

    private int heldMana = 0;
    private final List<BlockPos> tempTargetPositions = new ArrayList<>();
    private final List<ManaConnectable> manaTargets = new ArrayList<>();

    public ManaPylonBlockEntity() {
        super(Entities.MANA_PYLON);
    }

    @Override
    public void tick() {
        if(world == null) {
            return;
        }

        // get mana targets from positions
        if(!tempTargetPositions.isEmpty()) {
            tempTargetPositions.forEach(pos -> {
                if(world.getBlockEntity(pos) instanceof ManaConnectable) {
                    manaTargets.add((ManaConnectable) world.getBlockEntity(pos));
                }
            });

            tempTargetPositions.clear();
        }

        if(world.isClient) {
            return;
        }

        // deliver energy to relays
        if(this.heldMana > 0) {
            int amountPerConnection = (int) Math.floor(Math.min(MAX_OUTPUT, getMana()) / (float) manaTargets.size());
            int amountActuallyTaken = 0;

            for (ManaConnectable connection : manaTargets) {
                if (connection instanceof ManaStorage) {
                    ManaStorage storage = (ManaStorage) connection;
                    int denied = storage.insertMana(amountPerConnection);
                    amountActuallyTaken += amountPerConnection - denied;

                    if (storage instanceof BlockEntityClientSerializable) {
                        ((BlockEntityClientSerializable) storage).sync();
                    }
                }
            }

            if (amountActuallyTaken > 0) {
                this.heldMana -= amountActuallyTaken;
                sync();
                markDirty();
            }
        }
    }

    public List<ManaConnectable> getManaTargets() {
        return manaTargets;
    }

    @Environment(EnvType.CLIENT)
    public int getAnimationProgress() {
        return animationProgress;
    }

    @Environment(EnvType.CLIENT)
    public void incrementAnimationProgress() {
        this.animationProgress++;
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        // store connections
        ListTag connectionsTag = new ListTag();
        manaTargets.forEach(relay -> {
            if(relay instanceof BlockEntity) {
                connectionsTag.add(LongTag.of(((BlockEntity) relay).getPos().asLong()));
            }
        });

        tag.put("Connections", connectionsTag);
        tag.putInt("HeldMana", heldMana);

        return super.toTag(tag);
    }

    @Override
    public void fromTag(CompoundTag tag) {
        tempTargetPositions.clear();
        manaTargets.clear();

        // retrieve connections
        ListTag relays = tag.getList("Connections", NbtType.LONG);
        if(relays != null) {
            relays.forEach(longTag -> {
                tempTargetPositions.add(BlockPos.fromLong(((LongTag) longTag).getLong()));
            });
        }

        // update mana
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
        return true;
    }

    @Override
    public boolean canExtract() {
        return true;
    }

    @Override
    public void addTarget(ManaConnectable target) {
        this.manaTargets.add(target);
    }
}
