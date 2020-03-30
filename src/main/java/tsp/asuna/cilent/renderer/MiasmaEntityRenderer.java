package tsp.asuna.cilent.renderer;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.util.Identifier;
import tsp.asuna.Asuna;
import tsp.asuna.entities.MiasmaEntity;

public class MiasmaEntityRenderer extends EntityRenderer<MiasmaEntity> {

    public MiasmaEntityRenderer(EntityRenderDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public Identifier getTexture(MiasmaEntity entity) {
        return Asuna.id("textures/entity/miasmaeffect.png");
    }
}
