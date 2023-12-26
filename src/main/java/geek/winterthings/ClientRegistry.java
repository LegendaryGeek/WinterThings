package geek.winterthings;

import geek.winterthings.entity.SnowManModel;
import geek.winterthings.entity.SnowManRenderer;
import net.minecraftforge.client.event.EntityRenderersEvent;

public class ClientRegistry {

    public static void registerEntityModels(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(WTRegistries.SNOW_MAN.get(), SnowManRenderer::new);
    }

    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(SnowManModel.LAYER_LOCATION, SnowManModel::createBodyLayer);
    }
}