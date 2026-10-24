package com.stalker.sixnights;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class StalkerRenderer extends MobRenderer<StalkerEntity, StalkerModel> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(StalkerSixNights.MOD_ID, "textures/entity/stalker.png");
    public StalkerRenderer(EntityRendererProvider.Context context) {
        super(context, new StalkerModel(context.bakeLayer(StalkerModel.LAYER)), 0.35F);
    }
    @Override public ResourceLocation getTextureLocation(StalkerEntity entity) { return TEXTURE; }
}
