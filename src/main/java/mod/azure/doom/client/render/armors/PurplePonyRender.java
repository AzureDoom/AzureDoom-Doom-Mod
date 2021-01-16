package mod.azure.doom.client.render.armors;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import mod.azure.doom.client.models.armor.PurplePonyModel;
import mod.azure.doom.item.armor.PurplePonyDoomArmor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderType;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class PurplePonyRender extends GeoArmorRenderer<PurplePonyDoomArmor> {
	public PurplePonyRender() {
		super(new PurplePonyModel());

		this.headBone = "armorHead";
		this.bodyBone = "armorBody";
		this.rightArmBone = "armorRightArm";
		this.leftArmBone = "armorLeftArm";
		this.rightLegBone = "armorRightLeg";
		this.leftLegBone = "armorLeftLeg";
		this.rightBootBone = "armorRightBoot";
		this.leftBootBone = "armorLeftBoot";
	}

	@Override
	public void render(GeoModel model, PurplePonyDoomArmor animatable, float partialTicks, RenderType type,
			MatrixStack matrixStackIn, IRenderTypeBuffer renderTypeBuffer, IVertexBuilder vertexBuilder,
			int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		super.render(model, animatable, partialTicks, type, matrixStackIn, renderTypeBuffer, vertexBuilder,
				packedLightIn, packedOverlayIn, red, green, blue, alpha);
		matrixStackIn.push();
		IRenderTypeBuffer.Impl irendertypebuffer$impl = Minecraft.getInstance().getRenderTypeBuffers()
				.getBufferSource();
		RenderHelper.setupGuiFlatDiffuseLighting();
		irendertypebuffer$impl.finish();
		RenderSystem.enableDepthTest();
		RenderHelper.setupGui3DDiffuseLighting();
		matrixStackIn.pop();
	}
}