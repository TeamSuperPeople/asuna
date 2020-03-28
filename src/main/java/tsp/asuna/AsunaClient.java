package tsp.asuna;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import net.minecraft.text.LiteralText;
import tsp.asuna.cilent.renderer.MiasmaEntityRenderer;
import tsp.asuna.registry.Entities;
import tsp.asuna.registry.Items;


@Environment(EnvType.CLIENT)
public class AsunaClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        Items.init();




        EntityRendererRegistry.INSTANCE.register(Entities.MIASMA_ENTITY, (dispatcher, context) -> new MiasmaEntityRenderer(dispatcher));


    }




}
