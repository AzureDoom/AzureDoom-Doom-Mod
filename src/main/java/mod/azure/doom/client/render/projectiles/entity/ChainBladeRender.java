package mod.azure.doom.client.render.projectiles.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import mod.azure.doom.client.models.projectiles.ChainBladeModel;
import mod.azure.doom.entity.projectiles.entity.ChainBladeEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;

public class ChainBladeRender extends GeoProjectilesRenderer<ChainBladeEntity> {

	public ChainBladeRender(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new ChainBladeModel());
	}

	protected int getBlockLightLevel(ChainBladeEntity entityIn, BlockPos partialTicks) {
		return 15;
	}

	@Override
	public RenderType getRenderType(ChainBladeEntity animatable, float partialTicks, MatrixStack stack,
			IRenderTypeBuffer renderTypeBuffer, IVertexBuilder vertexBuilder, int packedLightIn,
			ResourceLocation textureLocation) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}

}