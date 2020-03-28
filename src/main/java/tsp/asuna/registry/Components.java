package tsp.asuna.registry;

import nerdhub.cardinal.components.api.ComponentRegistry;
import nerdhub.cardinal.components.api.ComponentType;
import net.minecraft.util.Identifier;
import tsp.asuna.Asuna;
import tsp.asuna.api.ManaComponent;

public class Components {

    public static final ComponentType<ManaComponent> MANA =
            ComponentRegistry.INSTANCE.registerIfAbsent(new Identifier(Asuna.MODID, "mana"), ManaComponent.class);

    public static void init() {

    }
}
