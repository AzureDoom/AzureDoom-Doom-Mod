package mod.azure.doom.client.render.projectiles;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import mod.azure.doom.DoomMod;
import mod.azure.doom.entity.projectiles.entity.BarenBlastEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Matrix3f;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3f;

public class BarenBlastRender extends EntityRenderer<BarenBlastEntity> {

	private static final ResourceLocation ROCKET_TEXTURE = new ResourceLocation(DoomMod.MODID,
			"textures/entity/projectiles/baronblast.png");
	private static final RenderType RENDER_TYPE = RenderType.entityCutoutNoCull(ROCKET_TEXTURE);

	public BarenBlastRender(EntityRendererManager renderManagerIn) {
		super(renderManagerIn);
	}

	@Override
	public ResourceLocation getTextureLocation(BarenBlastEntity entity) {
		return ROCKET_TEXTURE;
	}

	protected int getBlockLightLevel(BarenBlastEntity entityIn, BlockPos partialTicks) {
		return 15;
	}

	public void render(BarenBlastEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int packedLightIn) {
		matrixStackIn.pushPose();
		matrixStackIn.scale(1.0F, 1.0F, 1.0F);
		matrixStackIn.mulPose(this.entityRenderDispatcher.cameraOrientation());
		matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(180.0F));
		MatrixStack.Entry matrixstack$entry = matrixStackIn.last();
		Matrix4f matrix4f = matrixstack$entry.pose();
		Matrix3f matrix3f = matrixstack$entry.normal();
		IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RENDER_TYPE);
		vertex(ivertexbuilder, matrix4f, matrix3f, packedLightIn, 0.0F, 0, 0, 1);
		vertex(ivertexbuilder, matrix4f, matrix3f, packedLightIn, 1.0F, 0, 1, 1);
		vertex(ivertexbuilder, matrix4f, matrix3f, packedLightIn, 1.0F, 1, 1, 0);
		vertex(ivertexbuilder, matrix4f, matrix3f, packedLightIn, 0.0F, 1, 0, 0);
		matrixStackIn.popPose();
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}

	private static void vertex(IVertexBuilder p_229045_0_, Matrix4f p_229045_1_, Matrix3f p_229045_2_,
			int p_229045_3_, float p_229045_4_, int p_229045_5_, int p_229045_6_, int p_229045_7_) {
		p_229045_0_.vertex(p_229045_1_, p_229045_4_ - 0.5F, (float) p_229045_5_ - 0.25F, 0.0F).color(255, 255, 255, 255)
				.uv((float) p_229045_6_, (float) p_229045_7_).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(p_229045_3_)
				.normal(p_229045_2_, 0.0F, 1.0F, 0.0F).endVertex();
	}

}