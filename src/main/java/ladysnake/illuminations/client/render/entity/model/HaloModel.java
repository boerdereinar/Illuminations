package ladysnake.illuminations.client.render.entity.model;

import ladysnake.illuminations.client.Illuminations;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class HaloModel extends OverheadModel {
    public static final EntityModelLayer MODEL_LAYER = new EntityModelLayer(new Identifier(Illuminations.MODID, "halo"), "main");

    public HaloModel(EntityRendererFactory.Context ctx) {
        super(ctx, MODEL_LAYER);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData modelPartData1 = modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 7).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(-4.0f)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        modelPartData1.addChild("halo", ModelPartBuilder.create().uv(0, 0).cuboid(-8.0F, -11.0F, 5.0F, 16.0F, 16.0F, 0.0F), ModelTransform.pivot(0.0F, -4.0F, 0.0F));
        return TexturedModelData.of(modelData, 32, 48);
    }

    @Override
    public void render(MatrixStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        head.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelPart bone, float x, float y, float z) {
        bone.pitch = x;
        bone.yaw = y;
        bone.roll = z;
    }
}