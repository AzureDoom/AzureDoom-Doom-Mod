package mod.azure.doom.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import mod.azure.doom.client.models.WhiplashModel;
import mod.azure.doom.entity.tierheavy.WhiplashEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class WhiplashRender extends GeoEntityRenderer<WhiplashEntity> {

	public WhiplashRender(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new WhiplashModel());
	}

	@Override
	public RenderType getRenderType(WhiplashEntity animatable, float partialTicks, MatrixStack stack,
			IRenderTypeBuffer renderTypeBuffer, IVertexBuilder vertexBuilder, int packedLightIn,
			ResourceLocation textureLocation) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}

	@Override
	protected float getDeathMaxRotation(WhiplashEntity entityLivingBaseIn) {
		return 0.0F;
	}

}