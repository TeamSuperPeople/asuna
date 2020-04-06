package tsp.azuma.registry;

import net.fabricmc.fabric.api.entity.FabricEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.util.registry.Registry;
import tsp.azuma.Azuma;
import tsp.azuma.entity.*;

public class Entities {

    public static final EntityType<MiasmaEntity> MIASMA_ENTITY = register(
            "miasma_entity",
            FabricEntityTypeBuilder.<MiasmaEntity>create(EntityCategory.MISC, (entityType, world) -> new MiasmaEntity(world)).size(EntityDimensions.fixed(1, 1)).build());

    public static final EntityType<LifeStealEntity> LIFESTEAL_ENTITY = register(
            "lifesteal_entity",
            FabricEntityTypeBuilder.<LifeStealEntity>create(EntityCategory.MISC, (entityType, world) -> new LifeStealEntity(world)).size(EntityDimensions.fixed(1, 1)).build());

    public static final EntityType<MiasmaEntity> THORON_ENTITY = register(
            "thoron_entity",
            FabricEntityTypeBuilder.<MiasmaEntity>create(EntityCategory.MISC, (entityType, world) -> new MiasmaEntity(world)).size(EntityDimensions.fixed(1, 1)).build());

    public static final EntityType<ManaBombEntity> MANA_BOMB = register(
            "mana_bomb",
            FabricEntityTypeBuilder.<ManaBombEntity>create(EntityCategory.MISC, (entityType, world) -> new ManaBombEntity(world)).size(EntityDimensions.fixed(.3f, .3f)).build());

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

    public static final BlockEntityType<InfusionAltarCoreBlockEntity> INFUSION_ALTAR_CORE = register(
            "infusion_altar_core",
            BlockEntityType.Builder.create(InfusionAltarCoreBlockEntity::new, Blocks.INFUSION_ALTAR_CORE).build(null)
    );

    public static final BlockEntityType<InfusionAltarPedestalBlockEntity> INFUSION_PEDESTAL = register(
            "infusion_altar_pedstal",
            BlockEntityType.Builder.create(InfusionAltarPedestalBlockEntity::new, Blocks.INFUSION_ALTAR_PEDESTAL).build(null)
    );

    public static final BlockEntityType<BurningManaGeneratorBlockEntity> BURNING_MANA_GENERATOR = register(
            "burning_mana_generator",
            BlockEntityType.Builder.create(BurningManaGeneratorBlockEntity::new, Blocks.BURNING_MANA_GENERATOR).build(null)
    );

    public static final BlockEntityType<AerialAcceleratorBlockEntity> AERIAL_ACCELERATOR = register(
            "aerial_accelerator",
            BlockEntityType.Builder.create(AerialAcceleratorBlockEntity::new, Blocks.AERIAL_ACCELERATOR).build(null)
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
        return Registry.register(Registry.BLOCK_ENTITY_TYPE, Azuma.id(name), instance);
    }

    private static <T extends Entity> EntityType<T> register(String name, EntityType<T> instance) {
        return Registry.register(Registry.ENTITY_TYPE, Azuma.id(name), instance);
    }

    public static void init() {
        // NO-OP
    }

    private Entities() {
        // NO-OP
    }
}
