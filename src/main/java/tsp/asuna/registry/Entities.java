package tsp.asuna.registry;

import net.fabricmc.fabric.api.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.util.registry.Registry;
import tsp.asuna.Asuna;
import tsp.asuna.entities.MiasmaEntity;

public class Entities {

    public static final EntityType<MiasmaEntity> MIASMA_ENTITY = register(
            "miasma_entity",
            FabricEntityTypeBuilder.<MiasmaEntity>create(EntityCategory.MONSTER, (entityType, world) -> new MiasmaEntity(world)).size(EntityDimensions.fixed(1, 1)).build());


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
