package mod.azure.doom.item.weapons;

import java.util.List;

import mod.azure.doom.DoomMod;
import mod.azure.doom.client.Keybindings;
import mod.azure.doom.util.enums.DoomTier;
import mod.azure.doom.util.packets.ChainsawLoadingPacket;
import mod.azure.doom.util.packets.DoomPacketHandler;
import mod.azure.doom.util.registry.DoomItems;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class Chainsaw extends Item {

	public Chainsaw() {
		super(new Item.Properties().group(DoomMod.DoomWeaponItemGroup).maxStackSize(1).maxDamage(600));
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return false;
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return DoomTier.CHAINSAW.getRepairMaterial().test(repair) || super.getIsRepairable(toRepair, repair);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TranslationTextComponent(
				"Fuel: " + (stack.getMaxDamage() - stack.getDamage() - 1) + " / " + (stack.getMaxDamage() - 1))
						.mergeStyle(TextFormatting.ITALIC));
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		LivingEntity user = (LivingEntity) entityIn;
		PlayerEntity player = (PlayerEntity) entityIn;
		final Vector3d facing = Vector3d.fromPitchYaw(user.getPitchYaw()).normalize();
		if (player.getHeldItemMainhand().isItemEqualIgnoreDurability(stack)
				&& stack.getDamage() < (stack.getMaxDamage() - 1)) {
			final AxisAlignedBB aabb = new AxisAlignedBB(entityIn.getPosition().up()).grow(1D, 1D, 1D)
					.offset(facing.scale(1D));
			entityIn.getEntityWorld().getEntitiesWithinAABBExcludingEntity(user, aabb).forEach(e -> doDamage(user, e));
			entityIn.getEntityWorld().getEntitiesWithinAABBExcludingEntity(user, aabb)
					.forEach(e -> damageItem(user, stack));
			entityIn.getEntityWorld().getEntitiesWithinAABBExcludingEntity(user, aabb).forEach(e -> addParticle(e));
		}
		if (worldIn.isRemote) {
			if (player.getHeldItemMainhand().isItemEqualIgnoreDurability(stack)) {
				while (Keybindings.RELOAD.isPressed() && isSelected) {
					DoomPacketHandler.CHAINSAW.sendToServer(new ChainsawLoadingPacket(itemSlot));
				}
			}
		}
	}

	public static void reload(PlayerEntity user, Hand hand) {
		if (user.getHeldItem(hand).getItem() instanceof Chainsaw) {
			while (user.getHeldItem(hand).getDamage() != 0 && user.inventory.count(DoomItems.GAS_BARREL.get()) > 0) {
				removeAmmo(DoomItems.GAS_BARREL.get(), user);
				user.getHeldItem(hand).damageItem(-1, user, s -> user.sendBreakAnimation(hand));
				user.getHeldItem(hand).setAnimationsToGo(3);
			}
		}
	}

	private static void removeAmmo(Item ammo, PlayerEntity playerEntity) {
		if (!playerEntity.isCreative()) {
			for (ItemStack item : playerEntity.inventory.mainInventory) {
				if (item.getItem() == DoomItems.GAS_BARREL.get()) {
					item.shrink(1);
					break;
				}
			}
		}
	}

	private void doDamage(LivingEntity user, final Entity target) {
		if (target instanceof LivingEntity) {
			target.attackEntityFrom(DamageSource.causePlayerDamage((PlayerEntity) user), 2F);
		}
	}

	private void damageItem(LivingEntity user, ItemStack stack) {
		PlayerEntity player = (PlayerEntity) user;
		if (!player.abilities.isCreativeMode) {
			stack.setDamage(stack.getDamage() + 1);
		}
	}

	private void addParticle(Entity target) {
		if (target instanceof LivingEntity) {
			target.world.addParticle(RedstoneParticleData.REDSTONE_DUST, target.getPosXRandom(0.5D),
					target.getPosYRandom(), target.getPosZRandom(0.5D), 0.0D, 0D, 0D);
		}
	}

}