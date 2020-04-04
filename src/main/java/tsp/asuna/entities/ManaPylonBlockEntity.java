package tsp.asuna.entities;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Tickable;
import tsp.asuna.api.ManaConnectable;
import tsp.asuna.api.ManaStorage;
import tsp.asuna.registry.Entities;

import java.util.ArrayList;
import java.util.List;

public class ManaPylonBlockEntity extends BlockEntity implements BlockEntityClientSerializable, Tickable, ManaStorage, ManaConnectable {

    private final static int MAX_MANA = 1000;
    private final static int MAX_OUTPUT = 100;
    private final static int MAX_INPUT = 100;

    @Environment(EnvType.CLIENT)
    private int animationProgress = 0;

    private int heldMana = 0;
    private final List<ManaConnectable> manaTargets = new ArrayList<>();

    public ManaPylonBlockEntity() {
        super(Entities.MANA_PYLON);
    }

    @Override
    public void tick() {
        manaTargets.forEach(relay -> {

        });
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
//        // store relays
//        ListTag linkedRelaysTag = new ListTag();
//        linkedRelays.forEach((relay, action) -> linkedRelaysTag.add(NbtType.LONG, LongTag.of(relay.getPos().asLong())));
//        tag.put("Relays", linkedRelaysTag);
//
//        // store mana information
//        tag.putInt("HeldMana", heldMana);

        return super.toTag(tag);
    }

    @Override
    public void fromTag(CompoundTag tag) {
//        // retrieve relays
//        ListTag relays = tag.getList("Relays", NbtType.LONG);
//        relays.forEach(longTag -> {
//            BlockEntity potentialPylon = world.getBlockEntity(BlockPos.fromLong(((LongTag) longTag).getLong()));
//
//            if (potentialPylon instanceof ManaRelayBlockEntity) {
//                linkedRelays.add((ManaRelayBlockEntity) potentialPylon);
//            }
//        });
//
//        // update mana
//        this.heldMana = tag.getInt("HeldMana");

        super.fromTag(tag);
    }

    @Override
    public void fromClientTag(CompoundTag tag) {

    }

    @Override
    public CompoundTag toClientTag(CompoundTag tag) {
        return tag;
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
