package tsp.azuma.registry;

import nerdhub.cardinal.components.api.ComponentRegistry;
import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.event.EntityComponentCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import tsp.azuma.Azuma;
import tsp.azuma.api.cca.JoinComponent;
import tsp.azuma.api.cca.ManaComponent;

public class Components {

    public static final ComponentType<ManaComponent> MANA =
            ComponentRegistry.INSTANCE.registerIfAbsent(new Identifier(Azuma.MODID, "mana"), ManaComponent.class);

    public static final ComponentType<JoinComponent> HAS_JOINED =
            ComponentRegistry.INSTANCE.registerIfAbsent(new Identifier(Azuma.MODID, "has_joined"), JoinComponent.class);

    public static void init() {
        EntityComponentCallback.event(PlayerEntity.class).register((playerEntity, components) -> components.put(HAS_JOINED, new JoinComponent()));
    }

    private Components() {
        // NO-OP
    }
}
