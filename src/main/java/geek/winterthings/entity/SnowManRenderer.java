package geek.winterthings.entity;

import geek.winterthings.WinterThings;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class SnowManRenderer extends MobRenderer<SnowMan, SnowManModel<SnowMan>> {

    private static final ResourceLocation RESOURCE_LOCATION = new ResourceLocation(WinterThings.MODID,
            "textures/entity/snow_man.png");

    public SnowManRenderer(EntityRendererProvider.Context context) {
        super(context, new SnowManModel<>(
                context.bakeLayer(SnowManModel.LAYER_LOCATION)), 0.8F);
    }

    @NotNull
    @Override
    public ResourceLocation getTextureLocation(@NotNull final SnowMan entity) {
        return RESOURCE_LOCATION;
    }
}
