package mod.azure.doom.entity;

import java.util.Random;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import mod.azure.doom.entity.ai.goal.DemonAttackGoal;
import mod.azure.doom.entity.ai.goal.RangedStrafeAttackGoal;
import mod.azure.doom.entity.attack.FireballAttack;
import mod.azure.doom.util.config.Config;
import mod.azure.doom.util.config.EntityConfig;
import mod.azure.doom.util.config.EntityDefaults.EntityConfigType;
import net.minecraft.block.BlockState;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.ResetAngerGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class ProwlerEntity extends DemonEntity implements IAnimatable {

	private static final DataParameter<Boolean> ATTACKING = EntityDataManager.defineId(ProwlerEntity.class,
			DataSerializers.BOOLEAN);

	private AnimationFactory factory = new AnimationFactory(this);
	private int targetChangeTime;

	public static EntityConfig config = Config.SERVER.entityConfig.get(EntityConfigType.PROWLER);

	public ProwlerEntity(EntityType<? extends ProwlerEntity> entityType, World worldIn) {
		super(entityType, worldIn);
	}

	@Override
	public void registerControllers(AnimationData data) {
		data.addAnimationController(new AnimationController<ProwlerEntity>(this, "controller", 0, this::predicate));
	}

	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		if (event.isMoving() && !this.entityData.get(ATTACKING)) {
			event.getController().setAnimation(new AnimationBuilder().addAnimation("walking", true));
			return PlayState.CONTINUE;
		}
		if (this.entityData.get(ATTACKING) && !(this.dead || this.getHealth() < 0.01 || this.isDeadOrDying())) {
			event.getController().setAnimation(new AnimationBuilder().addAnimation("attack", false));
			return PlayState.CONTINUE;
		}
		if ((this.dead || this.getHealth() < 0.01 || this.isDeadOrDying())) {
			if (level.isClientSide) {
				event.getController().setAnimation(new AnimationBuilder().addAnimation("death", false));
				return PlayState.CONTINUE;
			}
		}
		event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
		return PlayState.CONTINUE;
	}

	@Override
	public AnimationFactory getFactory() {
		return this.factory;
	}

	@Override
	protected void tickDeath() {
		++this.deathTime;
		if (this.deathTime == 50) {
			this.remove();
			if (level.isClientSide) {
			}
		}
	}

	@OnlyIn(Dist.CLIENT)
	public boolean isAttacking() {
		return this.entityData.get(ATTACKING);
	}

	@Override
	public void setAttacking(boolean attacking) {
		this.entityData.set(ATTACKING, attacking);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(ATTACKING, false);
	}

	@Override
	public IPacket<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	public static boolean spawning(EntityType<ProwlerEntity> p_223337_0_, IWorld p_223337_1_,
			SpawnReason reason, BlockPos p_223337_3_, Random p_223337_4_) {
		return passPeacefulAndYCheck(config, p_223337_1_, reason, p_223337_3_, p_223337_4_);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(4,
				new RangedStrafeAttackGoal(this,
						new FireballAttack(this, false).setProjectileOriginOffset(0.8, 0.8, 0.8).setDamage(4).setSound(
								SoundEvents.BLAZE_SHOOT, 1.0F, 1.4F + this.getRandom().nextFloat() * 0.35F),
						1.0D, 50, 30, 15, 15F).setMultiShot(5, 3));
		this.goalSelector.addGoal(4, new DemonAttackGoal(this, 1.0D, false));
		this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
		this.targetSelector.addGoal(1, new ProwlerEntity.FindPlayerGoal(this, this::isAngryAt));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, true));
		this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(4, new ResetAngerGoal<>(this, false));
	}

	static class FindPlayerGoal extends NearestAttackableTargetGoal<PlayerEntity> {
		private final ProwlerEntity enderman;
		private PlayerEntity pendingTarget;
		private int aggroTime;
		private int teleportTime;
		private final EntityPredicate startAggroTargetConditions;
		private final EntityPredicate continueAggroTargetConditions = (new EntityPredicate()).allowUnseeable();

		public FindPlayerGoal(ProwlerEntity p_i241912_1_, @Nullable Predicate<LivingEntity> p_i241912_2_) {
			super(p_i241912_1_, PlayerEntity.class, 10, false, false, p_i241912_2_);
			this.enderman = p_i241912_1_;
			this.startAggroTargetConditions = (new EntityPredicate()).range(this.getFollowDistance())
					.selector((p_220790_1_) -> {
						return p_i241912_1_.isLookingAtMe((PlayerEntity) p_220790_1_);
					});
		}

		public boolean canUse() {
			this.pendingTarget = this.enderman.level.getNearestPlayer(this.startAggroTargetConditions, this.enderman);
			return this.pendingTarget != null;
		}

		public void start() {
			this.aggroTime = 5;
			this.teleportTime = 0;
		}

		public void stop() {
			this.pendingTarget = null;
			super.stop();
		}

		public boolean canContinueToUse() {
			if (this.pendingTarget != null) {
				this.enderman.lookAt(this.pendingTarget, 10.0F, 10.0F);
				return true;
			} else {
				return this.target != null && this.continueAggroTargetConditions.test(this.enderman, this.target) ? true
						: super.canContinueToUse();
			}
		}

		public void tick() {
			if (this.enderman.getTarget() == null) {
				super.setTarget((LivingEntity) null);
			}

			if (this.pendingTarget != null) {
				if (--this.aggroTime <= 0) {
					this.target = this.pendingTarget;
					this.pendingTarget = null;
					super.start();
				}
			} else {
				if (this.target != null && !this.enderman.isPassenger()) {
					if (this.target.distanceToSqr(this.enderman) > 256.0D && this.teleportTime++ >= 30
							&& this.enderman.teleportTowards(this.target)) {
						this.teleportTime = 0;
					}
				}

				super.tick();
			}

		}
	}

	private boolean isLookingAtMe(PlayerEntity p_70821_1_) {
		Vector3d vector3d = p_70821_1_.getViewVector(1.0F).normalize();
		Vector3d vector3d1 = new Vector3d(this.getX() - p_70821_1_.getX(), this.getEyeY() - p_70821_1_.getEyeY(),
				this.getZ() - p_70821_1_.getZ());
		double d0 = vector3d1.length();
		vector3d1 = vector3d1.normalize();
		double d1 = vector3d.dot(vector3d1);
		return d1 > 1.0D - 0.025D / d0 ? p_70821_1_.canSee(this) : false;
	}

	@Override
	public void aiStep() {
		if (this.level.isClientSide) {
			for (int i = 0; i < 2; ++i) {
				this.level.addParticle(ParticleTypes.PORTAL, this.getRandomX(0.5D), this.getRandomY() - 0.25D,
						this.getRandomZ(0.5D), (this.random.nextDouble() - 0.5D) * 2.0D, -this.random.nextDouble(),
						(this.random.nextDouble() - 0.5D) * 2.0D);
			}
		}

		this.jumping = false;
		if (!this.level.isClientSide) {
			this.updatePersistentAnger((ServerWorld) this.level, true);
		}

		super.aiStep();
	}

	@Override
	protected void customServerAiStep() {
		if (this.level.isDay() && this.tickCount >= this.targetChangeTime + 600) {
			float f = this.getBrightness();
			if (f > 0.5F && this.level.canSeeSky(this.blockPosition())
					&& this.random.nextFloat() * 30.0F < (f - 0.4F) * 2.0F) {
				this.setTarget((LivingEntity) null);
				this.teleport();
			}
		}

		super.customServerAiStep();
	}

	protected boolean teleport() {
		if (!this.level.isClientSide() && this.isAlive()) {
			double d0 = this.getX() + (this.random.nextDouble() - 0.5D) * 64.0D;
			double d1 = this.getY() + (double) (this.random.nextInt(64) - 32);
			double d2 = this.getZ() + (this.random.nextDouble() - 0.5D) * 64.0D;
			return this.teleport(d0, d1, d2);
		} else {
			return false;
		}
	}

	private boolean teleportTowards(Entity p_70816_1_) {
		Vector3d vector3d = new Vector3d(this.getX() - p_70816_1_.getX(), this.getY(0.5D) - p_70816_1_.getEyeY(),
				this.getZ() - p_70816_1_.getZ());
		vector3d = vector3d.normalize();
		double d1 = this.getX() + (this.random.nextDouble() - 0.5D) * 8.0D - vector3d.x * 16.0D;
		double d2 = this.getY() + (double) (this.random.nextInt(16) - 8) - vector3d.y * 16.0D;
		double d3 = this.getZ() + (this.random.nextDouble() - 0.5D) * 8.0D - vector3d.z * 16.0D;
		return this.teleport(d1, d2, d3);
	}

	private boolean teleport(double p_70825_1_, double p_70825_3_, double p_70825_5_) {
		BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable(p_70825_1_, p_70825_3_, p_70825_5_);
		while (blockpos$mutable.getY() > 0
				&& !this.level.getBlockState(blockpos$mutable).getMaterial().blocksMotion()) {
			blockpos$mutable.move(Direction.DOWN);
		}

		BlockState blockstate = this.level.getBlockState(blockpos$mutable);
		boolean flag = blockstate.getMaterial().blocksMotion();
		boolean flag1 = blockstate.getFluidState().is(FluidTags.WATER);
		if (flag && !flag1) {
			net.minecraftforge.event.entity.living.EnderTeleportEvent event = new net.minecraftforge.event.entity.living.EnderTeleportEvent(
					this, p_70825_1_, p_70825_3_, p_70825_5_, 0);
			if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event))
				return false;
			boolean flag2 = this.randomTeleport(event.getTargetX(), event.getTargetY(), event.getTargetZ(), true);
			return flag2;
		} else {
			return false;
		}
	}

	public static AttributeModifierMap.MutableAttribute createAttributes() {
		return config.pushAttributes(MobEntity.createMobAttributes().add(Attributes.FOLLOW_RANGE, 50.0D));
	}

	@Override
	public CreatureAttribute getMobType() {
		return CreatureAttribute.UNDEAD;
	}

	@Override
	public int getMaxSpawnClusterSize() {
		return 1;
	}

}