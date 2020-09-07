package mod.azure.doomweapon.client.models;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * Cyberdemon - Batpixxler Created using Tabula 8.0.0
 */
public class CyberdemonModel<T extends Entity> extends EntityModel<T> {
	public ModelRenderer thighs;
    public ModelRenderer rLeg1_flat;
    public ModelRenderer lLeg1_flat;
    public ModelRenderer torso;
    public ModelRenderer leftPec;
    public ModelRenderer neck;
    public ModelRenderer lShoulder;
    public ModelRenderer rShoulder;
    public ModelRenderer rightPec;
    public ModelRenderer head;
    public ModelRenderer rHorn1;
    public ModelRenderer topJaw;
    public ModelRenderer chin;
    public ModelRenderer lHorn1;
    public ModelRenderer rHorn2;
    public ModelRenderer rHorn3;
    public ModelRenderer rHorn4;
    public ModelRenderer rHorn5;
    public ModelRenderer snout;
    public ModelRenderer topTeethL;
    public ModelRenderer topTeethR;
    public ModelRenderer lowJaw;
    public ModelRenderer rFang;
    public ModelRenderer lFang;
    public ModelRenderer lowTeeth;
    public ModelRenderer lHorn2;
    public ModelRenderer lHorn3;
    public ModelRenderer lHorn4;
    public ModelRenderer lHorn5;
    public ModelRenderer lArm;
    public ModelRenderer lArm2;
    public ModelRenderer gunBase;
    public ModelRenderer gunBarrel;
    public ModelRenderer gunrim;
    public ModelRenderer armWires;
    public ModelRenderer rArm1;
    public ModelRenderer rArm2;
    public ModelRenderer rLeg1;
    public ModelRenderer rLegP1;
    public ModelRenderer rLeg2;
    public ModelRenderer rLegP2;
    public ModelRenderer rLeg3;
    public ModelRenderer rLeg3_2;
    public ModelRenderer rFoot;
    public ModelRenderer lLeg1;
    public ModelRenderer lLeg2;
    public ModelRenderer lLeg3;
    public ModelRenderer lFoot;

	public CyberdemonModel(float modelSize, boolean smallArmsIn) {
		this.textureWidth = 100;
        this.textureHeight = 200;
        this.lHorn5 = new ModelRenderer(this, 72, 173);
        this.lHorn5.setRotationPoint(0.0F, 6.0F, 0.0F);
        this.lHorn5.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(lHorn5, -0.591841146688116F, 0.0F, -0.0911061832922575F);
        this.topJaw = new ModelRenderer(this, 52, 137);
        this.topJaw.setRotationPoint(0.0F, 0.0F, -7.9F);
        this.topJaw.addBox(-5.0F, -5.0F, -3.0F, 10.0F, 5.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.rLeg3_2 = new ModelRenderer(this, 20, 88);
        this.rLeg3_2.setRotationPoint(0.0F, 7.0F, -2.0F);
        this.rLeg3_2.addBox(-2.5F, 0.0F, -1.5F, 5.0F, 3.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.topTeethL = new ModelRenderer(this, 0, 169);
        this.topTeethL.mirror = true;
        this.topTeethL.setRotationPoint(0.2F, -0.1F, -2.4F);
        this.topTeethL.addBox(0.0F, 0.0F, 0.0F, 4.0F, 1.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.snout = new ModelRenderer(this, 52, 145);
        this.snout.setRotationPoint(0.0F, -5.0F, -3.0F);
        this.snout.addBox(-4.5F, 0.0F, 0.0F, 9.0F, 2.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(snout, 0.5462880092689061F, 0.0F, 0.0F);
        this.lLeg3 = new ModelRenderer(this, 50, 47);
        this.lLeg3.setRotationPoint(0.0F, 13.0F, 4.5F);
        this.lLeg3.addBox(-2.5F, 0.0F, -4.0F, 5.0F, 11.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(lLeg3, -0.9105382388075086F, 0.0F, 0.0F);
        this.rLeg1 = new ModelRenderer(this, 0, 67);
        this.rLeg1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rLeg1.addBox(-3.5F, 0.0F, -3.5F, 7.0F, 14.0F, 7.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(rLeg1, -0.43022365395869955F, 0.0F, 0.0F);
        this.rLeg1_flat = new ModelRenderer(this, 0, 67);
        this.rLeg1_flat.setRotationPoint(-4.0F, -5.1F, 0.0F);
        this.rLeg1_flat.addBox(-3.5F, 0.0F, -3.5F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.lHorn1 = new ModelRenderer(this, 0, 173);
        this.lHorn1.setRotationPoint(5.0F, -5.0F, 0.0F);
        this.lHorn1.addBox(-3.0F, 0.0F, -3.0F, 6.0F, 7.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(lHorn1, -0.2275909337942703F, 0.0F, -1.3658947098950176F);
        this.armWires = new ModelRenderer(this, 0, 135);
        this.armWires.setRotationPoint(-6.0F, 0.0F, 2.0F);
        this.armWires.addBox(-5.0F, 0.0F, -4.0F, 5.0F, 14.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(armWires, 0.0F, 0.0F, -0.0911061832922575F);
        this.chin = new ModelRenderer(this, 52, 162);
        this.chin.setRotationPoint(0.0F, -0.1F, -8.0F);
        this.chin.addBox(-5.0F, 0.0F, -0.2F, 10.0F, 3.0F, 7.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(chin, 0.13665927909957545F, 0.0F, 0.0F);
        this.lFoot = new ModelRenderer(this, 48, 36);
        this.lFoot.setRotationPoint(0.0F, 10.0F, -2.0F);
        this.lFoot.addBox(-3.0F, 0.0F, -3.0F, 6.0F, 4.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(lFoot, 0.2275909337942703F, 0.0F, 0.0F);
        this.gunBase = new ModelRenderer(this, 24, 110);
        this.gunBase.mirror = true;
        this.gunBase.setRotationPoint(0.0F, 5.0F, -3.0F);
        this.gunBase.addBox(-3.5F, 0.0F, -4.0F, 7.0F, 9.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.lowJaw = new ModelRenderer(this, 52, 151);
        this.lowJaw.setRotationPoint(0.0F, 0.0F, 0.4F);
        this.lowJaw.addBox(-4.5F, 0.0F, -3.0F, 9.0F, 3.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(lowJaw, -0.2275909337942703F, 0.0F, 0.0F);
        this.torso = new ModelRenderer(this, 0, 0);
        this.torso.setRotationPoint(0.0F, -1.0F, 2.3F);
        this.torso.addBox(-9.5F, -13.0F, -12.0F, 19.0F, 12.0F, 12.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(torso, 0.04555309164612875F, 0.0F, 0.0F);
        this.rHorn4 = new ModelRenderer(this, 60, 173);
        this.rHorn4.setRotationPoint(0.0F, 5.7F, 0.0F);
        this.rHorn4.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 7.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(rHorn4, -0.6373942508178124F, 0.0F, 0.0911061832922575F);
        this.rArm1 = new ModelRenderer(this, 28, 94);
        this.rArm1.mirror = true;
        this.rArm1.setRotationPoint(-2.5F, 6.0F, 0.0F);
        this.rArm1.addBox(-3.0F, 0.0F, -3.0F, 6.0F, 10.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.rLegP1 = new ModelRenderer(this, 28, 64);
        this.rLegP1.setRotationPoint(-4.0F, 0.5F, 0.0F);
        this.rLegP1.addBox(0.0F, 0.0F, -3.0F, 1.0F, 14.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.rightPec = new ModelRenderer(this, 52, 124);
        this.rightPec.mirror = true;
        this.rightPec.setRotationPoint(-5.1F, -7.7F, -10.0F);
        this.rightPec.addBox(-5.0F, -5.0F, -3.0F, 10.0F, 10.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(rightPec, 0.0F, 0.0F, -0.08726646259971647F);
        this.lArm = new ModelRenderer(this, 28, 94);
        this.lArm.setRotationPoint(2.5F, 6.0F, 0.0F);
        this.lArm.addBox(-3.0F, 0.0F, -3.0F, 6.0F, 10.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.gunBarrel = new ModelRenderer(this, 54, 106);
        this.gunBarrel.setRotationPoint(0.0F, 9.0F, 0.0F);
        this.gunBarrel.addBox(-2.0F, 0.0F, -2.5F, 5.0F, 12.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.lLeg1 = new ModelRenderer(this, 0, 46);
        this.lLeg1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.lLeg1.addBox(-3.5F, 0.0F, -3.5F, 7.0F, 14.0F, 7.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(lLeg1, -0.43022365395869955F, 0.0F, 0.0F);
        this.rHorn3 = new ModelRenderer(this, 44, 173);
        this.rHorn3.setRotationPoint(0.0F, 5.7F, 0.0F);
        this.rHorn3.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(rHorn3, -0.45535640450848164F, 0.0F, 0.13665927909957545F);
        this.lArm2 = new ModelRenderer(this, 52, 94);
        this.lArm2.setRotationPoint(0.0F, 9.0F, 3.0F);
        this.lArm2.addBox(-3.0F, 0.0F, -6.0F, 6.0F, 6.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(lArm2, -0.27314400463445304F, 0.0F, 0.13665927909957545F);
        this.rHorn5 = new ModelRenderer(this, 72, 173);
        this.rHorn5.setRotationPoint(0.0F, 6.0F, 0.0F);
        this.rHorn5.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(rHorn5, -0.591841146688116F, 0.0F, 0.0911061832922575F);
        this.lLeg2 = new ModelRenderer(this, 28, 46);
        this.lLeg2.setRotationPoint(0.0F, 13.5F, -3.0F);
        this.lLeg2.addBox(-3.0F, 0.0F, -0.5F, 6.0F, 13.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(lLeg2, 1.092750655326294F, 0.0F, 0.0F);
        this.rFoot = new ModelRenderer(this, 36, 84);
        this.rFoot.setRotationPoint(0.0F, 9.0F, -2.0F);
        this.rFoot.addBox(-3.0F, 0.0F, -3.0F, 6.0F, 4.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(rFoot, 0.2275909337942703F, 0.0F, 0.0F);
        this.neck = new ModelRenderer(this, 18, 132);
        this.neck.setRotationPoint(0.0F, -11.9F, -4.0F);
        this.neck.addBox(-4.5F, -8.0F, -4.0F, 9.0F, 8.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(neck, 0.27314400463445304F, 0.0F, 0.0F);
        this.lHorn4 = new ModelRenderer(this, 60, 173);
        this.lHorn4.setRotationPoint(0.0F, 5.7F, 0.0F);
        this.lHorn4.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 7.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(lHorn4, -0.6373942508178124F, 0.0F, -0.0911061832922575F);
        this.rFang = new ModelRenderer(this, 0, 25);
        this.rFang.setRotationPoint(1.5F, 0.3F, -2.3F);
        this.rFang.addBox(0.0F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(rFang, -0.956091342937205F, 0.0F, 0.0F);
        this.lHorn2 = new ModelRenderer(this, 24, 173);
        this.lHorn2.setRotationPoint(0.0F, 5.9F, 0.0F);
        this.lHorn2.addBox(-2.5F, 0.0F, -2.5F, 5.0F, 7.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(lHorn2, -0.4098033003787853F, 0.0F, 0.0F);
        this.rLegP2 = new ModelRenderer(this, 28, 64);
        this.rLegP2.setRotationPoint(3.0F, 0.5F, 0.0F);
        this.rLegP2.addBox(0.0F, 0.0F, -3.0F, 1.0F, 14.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.rHorn1 = new ModelRenderer(this, 0, 173);
        this.rHorn1.setRotationPoint(-5.0F, -5.0F, 0.0F);
        this.rHorn1.addBox(-3.0F, 0.0F, -3.0F, 6.0F, 7.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(rHorn1, -0.2275909337942703F, 0.0F, 1.3658947098950176F);
        this.lShoulder = new ModelRenderer(this, 0, 99);
        this.lShoulder.setRotationPoint(7.5F, -12.0F, -6.0F);
        this.lShoulder.addBox(-1.0F, -2.0F, -4.0F, 7.0F, 8.0F, 7.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(lShoulder, -0.0F, 0.0F, -0.13665927909957545F);
        this.lHorn3 = new ModelRenderer(this, 44, 173);
        this.lHorn3.setRotationPoint(0.0F, 5.7F, 0.0F);
        this.lHorn3.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(lHorn3, -0.45535640450848164F, 0.0F, -0.13665927909957545F);
        this.head = new ModelRenderer(this, 7, 148);
        this.head.setRotationPoint(0.0F, -6.0F, 1.3F);
        this.head.addBox(-7.5F, -10.0F, -8.0F, 15.0F, 10.0F, 11.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(head, -0.27314400463445304F, 0.006283185556850939F, 0.0F);
        this.rShoulder = new ModelRenderer(this, 0, 99);
        this.rShoulder.mirror = true;
        this.rShoulder.setRotationPoint(-7.5F, -12.0F, -6.0F);
        this.rShoulder.addBox(-6.0F, -2.0F, -4.0F, 7.0F, 8.0F, 7.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(rShoulder, 0.0F, 0.0F, 0.13665927909957545F);
        this.leftPec = new ModelRenderer(this, 52, 124);
        this.leftPec.setRotationPoint(5.1F, -7.7F, -10.0F);
        this.leftPec.addBox(-5.0F, -5.0F, -3.0F, 10.0F, 10.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(leftPec, 0.0F, 0.0F, 0.08726646259971647F);
        this.rLeg2 = new ModelRenderer(this, 42, 64);
        this.rLeg2.setRotationPoint(0.0F, 13.5F, -3.0F);
        this.rLeg2.addBox(-2.5F, 0.0F, -0.5F, 5.0F, 11.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(rLeg2, 1.092750655326294F, 0.0F, 0.0F);
        this.rLeg3 = new ModelRenderer(this, 0, 88);
        this.rLeg3.setRotationPoint(0.0F, 11.0F, 4.5F);
        this.rLeg3.addBox(-3.0F, 0.0F, -4.0F, 6.0F, 7.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(rLeg3, -0.9105382388075086F, 0.0F, 0.0F);
        this.topTeethR = new ModelRenderer(this, 0, 169);
        this.topTeethR.setRotationPoint(-0.2F, -0.1F, -2.4F);
        this.topTeethR.addBox(-4.0F, 0.0F, 0.0F, 4.0F, 1.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.rHorn2 = new ModelRenderer(this, 24, 173);
        this.rHorn2.setRotationPoint(0.0F, 5.9F, 0.0F);
        this.rHorn2.addBox(-2.5F, 0.0F, -2.5F, 5.0F, 7.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(rHorn2, -0.4098033003787853F, 0.0F, 0.0F);
        this.lLeg1_flat = new ModelRenderer(this, 0, 46);
        this.lLeg1_flat.setRotationPoint(4.5F, -7.7F, 1.4F);
        this.lLeg1_flat.addBox(-3.5F, 0.0F, -3.5F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.lowTeeth = new ModelRenderer(this, 13, 169);
        this.lowTeeth.setRotationPoint(0.0F, -0.7F, -2.8F);
        this.lowTeeth.addBox(-4.0F, 0.0F, 0.0F, 8.0F, 1.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.gunrim = new ModelRenderer(this, 26, 128);
        this.gunrim.mirror = true;
        this.gunrim.setRotationPoint(1.0F, 11.5F, 0.0F);
        this.gunrim.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 1.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.lFang = new ModelRenderer(this, 0, 25);
        this.lFang.setRotationPoint(-2.5F, 0.3F, -2.3F);
        this.lFang.addBox(0.0F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(lFang, -0.956091342937205F, 0.0F, 0.0F);
        this.rArm2 = new ModelRenderer(this, 0, 114);
        this.rArm2.setRotationPoint(0.0F, 9.0F, 3.0F);
        this.rArm2.addBox(-3.0F, 0.0F, -6.0F, 6.0F, 15.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(rArm2, -0.27314400463445304F, 0.0F, -0.13665927909957545F);
        this.thighs = new ModelRenderer(this, 0, 24);
        this.thighs.setRotationPoint(0.0F, -14.0F, 3.5F);
        this.thighs.addBox(-7.0F, -2.0F, -8.0F, 14.0F, 12.0F, 10.0F, 0.25F, 0.25F, 0.25F);
        this.lHorn4.addChild(this.lHorn5);
        this.head.addChild(this.topJaw);
        this.rLeg3.addChild(this.rLeg3_2);
        this.topJaw.addChild(this.topTeethL);
        this.topJaw.addChild(this.snout);
        this.lLeg2.addChild(this.lLeg3);
        this.rLeg1_flat.addChild(this.rLeg1);
        this.head.addChild(this.lHorn1);
        this.rShoulder.addChild(this.armWires);
        this.head.addChild(this.chin);
        this.lLeg3.addChild(this.lFoot);
        this.lArm2.addChild(this.gunBase);
        this.chin.addChild(this.lowJaw);
        this.thighs.addChild(this.torso);
        this.rHorn3.addChild(this.rHorn4);
        this.rShoulder.addChild(this.rArm1);
        this.rLeg1.addChild(this.rLegP1);
        this.torso.addChild(this.rightPec);
        this.lShoulder.addChild(this.lArm);
        this.gunBase.addChild(this.gunBarrel);
        this.lLeg1_flat.addChild(this.lLeg1);
        this.rHorn2.addChild(this.rHorn3);
        this.lArm.addChild(this.lArm2);
        this.rHorn4.addChild(this.rHorn5);
        this.lLeg1.addChild(this.lLeg2);
        this.rLeg3.addChild(this.rFoot);
        this.torso.addChild(this.neck);
        this.lHorn3.addChild(this.lHorn4);
        this.lowJaw.addChild(this.rFang);
        this.lHorn1.addChild(this.lHorn2);
        this.rLeg1.addChild(this.rLegP2);
        this.head.addChild(this.rHorn1);
        this.torso.addChild(this.lShoulder);
        this.lHorn2.addChild(this.lHorn3);
        this.neck.addChild(this.head);
        this.torso.addChild(this.rShoulder);
        this.torso.addChild(this.leftPec);
        this.rLeg1.addChild(this.rLeg2);
        this.rLeg2.addChild(this.rLeg3);
        this.topJaw.addChild(this.topTeethR);
        this.rHorn1.addChild(this.rHorn2);
        this.lowJaw.addChild(this.lowTeeth);
        this.gunBarrel.addChild(this.gunrim);
        this.lowJaw.addChild(this.lFang);
        this.rArm1.addChild(this.rArm2);
	}

	@Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) { 
        ImmutableList.of(this.thighs, this.rLeg1_flat, this.lLeg1_flat).forEach((modelRenderer) -> { 
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	} 

	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {
		this.rShoulder.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / 1.0F;
		this.lShoulder.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount
				/ 1.0F;
		this.rLeg1_flat.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.0F * limbSwingAmount / 1.0F;
		this.lLeg1_flat.rotateAngleX = MathHelper.cos(limbSwing * -0.6662F + (float) Math.PI) * 1.0F * limbSwingAmount / 1.0F;
	}
}