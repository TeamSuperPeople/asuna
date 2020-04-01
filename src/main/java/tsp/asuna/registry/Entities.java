package tsp.asuna.registry;

import net.fabricmc.fabric.api.entity.FabricEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.util.registry.Registry;
import tsp.asuna.Asuna;
import tsp.asuna.block.InfernalYeeterBlock;
import tsp.asuna.entities.*;

public class Entities {

    public static final EntityType<MiasmaEntity> MIASMA_ENTITY = register(
            "miasma_entity",
            FabricEntityTypeBuilder.<MiasmaEntity>create(EntityCategory.MONSTER, (entityType, world) -> new MiasmaEntity(world)).size(EntityDimensions.fixed(1, 1)).build());

    public static final EntityType<LifeStealEntity> LIFESTEAL_ENTITY = register(
            "lifesteal_entity",
            FabricEntityTypeBuilder.<LifeStealEntity>create(EntityCategory.MONSTER, (entityType, world) -> new LifeStealEntity(world)).size(EntityDimensions.fixed(1, 1)).build());

    public static final EntityType<MiasmaEntity> THORON_ENTITY = register(
            "thoron_entity",
            FabricEntityTypeBuilder.<MiasmaEntity>create(EntityCategory.MONSTER, (entityType, world) -> new MiasmaEntity(world)).size(EntityDimensions.fixed(1, 1)).build());

    public static final BlockEntityType<ManaPylonBlockEntity> MANA_PYLON = register(
            "mana_pylon",
            BlockEntityType.Builder.create(ManaPylonBlockEntity::new, Blocks.MANA_PYLON).build(null)
    );

    public static final BlockEntityType<ManaRelayBlockEntity> MANA_RELAY = register(
            "mana_relay",
            BlockEntityType.Builder.create(ManaRelayBlockEntity::new, Blocks.MANA_RELAY).build(null)
    );

    public static final BlockEntityType<InfernalAbsorberBlockEntity> INFERNAL_ABSORBER = register(
            "infernal_absorber",
            BlockEntityType.Builder.create(InfernalAbsorberBlockEntity::new, Blocks.INFERNAL_ABSORBER).build(null)
    );

    public static final BlockEntityType<InfernalYeeterBlockEntity> INFERNAL_YEETER = register(
            "infernal_yeeter",
            BlockEntityType.Builder.create(InfernalYeeterBlockEntity::new, Blocks.INFERNAL_YEETER).build(null)
    );


    /**
     * Registers and returns the provided {@link BlockEntityType} with the given name.
     *
     * @param name  name to register the {@link BlockEntityType} under. Combined with in an Identifier with the modid path.
     * @param instance  instance of a {@link BlockEntityType}; see {@link BlockEntityType.Builder} for creation
     * @param <T>  {@link BlockEntity} wrapped by the {@link BlockEntityType}
     * @return  registered {@link BlockEntityType} which was passed in
     */
    private static <T extends BlockEntity> BlockEntityType<T> register(String name, BlockEntityType<T> instance) {
        return Registry.register(Registry.BLOCK_ENTITY_TYPE, Asuna.id(name), instance);
    }

    private static <T extends Entity> EntityType<T> register(String name, EntityType<T> instance) {
        return Registry.register(Registry.ENTITY_TYPE, Asuna.id(name), instance);
    }

    public static void init() {
        // NO-OP
    }

    private Entities() {
        // NO-OP
    }
}
