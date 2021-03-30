package mod.azure.doom.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import mod.azure.doom.client.models.ProwlerModel;
import mod.azure.doom.entity.ProwlerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class ProwlerRender extends GeoEntityRenderer<ProwlerEntity> {

	public ProwlerRender(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new ProwlerModel());
		this.shadowRadius = 0.7F;
	}

	@Override
	public RenderType getRenderType(ProwlerEntity animatable, float partialTicks, MatrixStack stack,
			IRenderTypeBuffer renderTypeBuffer, IVertexBuilder vertexBuilder, int packedLightIn,
			ResourceLocation textureLocation) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}
	
	@Override
	protected float getDeathMaxRotation(ProwlerEntity entityLivingBaseIn) {
		return 0;
	}

}