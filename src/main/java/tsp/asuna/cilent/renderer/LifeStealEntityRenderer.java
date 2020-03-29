package tsp.asuna.cilent.renderer;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import tsp.asuna.Asuna;

public class LifeStealEntityRenderer extends EntityRenderer {
    public LifeStealEntityRenderer(EntityRenderDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public Identifier getTexture(Entity entity) {
        return Asuna.id("textures/entity/miasmaeffect.png");
    }
}
