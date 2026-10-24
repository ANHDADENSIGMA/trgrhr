package com.stalker.sixnights;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

/** Lightweight custom tall, narrow humanoid model. Uses the vanilla Enderman texture. */
public class StalkerModel extends HierarchicalModel<StalkerEntity> {
    public static final ModelLayerLocation LAYER = new ModelLayerLocation(new ResourceLocation(StalkerSixNights.MOD_ID, "stalker"), "main");
    private final ModelPart root, head, body, rightArm, leftArm, rightLeg, leftLeg;

    public StalkerModel(ModelPart root) {
        this.root = root;
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.rightArm = root.getChild("right_arm");
        this.leftArm = root.getChild("left_arm");
        this.rightLeg = root.getChild("right_leg");
        this.leftLeg = root.getChild("left_leg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F), PartPose.offset(0.0F, -14.0F, 0.0F));
        root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 16).addBox(-3.0F, 0.0F, -1.8F, 6.0F, 12.0F, 3.6F), PartPose.offset(0.0F, -6.0F, 0.0F));
        root.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(24, 16).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 17.0F, 3.0F), PartPose.offset(-4.0F, -5.0F, 0.0F));
        root.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(24, 16).mirror().addBox(-1.5F, 0.0F, -1.5F, 3.0F, 17.0F, 3.0F), PartPose.offset(4.0F, -5.0F, 0.0F));
        root.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 32).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 12.0F, 3.0F), PartPose.offset(-1.7F, 6.0F, 0.0F));
        root.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 32).mirror().addBox(-1.5F, 0.0F, -1.5F, 3.0F, 12.0F, 3.0F), PartPose.offset(1.7F, 6.0F, 0.0F));
        return LayerDefinition.create(mesh, 64, 64);
    }

    @Override public ModelPart root() { return root; }

    @Override
    public void setupAnim(StalkerEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        head.yRot = netHeadYaw * Mth.DEG_TO_RAD;
        head.xRot = headPitch * Mth.DEG_TO_RAD;
        rightArm.xRot = Mth.cos(limbSwing * 0.6662F + Mth.PI) * 1.0F * limbSwingAmount;
        leftArm.xRot = Mth.cos(limbSwing * 0.6662F) * 1.0F * limbSwingAmount;
        rightLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.1F * limbSwingAmount;
        leftLeg.xRot = Mth.cos(limbSwing * 0.6662F + Mth.PI) * 1.1F * limbSwingAmount;
        body.zRot = Mth.sin(ageInTicks * 0.045F) * 0.025F;
        if (entity.getTarget() != null) { rightArm.xRot -= 0.18F; leftArm.xRot -= 0.18F; }
    }
}
