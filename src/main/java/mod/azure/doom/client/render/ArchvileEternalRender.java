package mod.azure.doom.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import mod.azure.doom.client.models.ArchvileEternalModel;
import mod.azure.doom.entity.tiersuperheavy.ArchvileEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class ArchvileEternalRender extends GeoEntityRenderer<ArchvileEntity> {

	public ArchvileEternalRender(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new ArchvileEternalModel());
		this.shadowRadius = 0.7F;
	}

	@Override
	public RenderType getRenderType(ArchvileEntity animatable, float partialTicks, MatrixStack stack,
			IRenderTypeBuffer renderTypeBuffer, IVertexBuilder vertexBuilder, int packedLightIn,
			ResourceLocation textureLocation) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}

	@Override
	protected int getBlockLightLevel(ArchvileEntity entityIn, BlockPos partialTicks) {
		return entityIn.isAttacking() ? 15 : 1;
	}

	@Override
	protected float getDeathMaxRotation(ArchvileEntity entityLivingBaseIn) {
		return 0;
	}

}