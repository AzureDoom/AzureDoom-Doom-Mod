package mod.azure.doom.item.weapons;

import java.util.List;

import mod.azure.doom.DoomMod;
import mod.azure.doom.client.Keybindings;
import mod.azure.doom.client.render.weapons.HeavyCannonRender;
import mod.azure.doom.entity.projectiles.BulletEntity;
import mod.azure.doom.util.enums.DoomTier;
import mod.azure.doom.util.packets.DoomPacketHandler;
import mod.azure.doom.util.packets.HeavyCannonLoadingPacket;
import mod.azure.doom.util.registry.DoomItems;
import mod.azure.doom.util.registry.ModSoundEvents;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class HeavyCannon extends Item implements IAnimatable {

	public AnimationFactory factory = new AnimationFactory(this);
	private String controllerName = "controller";

	private <P extends Item & IAnimatable> PlayState predicate(AnimationEvent<P> event) {
		return PlayState.CONTINUE;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void registerControllers(AnimationData data) {
		data.addAnimationController(new AnimationController(this, controllerName, 1, this::predicate));
	}

	@Override
	public AnimationFactory getFactory() {
		return this.factory;
	}

	public HeavyCannon() {
		super(new Item.Properties().group(DoomMod.DoomWeaponItemGroup).maxStackSize(1).maxDamage(201)
				.setISTER(() -> HeavyCannonRender::new));
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return DoomTier.PISTOL.getRepairMaterial().test(repair) || super.getIsRepairable(toRepair, repair);
	}

	@Override
	public void onUse(World worldIn, LivingEntity entityLiving, ItemStack stack, int count) {
		if (entityLiving instanceof PlayerEntity) {
			PlayerEntity playerentity = (PlayerEntity) entityLiving;
			if (stack.getDamage() < (stack.getMaxDamage() - 1) && !playerentity.getCooldownTracker().hasCooldown(this)) {
				playerentity.getCooldownTracker().setCooldown(this, 4);
				if (!worldIn.isRemote) {
					BulletEntity abstractarrowentity = createArrow(worldIn, stack, playerentity);
					abstractarrowentity = customeArrow(abstractarrowentity);
					abstractarrowentity.func_234612_a_(playerentity, playerentity.rotationPitch,
							playerentity.rotationYaw, 0.0F, 1.0F * 3.0F, 1.0F);

					abstractarrowentity.setDamage(3.0);
					abstractarrowentity.hasNoGravity();

					stack.damageItem(1, entityLiving, p -> p.sendBreakAnimation(entityLiving.getActiveHand()));
					worldIn.addEntity(abstractarrowentity);
					worldIn.playSound((PlayerEntity) null, playerentity.getPosX(), playerentity.getPosY(),
							playerentity.getPosZ(), ModSoundEvents.HEAVY_CANNON.get(), SoundCategory.PLAYERS, 1.0F, 1.0F);
				}
				AnimationController<?> controller = GeckoLibUtil.getControllerForStack(this.factory, stack,
						controllerName);

				if (controller.getAnimationState() == AnimationState.Stopped) {
					controller.markNeedsReload();
					controller.setAnimation(new AnimationBuilder().addAnimation("firing", false));
				}
			}
		}
	}

	public BulletEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {
		BulletEntity arrowentity = new BulletEntity(worldIn, shooter);
		return arrowentity;
	}

	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (worldIn.isRemote) {
			if (((PlayerEntity) entityIn).getHeldItemMainhand().getItem() instanceof HeavyCannon) {
				while (Keybindings.RELOAD.isPressed() && isSelected) {
					DoomPacketHandler.HEAVYCANNON.sendToServer(new HeavyCannonLoadingPacket(itemSlot));
				}
			}
		}
	}

	public static void reload(PlayerEntity user, Hand hand) {
		if (user.getHeldItemMainhand().getItem() instanceof HeavyCannon) {
			while (user.getHeldItem(hand).getDamage() != 0 && user.inventory.count(DoomItems.BULLETS.get()) > 0) {
				removeAmmo(DoomItems.BULLETS.get(), user);
				user.getHeldItemMainhand().damageItem(-10, user, s -> user.sendBreakAnimation(hand));
				user.getHeldItemMainhand().setAnimationsToGo(3);
			}
		}
	}

	private static void removeAmmo(Item ammo, PlayerEntity playerEntity) {
		if (!playerEntity.isCreative()) {
			for (ItemStack item : playerEntity.inventory.mainInventory) {
				if (item.getItem() == DoomItems.BULLETS.get()) {
					item.shrink(1);
					break;
				}
			}
		}
	}

	public static float getArrowVelocity(int charge) {
		float f = (float) charge / 20.0F;
		f = (f * f + f * 2.0F) / 3.0F;
		if (f > 1.0F) {
			f = 1.0F;
		}

		return f;
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return 72000;
	}

	@Override
	public UseAction getUseAction(ItemStack stack) {
		return UseAction.BLOCK;
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TranslationTextComponent(
				"Ammo: " + (stack.getMaxDamage() - stack.getDamage() - 1) + " / " + (stack.getMaxDamage() - 1))
						.mergeStyle(TextFormatting.ITALIC));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		playerIn.setActiveHand(handIn);
		return ActionResult.resultConsume(itemstack);
	}

	public BulletEntity customeArrow(BulletEntity arrow) {
		return arrow;
	}
}