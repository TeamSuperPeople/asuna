package tsp.asuna.cilent.renderer;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.util.Identifier;
import tsp.asuna.Asuna;
import tsp.asuna.entity.LifeStealEntity;

public class LifeStealEntityRenderer extends EntityRenderer<LifeStealEntity> {
    public LifeStealEntityRenderer(EntityRenderDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public Identifier getTexture(LifeStealEntity entity) {
        return Asuna.id("textures/entity/miasma_effect.png");
    }
}
