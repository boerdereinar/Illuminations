package ladysnake.illuminations.client.render.entity.feature;

import ladysnake.illuminations.client.Illuminations;
import ladysnake.illuminations.client.data.PlayerCosmeticData;
import ladysnake.illuminations.client.render.GlowyRenderLayer;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class DripFeatureRenderer extends FeatureRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {
    private static final Identifier dripTexture = new Identifier("illuminations", "textures/entity/drip.png");
    private static final Identifier dripColorTexture = new Identifier("illuminations", "textures/entity/drip_color.png");
    private final BipedEntityModel<AbstractClientPlayerEntity> playerModel;

    public DripFeatureRenderer(FeatureRendererContext<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> featureContext, EntityRendererFactory.Context registrationContext) {
        super(featureContext);
        playerModel = new BipedEntityModel<>(registrationContext.getPart(EntityModelLayers.PLAYER), RenderLayer::getEntitySolid);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, AbstractClientPlayerEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        PlayerCosmeticData cosmeticData = Illuminations.getCosmeticData(entity);
        if (cosmeticData == null || entity.isInvisible() || !cosmeticData.isDrip()) {
            return;
        }
        this.getContextModel().setAttributes(playerModel);
        playerModel.setVisible(false);
        playerModel.rightLeg.visible = true;
        playerModel.leftLeg.visible = true;
        float r = cosmeticData.getColorRed() / 255.0F;
        float g = cosmeticData.getColorGreen() / 255.0F;
        float b = cosmeticData.getColorBlue() / 255.0F;
        playerModel.render(matrices, ItemRenderer.getArmorGlintConsumer(vertexConsumers, RenderLayer.getArmorCutoutNoCull(dripTexture), false, false), light, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
        playerModel.render(matrices, vertexConsumers.getBuffer(GlowyRenderLayer.get(dripColorTexture)), 15728880, OverlayTexture.DEFAULT_UV, r, g, b, 1.0F);
    }

}
