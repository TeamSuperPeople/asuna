package tsp.asuna;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.ItemStack;
import tsp.asuna.cilent.renderer.*;
import tsp.asuna.entity.*;
import tsp.asuna.item.CrystalLinkerItem;
import tsp.asuna.patchouli.PageAltarRecipe;
import tsp.asuna.registry.Blocks;
import tsp.asuna.registry.Entities;
import tsp.asuna.registry.Items;
import vazkii.patchouli.client.book.ClientBookRegistry;


@Environment(EnvType.CLIENT)
public class AsunaClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        Items.init();

        ClientBookRegistry.INSTANCE.pageTypes.put("altar_recipe", PageAltarRecipe.class);

        EntityRendererRegistry.INSTANCE.register(Entities.MANA_BOMB, (dispatcher, context) -> new ManaBombEntityRenderer(dispatcher));
        EntityRendererRegistry.INSTANCE.register(Entities.MIASMA_ENTITY, (dispatcher, context) -> new MiasmaEntityRenderer(dispatcher));
        EntityRendererRegistry.INSTANCE.register(Entities.LIFESTEAL_ENTITY, (dispatcher, context) -> new LifeStealEntityRenderer(dispatcher));
        EntityRendererRegistry.INSTANCE.register(Entities.THORON_ENTITY, (dispatcher, context) -> new ThoronEntityRenderer(dispatcher));

        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.MANA_CRYSTAL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.MANA_PYLON, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.MANA_RELAY, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.INFUSION_ALTAR_CORE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.INFUSION_ALTAR_PEDESTAL, RenderLayer.getCutout());

        BlockEntityRendererRegistry.INSTANCE.register(Entities.MANA_RELAY, ManaRelayBlockEntityRenderer::new);
        BlockEntityRendererRegistry.INSTANCE.register(Entities.MANA_PYLON, ManaPylonBlockEntityRenderer::new);
        BlockEntityRendererRegistry.INSTANCE.register(Entities.INFERNAL_ABSORBER, InfernalAbsorberBlockEntityRenderer::new);
        BlockEntityRendererRegistry.INSTANCE.register(Entities.INFERNAL_YEETER, InfernalYeeterBlockEntityRenderer::new);
        BlockEntityRendererRegistry.INSTANCE.register(Entities.INFUSION_ALTAR_CORE, InfusionAltarCoreBlockEntityRenderer::new);
        BlockEntityRendererRegistry.INSTANCE.register(Entities.INFUSION_PEDESTAL, InfusionAltarPedestalBlockEntityRenderer::new);

        ClientSidePacketRegistry.INSTANCE.register(LifeStealEntity.ENTITY_ID, (context, packet) -> {
            double x = packet.readDouble();
            double y = packet.readDouble();
            double z = packet.readDouble();

            int entityId = packet.readInt();
            int ownerId = packet.readInt();

            context.getTaskQueue().execute(() -> {
                LifeStealEntity lifeStealEntity = new LifeStealEntity(MinecraftClient.getInstance().world, x, y, z, MinecraftClient.getInstance().world.getEntityById(ownerId));
                lifeStealEntity.setEntityId(entityId);
                MinecraftClient.getInstance().world.addEntity(entityId, lifeStealEntity);
            });
        });

        ClientSidePacketRegistry.INSTANCE.register(ManaBombEntity.ENTITY_ID, (context, packet) -> {
            double x = packet.readDouble();
            double y = packet.readDouble();
            double z = packet.readDouble();

            int entityId = packet.readInt();
            int ownerId = packet.readInt();

            context.getTaskQueue().execute(() -> {
                ManaBombEntity manaBomb = new ManaBombEntity(MinecraftClient.getInstance().world, x, y, z, MinecraftClient.getInstance().world.getEntityById(ownerId));
                manaBomb.setEntityId(entityId);
                MinecraftClient.getInstance().world.addEntity(entityId, manaBomb);
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

        ClientSidePacketRegistry.INSTANCE.register(ThoronEntity.ENTITY_ID, (context, packet) -> {
            double x = packet.readDouble();
            double y = packet.readDouble();
            double z = packet.readDouble();

            int entityId = packet.readInt();

            context.getTaskQueue().execute(() -> {
                ThoronEntity thoronEntity = new ThoronEntity(MinecraftClient.getInstance().world, x, y, z);
                thoronEntity.setEntityId(entityId);
                MinecraftClient.getInstance().world.addEntity(entityId, thoronEntity);
            });
        });

        HudRenderCallback.EVENT.register(tickDelta -> {
            if(MinecraftClient.getInstance().player == null) {
                return;
            }

            if(MinecraftClient.getInstance().player.getMainHandStack().getItem().equals(Items.CRYSTAL_LINKER)) {
                ItemStack heldStack = MinecraftClient.getInstance().player.getMainHandStack();

                if(heldStack.getOrCreateSubTag(CrystalLinkerItem.SUBTAG_KEY).contains(CrystalLinkerItem.ORIGIN_KEY)) {
                    MinecraftClient.getInstance().textRenderer.draw(heldStack.getOrCreateSubTag(CrystalLinkerItem.SUBTAG_KEY).get(CrystalLinkerItem.ORIGIN_KEY).asString(), 6, 6, 0x333333);
                    MinecraftClient.getInstance().textRenderer.draw("Shift-click to reset the current connection.", 6, 16, 0x333333);
                    MinecraftClient.getInstance().textRenderer.draw("Click on another Mana Relay to finish the connection.", 6, 26, 0x333333);

                    MinecraftClient.getInstance().textRenderer.draw(heldStack.getOrCreateSubTag(CrystalLinkerItem.SUBTAG_KEY).get(CrystalLinkerItem.ORIGIN_KEY).asString(), 5, 5, 0xFFFFFF);
                    MinecraftClient.getInstance().textRenderer.draw("Shift-click to reset the current connection.", 5, 15, 0xFFFFFF);
                    MinecraftClient.getInstance().textRenderer.draw("Click on another Mana Relay to finish the connection.", 5, 25, 0xFFFFFF);
                } else {
                    MinecraftClient.getInstance().textRenderer.draw("Click on a Mana Pylon or Mana Relay to begin a connection.", 6, 6, 0x333333);
                    MinecraftClient.getInstance().textRenderer.draw("Click on a Mana Pylon or Mana Relay to begin a connection.", 5, 5, 0xFFFFFF);
                }
            }
        });
    }
}
