package tsp.asuna.cilent.renderer;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import tsp.asuna.Asuna;

public class MiasmaEntityRenderer extends EntityRenderer {
    public MiasmaEntityRenderer(EntityRenderDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public Identifier getTexture(Entity entity) {
        return Asuna.id("textures/entity/miasmaeffect.png");
    }
}
