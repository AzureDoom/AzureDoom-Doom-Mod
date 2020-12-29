package mod.azure.doom.client.render.weapons;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import mod.azure.doom.client.models.weapons.BFG9000Model;
import mod.azure.doom.item.weapons.BFG9000;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType;
import net.minecraft.item.ItemStack;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class BFG9000Render extends GeoItemRenderer<BFG9000> {
	public BFG9000Render() {
		super(new BFG9000Model());
	}

	@SuppressWarnings("deprecation")
	@Override
	public void func_239207_a_(ItemStack itemStack, TransformType transformType, MatrixStack stack,
			IRenderTypeBuffer bufferIn, int combinedLightIn, int p_239207_6_) {
		if (transformType == ItemCameraTransforms.TransformType.GUI) {
			RenderSystem.pushMatrix();
			IRenderTypeBuffer.Impl irendertypebuffer$impl = Minecraft.getInstance().getRenderTypeBuffers()
					.getBufferSource();
			RenderHelper.setupGuiFlatDiffuseLighting();
			super.func_239207_a_(itemStack, transformType, stack, bufferIn, 15728880, p_239207_6_);
			irendertypebuffer$impl.finish();
			RenderSystem.enableDepthTest();
			RenderHelper.setupGui3DDiffuseLighting();
			RenderSystem.popMatrix();
		} else {
			super.func_239207_a_(itemStack, transformType, stack, bufferIn, combinedLightIn, p_239207_6_);
		}
	}
	
}