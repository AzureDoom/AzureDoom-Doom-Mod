package mod.azure.doomweapon.item.entityweapons;

import java.util.function.Predicate;

import mod.azure.doomweapon.entity.projectiles.EnergyCellEntity;
import mod.azure.doomweapon.item.ammo.EnergyCell;
import mod.azure.doomweapon.util.registry.DoomItems;
import mod.azure.doomweapon.util.registry.ModSoundEvents;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShootableItem;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ArchnotronAttackItem extends ShootableItem {

	public ArchnotronAttackItem() {
		super(new Item.Properties().maxStackSize(1));
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return false;
	}

	@Override
	public void onUse(World worldIn, LivingEntity livingEntityIn, ItemStack stack, int count) {
		if (livingEntityIn instanceof PlayerEntity) {
			PlayerEntity playerentity = (PlayerEntity) livingEntityIn;
			boolean flag = playerentity.abilities.isCreativeMode
					|| EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
			ItemStack itemstack = playerentity.findAmmo(stack);

			if (!itemstack.isEmpty() || flag) {
				if (itemstack.isEmpty()) {
					itemstack = new ItemStack(DoomItems.ENERGY_CELLS.get());
				}

				boolean flag1 = playerentity.abilities.isCreativeMode || (itemstack.getItem() instanceof EnergyCell
						&& ((EnergyCell) itemstack.getItem()).isInfinite(itemstack, stack, playerentity));
				if (!worldIn.isRemote) {
					EnergyCell arrowitem = (EnergyCell) (itemstack.getItem() instanceof EnergyCell
							? itemstack.getItem()
							: DoomItems.ENERGY_CELLS.get());
					EnergyCellEntity abstractarrowentity = arrowitem.createArrow(worldIn, itemstack, playerentity);
					abstractarrowentity = customeArrow(abstractarrowentity);
					abstractarrowentity.func_234612_a_(playerentity, playerentity.rotationPitch,
							playerentity.rotationYaw, 0.0F, 0.25F * 3.0F, 1.0F);

					abstractarrowentity.setNoGravity(true);

					stack.damageItem(1, playerentity, (p_220009_1_) -> {
						p_220009_1_.sendBreakAnimation(playerentity.getActiveHand());
					});
					if (flag1 || playerentity.abilities.isCreativeMode
							&& (itemstack.getItem() == DoomItems.ENERGY_CELLS.get()
									|| itemstack.getItem() == DoomItems.ENERGY_CELLS.get())) {
						abstractarrowentity.pickupStatus = AbstractArrowEntity.PickupStatus.DISALLOWED;
					}
					worldIn.addEntity(abstractarrowentity);
				}
				worldIn.playSound((PlayerEntity) null, playerentity.getPosX(), playerentity.getPosY(),
						playerentity.getPosZ(), ModSoundEvents.PLASMA_FIRING.get(), SoundCategory.PLAYERS, 1.0F,
						1.0F / (random.nextFloat() * 0.4F + 1.2F) + 0.25F * 0.5F);
				if (!flag1 && !playerentity.abilities.isCreativeMode) {
					itemstack.shrink(1);
					if (itemstack.isEmpty()) {
						playerentity.inventory.deleteStack(itemstack);
					}
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
	public UseAction getUseAction(ItemStack stack) {
		return UseAction.NONE;
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return 72000;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		boolean flag = !playerIn.findAmmo(itemstack).isEmpty();

		if (!playerIn.abilities.isCreativeMode && !flag) {
			return ActionResult.resultFail(itemstack);
		} else {
			playerIn.setActiveHand(handIn);
			return ActionResult.resultConsume(itemstack);
		}
	}

	@Override
	public Predicate<ItemStack> getInventoryAmmoPredicate() {
		return getAmmoPredicate();
	}

	@Override
	public Predicate<ItemStack> getAmmoPredicate() {
		return itemStack -> itemStack.getItem() instanceof EnergyCell;
	}

	public EnergyCellEntity customeArrow(EnergyCellEntity abstractarrowentity) {
		return abstractarrowentity;
	}

	@Override
	public int func_230305_d_() {
		return 15;
	}
}