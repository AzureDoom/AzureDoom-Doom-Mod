package mod.azure.doom.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import mod.azure.doom.client.DoomGeoEntityRenderer;
import mod.azure.doom.client.models.LostSoulModel;
import mod.azure.doom.entity.LostSoulEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class LostSoulRender extends DoomGeoEntityRenderer<LostSoulEntity> {

	public LostSoulRender(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new LostSoulModel());
	}

	@Override
	public RenderType getRenderType(LostSoulEntity animatable, float partialTicks, MatrixStack stack,
			IRenderTypeBuffer renderTypeBuffer, IVertexBuilder vertexBuilder, int packedLightIn,
			ResourceLocation textureLocation) {
		return RenderType.getEntityTranslucent(getTextureLocation(animatable));
	}

	@Override
	protected int getBlockLight(LostSoulEntity entityIn, float partialTicks) {
		return 15;
	}
}