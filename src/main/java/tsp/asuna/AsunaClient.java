package tsp.asuna;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.SpriteAtlasTexture;
import tsp.asuna.cilent.renderer.LifeStealEntityRenderer;
import tsp.asuna.cilent.renderer.MiasmaEntityRenderer;
import tsp.asuna.entities.LifeStealEntity;
import tsp.asuna.entities.MiasmaEntity;
import tsp.asuna.registry.Entities;
import tsp.asuna.registry.Items;


@Environment(EnvType.CLIENT)
public class AsunaClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        Items.init();

        EntityRendererRegistry.INSTANCE.register(Entities.MIASMA_ENTITY, (dispatcher, context) -> new MiasmaEntityRenderer(dispatcher));
        EntityRendererRegistry.INSTANCE.register(Entities.LIFESTEAL_ENTITY, (dispatcher, context) -> new LifeStealEntityRenderer(dispatcher));

        ClientSidePacketRegistry.INSTANCE.register(LifeStealEntity.ENTITY_ID, (context, packet) -> {
            double x = packet.readDouble();
            double y = packet.readDouble();
            double z = packet.readDouble();

            int entityId = packet.readInt();

            context.getTaskQueue().execute(() -> {
                LifeStealEntity lifeStealEntity = new LifeStealEntity(MinecraftClient.getInstance().world, x, y, z);
                lifeStealEntity.setEntityId(entityId);
                MinecraftClient.getInstance().world.addEntity(entityId, lifeStealEntity);
            });
        });

        ClientSidePacketRegistry.INSTANCE.register(MiasmaEntity.ENTITY_ID, (context, packet) -> {
            double x = packet.readDouble();
            double y = packet.readDouble();
            double z = packet.readDouble();

            int entityId = packet.readInt();

            context.getTaskQueue().execute(() -> {
                MiasmaEntity miasmaEntity = new MiasmaEntity(MinecraftClient.getInstance().world, x, y, z);
                miasmaEntity.setEntityId(entityId);
                MinecraftClient.getInstance().world.addEntity(entityId, miasmaEntity);
            });
        });
    }
}
