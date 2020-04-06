package tsp.azuma.cilent.renderer;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.util.Identifier;
import tsp.azuma.Azuma;
import tsp.azuma.entity.LifeStealEntity;

public class LifeStealEntityRenderer extends EntityRenderer<LifeStealEntity> {
    public LifeStealEntityRenderer(EntityRenderDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public Identifier getTexture(LifeStealEntity entity) {
        return Azuma.id("textures/entity/miasma_effect.png");
    }
}
