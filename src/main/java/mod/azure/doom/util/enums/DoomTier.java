package mod.azure.doom.util.enums;

import java.util.function.Supplier;

import mod.azure.doom.util.config.Config;
import mod.azure.doom.util.registry.DoomItems;
import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;

public enum DoomTier implements IItemTier {
	DOOM(18, 1561, 16.0F, 3.0F, 30, () -> {
		return Ingredient.fromItems(DoomItems.ARGENT_ENERGY.get());
	}), DOOM_HIGHTEIR(6, Config.SERVER.CRUCIBLE_MARAUDER_MAX_DAMAGE, 16.0F, 3.0F, 30, () -> {
		return Ingredient.fromItems(DoomItems.ARGENT_BLOCK.get());
	}), CHAINSAW(6, 600, 16.0F, 0.0F, 30, () -> {
		return Ingredient.fromItems(DoomItems.GAS_BARREL.get());
	}), PISTOL(6, 600, 16.0F, 0.0F, 30, () -> {
		return Ingredient.fromItems(DoomItems.BULLETS.get());
	}), BALLISTA(6, 600, 16.0F, 0.0F, 30, () -> {
		return Ingredient.fromItems(DoomItems.ARGENT_BOLT.get());
	}), BFG(6, 600, 16.0F, 0.0F, 30, () -> {
		return Ingredient.fromItems(DoomItems.BFG_CELL.get());
	}), CHAINGUN(6, 600, 16.0F, 0.0F, 30, () -> {
		return Ingredient.fromItems(DoomItems.CHAINGUN_BULLETS.get());
	}), PLASMA(6, 600, 16.0F, 0.0F, 30, () -> {
		return Ingredient.fromItems(DoomItems.ENERGY_CELLS.get());
	}), ROCKET(6, 600, 16.0F, 0.0F, 30, () -> {
		return Ingredient.fromItems(DoomItems.ROCKET.get());
	}), SHOTGUN(6, 600, 16.0F, 0.0F, 30, () -> {
		return Ingredient.fromItems(DoomItems.SHOTGUN_SHELLS.get());
	}), UNMAYKR(6, 600, 16.0F, 0.0F, 30, () -> {
		return Ingredient.fromItems(DoomItems.UNMAKRY_BOLT.get());
	});

	private final int harvestLevel;
	private final int maxUses;
	private final float efficiency;
	private final float attackDamage;
	private final int enchantability;
	private final LazyValue<Ingredient> repairMaterial;

	private DoomTier(int harvestLevelIn, int maxUsesIn, float efficiencyIn, float attackDamageIn, int enchantabilityIn,
			Supplier<Ingredient> repairMaterialIn) {
		this.harvestLevel = harvestLevelIn;
		this.maxUses = maxUsesIn;
		this.efficiency = efficiencyIn;
		this.attackDamage = attackDamageIn;
		this.enchantability = enchantabilityIn;
		this.repairMaterial = new LazyValue<>(repairMaterialIn);
	}

	public int getMaxUses() {
		return this.maxUses;
	}

	public float getEfficiency() {
		return this.efficiency;
	}

	public float getAttackDamage() {
		return this.attackDamage;
	}

	public int getHarvestLevel() {
		return this.harvestLevel;
	}

	public int getEnchantability() {
		return this.enchantability;
	}

	public Ingredient getRepairMaterial() {
		return this.repairMaterial.getValue();
	}

}