package geek.winterthings.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import geek.winterthings.WinterThings;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class SnowManModel<T extends SnowMan> extends EntityModel<T> {

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
        new ResourceLocation(WinterThings.MODID, "snow_man"),
        "main");
    private final ModelPart body;

    public SnowManModel(ModelPart root) {
        body = root.getChild("body");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body",
                CubeListBuilder.create().texOffs(0, 0)
                        .addBox(-4.0F, -16.0F, -3.0F,
                                8.0F, 27.0F, 6.0F,
                                new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 7.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(@NotNull T entity, float limbSwing, float limbSwingAmount, float ageInTicks,
                          float netHeadYaw, float headPitch) {
    /*    legFrontLeft.xRot = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount;
        legFrontRight.xRot = Mth.cos(limbSwing * 0.6662F
                + (float) Math.PI) * 0.5F * limbSwingAmount;
        legBackLeft.xRot = Mth.cos(limbSwing * 0.6662F
                + (float) Math.PI) * 0.5F * limbSwingAmount;
        legBackRight.xRot = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount;*/
    }

    @Override
    public void renderToBuffer(@NotNull PoseStack matrixStack, @NotNull VertexConsumer buffer, int packedLight,
                               int packedOverlay, float red, float green, float blue, float alpha) {
        body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}