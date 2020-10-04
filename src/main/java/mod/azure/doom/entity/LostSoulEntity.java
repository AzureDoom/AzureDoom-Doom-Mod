package mod.azure.doom.entity;

import java.util.EnumSet;
import java.util.Random;

import javax.annotation.Nullable;

import mod.azure.doom.util.registry.ModEntityTypes;
import mod.azure.doom.util.registry.ModSoundEvents;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.Explosion;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class LostSoulEntity extends FlyingEntity implements IMob {

	public int explosionPower = 1;
	public int flameTimer;
	@Nullable
	private BlockPos boundOrigin;

	public LostSoulEntity(EntityType<? extends LostSoulEntity> type, World world) {
		super(type, world);
		this.moveController = new LostSoulEntity.MoveHelperController(this);
	}

	@OnlyIn(Dist.CLIENT)
	public LostSoulEntity(World worldIn, double x, double y, double z, double accelX, double accelY, double accelZ) {
		super(ModEntityTypes.LOST_SOUL.get(), worldIn);
	}

	public LostSoulEntity(World worldIn, LivingEntity shooter, double accelX, double accelY, double accelZ) {
		super(ModEntityTypes.LOST_SOUL.get(), worldIn);
	}

	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	public static AttributeModifierMap.MutableAttribute func_234200_m_() {
		return MobEntity.func_233666_p_().createMutableAttribute(Attributes.FOLLOW_RANGE, 50.0D)
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.25D)
				.createMutableAttribute(Attributes.ATTACK_DAMAGE, 1.0D)
				.createMutableAttribute(Attributes.MAX_HEALTH, 10.0D);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(8, new LostSoulEntity.LookAroundGoal(this));
		this.goalSelector.addGoal(8, new LostSoulEntity.MoveRandomGoal());
		this.goalSelector.addGoal(4, new LostSoulEntity.ChargeAttackGoal());
		this.targetSelector.addGoal(9, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
	}

	public static boolean spawning(EntityType<LostSoulEntity> p_223368_0_, IWorld p_223368_1_, SpawnReason reason,
			BlockPos p_223368_3_, Random p_223368_4_) {
		return p_223368_1_.getDifficulty() != Difficulty.PEACEFUL && p_223368_4_.nextInt(20) == 0
				&& canSpawnOn(p_223368_0_, p_223368_1_, reason, p_223368_3_, p_223368_4_);
	}

	public boolean isCharging() {
		return true;
	}

	public void setCharging(boolean charging) {
		return;
	}

	@Nullable
	public BlockPos getBoundOrigin() {
		return this.boundOrigin;
	}

	class MoveRandomGoal extends Goal {
		public MoveRandomGoal() {
			this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
		}

		public boolean shouldExecute() {
			return !LostSoulEntity.this.getMoveHelper().isUpdating() && LostSoulEntity.this.rand.nextInt(7) == 0;
		}

		public boolean shouldContinueExecuting() {
			return false;
		}

		public void tick() {
			BlockPos blockpos = LostSoulEntity.this.getBoundOrigin();
			if (blockpos == null) {
				blockpos = LostSoulEntity.this.getPosition();
			}
			for (int i = 0; i < 3; ++i) {
				BlockPos blockpos1 = blockpos.add(LostSoulEntity.this.rand.nextInt(15) - 7,
						LostSoulEntity.this.rand.nextInt(11) - 5, LostSoulEntity.this.rand.nextInt(15) - 7);
				if (LostSoulEntity.this.world.isAirBlock(blockpos1)) {
					LostSoulEntity.this.moveController.setMoveTo((double) blockpos1.getX() + 0.5D,
							(double) blockpos1.getY() + 0.5D, (double) blockpos1.getZ() + 0.5D, 0.25D);
					if (LostSoulEntity.this.getAttackTarget() == null) {
						LostSoulEntity.this.getLookController().setLookPosition((double) blockpos1.getX() + 0.5D,
								(double) blockpos1.getY() + 0.5D, (double) blockpos1.getZ() + 0.5D, 180.0F, 20.0F);
					}
					break;
				}
			}

		}
	}

	class ChargeAttackGoal extends Goal {
		public ChargeAttackGoal() {
			this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
		}

		public boolean shouldExecute() {
			if (LostSoulEntity.this.getAttackTarget() != null && !LostSoulEntity.this.getMoveHelper().isUpdating()
					&& LostSoulEntity.this.rand.nextInt(7) == 0) {
				return LostSoulEntity.this.getDistanceSq(LostSoulEntity.this.getAttackTarget()) > 4.0D;
			} else {
				return false;
			}
		}

		public boolean shouldContinueExecuting() {
			return LostSoulEntity.this.getMoveHelper().isUpdating() && LostSoulEntity.this.isCharging()
					&& LostSoulEntity.this.getAttackTarget() != null && LostSoulEntity.this.getAttackTarget().isAlive();
		}

		public void startExecuting() {
			LivingEntity livingentity = LostSoulEntity.this.getAttackTarget();
			Vector3d vec3d = livingentity.getEyePosition(1.0F);
			LostSoulEntity.this.moveController.setMoveTo(vec3d.x, vec3d.y, vec3d.z, 1.0D);
			LostSoulEntity.this.setCharging(true);
			LostSoulEntity.this.playSound(ModSoundEvents.LOST_SOUL_AMBIENT.get(), 1.0F, 1.0F);
		}

		public void resetTask() {
			LostSoulEntity.this.setCharging(false);
		}

		public void tick() {
			LivingEntity livingentity = LostSoulEntity.this.getAttackTarget();
			if (LostSoulEntity.this.getBoundingBox().intersects(livingentity.getBoundingBox())) {
				LostSoulEntity.this.attackEntityAsMob(livingentity);
				LostSoulEntity.this.setCharging(false);
			} else {
				double d0 = LostSoulEntity.this.getDistanceSq(livingentity);
				if (d0 < 9.0D) {
					Vector3d vec3d = livingentity.getEyePosition(1.0F);
					LostSoulEntity.this.moveController.setMoveTo(vec3d.x, vec3d.y, vec3d.z, 1.0D);
				}
			}

		}
	}

	@Override
	public void livingTick() {
		super.livingTick();
		flameTimer = (flameTimer + 1) % 8;
	}

	@Override
	protected boolean isDespawnPeaceful() {
		return true;
	}

	static class MoveHelperController extends MovementController {
		private final LostSoulEntity parentEntity;
		private int courseChangeCooldown;

		public MoveHelperController(LostSoulEntity ghast) {
			super(ghast);
			this.parentEntity = ghast;
		}

		public void tick() {
			if (this.action == MovementController.Action.MOVE_TO) {
				if (this.courseChangeCooldown-- <= 0) {
					this.courseChangeCooldown += this.parentEntity.getRNG().nextInt(5) + 2;
					Vector3d vector3d = new Vector3d(this.posX - this.parentEntity.getPosX(),
							this.posY - this.parentEntity.getPosY(), this.posZ - this.parentEntity.getPosZ());
					double d0 = vector3d.length();
					vector3d = vector3d.normalize();
					if (this.func_220673_a(vector3d, MathHelper.ceil(d0))) {
						this.parentEntity.setMotion(this.parentEntity.getMotion().add(vector3d.scale(0.1D)));
					} else {
						this.action = MovementController.Action.WAIT;
					}
				}

			}
		}

		private boolean func_220673_a(Vector3d p_220673_1_, int p_220673_2_) {
			AxisAlignedBB axisalignedbb = this.parentEntity.getBoundingBox();

			for (int i = 1; i < p_220673_2_; ++i) {
				axisalignedbb = axisalignedbb.offset(p_220673_1_);
				if (!this.parentEntity.world.hasNoCollisions(this.parentEntity, axisalignedbb)) {
					return false;
				}
			}

			return true;
		}
	}

	static class LookAroundGoal extends Goal {
		private final LostSoulEntity parentEntity;

		public LookAroundGoal(LostSoulEntity ghast) {
			this.parentEntity = ghast;
			this.setMutexFlags(EnumSet.of(Goal.Flag.LOOK));
		}

		public boolean shouldExecute() {
			return true;
		}

		public void tick() {
			if (this.parentEntity.getAttackTarget() == null) {
				Vector3d vec3d = this.parentEntity.getMotion();
				this.parentEntity.rotationYaw = -((float) MathHelper.atan2(vec3d.x, vec3d.z))
						* (180F / (float) Math.PI);
				this.parentEntity.renderYawOffset = this.parentEntity.rotationYaw;
			} else {
				LivingEntity livingentity = this.parentEntity.getAttackTarget();
				if (livingentity.getDistanceSq(this.parentEntity) < 4096.0D) {
					double d1 = livingentity.getPosX() - this.parentEntity.getPosX();
					double d2 = livingentity.getPosZ() - this.parentEntity.getPosZ();
					this.parentEntity.rotationYaw = -((float) MathHelper.atan2(d1, d2)) * (180F / (float) Math.PI);
					this.parentEntity.renderYawOffset = this.parentEntity.rotationYaw;
				}
			}

		}
	}

	protected boolean shouldBurnInDay() {
		return false;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return ModSoundEvents.PAIN_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return ModSoundEvents.PAIN_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSoundEvents.PAIN_DEATH.get();
	}

	@Override
	public CreatureAttribute getCreatureAttribute() {
		return CreatureAttribute.UNDEAD;
	}

	@Override
	protected float getSoundVolume() {
		return 1.0F;
	}

	protected void explode() {
		this.world.createExplosion(this, this.getPosX(), this.getPosYHeight(0.0625D), this.getPosZ(), 1.0F,
				Explosion.Mode.NONE);
	}

	public int getFlameTimer() {
		return flameTimer;
	}

}