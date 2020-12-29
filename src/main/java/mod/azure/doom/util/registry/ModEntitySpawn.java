package mod.azure.doom.util.registry;

import java.util.List;
import java.util.Set;

import mod.azure.doom.DoomMod;
import mod.azure.doom.entity.ArachnotronEntity;
import mod.azure.doom.entity.ArchvileEntity;
import mod.azure.doom.entity.BaronEntity;
import mod.azure.doom.entity.CacodemonEntity;
import mod.azure.doom.entity.ChaingunnerEntity;
import mod.azure.doom.entity.Cyberdemon2016Entity;
import mod.azure.doom.entity.CyberdemonEntity;
import mod.azure.doom.entity.GargoyleEntity;
import mod.azure.doom.entity.GoreNestEntity;
import mod.azure.doom.entity.Hellknight2016Entity;
import mod.azure.doom.entity.HellknightEntity;
import mod.azure.doom.entity.IconofsinEntity;
import mod.azure.doom.entity.Imp2016Entity;
import mod.azure.doom.entity.ImpEntity;
import mod.azure.doom.entity.LostSoulEntity;
import mod.azure.doom.entity.MancubusEntity;
import mod.azure.doom.entity.MarauderEntity;
import mod.azure.doom.entity.MechaZombieEntity;
import mod.azure.doom.entity.NightmareImpEntity;
import mod.azure.doom.entity.PainEntity;
import mod.azure.doom.entity.PinkyEntity;
import mod.azure.doom.entity.PossessedScientistEntity;
import mod.azure.doom.entity.PossessedSoldierEntity;
import mod.azure.doom.entity.RevenantEntity;
import mod.azure.doom.entity.ShotgunguyEntity;
import mod.azure.doom.entity.SpiderdemonEntity;
import mod.azure.doom.entity.UnwillingEntity;
import mod.azure.doom.entity.ZombiemanEntity;
import mod.azure.doom.util.Config;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo.Spawners;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DoomMod.MODID)
public class ModEntitySpawn {

	@SubscribeEvent
	public static void onBiomeLoad(BiomeLoadingEvent event) {
		RegistryKey<Biome> key = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, event.getName());
		Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);
		List<Spawners> base = event.getSpawns().getSpawner(EntityClassification.MONSTER);
		if (types.contains(BiomeDictionary.Type.NETHER)) {
			base.add(new Spawners(ModEntityTypes.IMP.get(), Config.SERVER.COMMON_DEMON_SPAWN_WEIGHT.get(), 1, 4));
			base.add(new Spawners(ModEntityTypes.PINKY.get(), Config.SERVER.COMMON_DEMON_SPAWN_WEIGHT.get(), 1, 4));
			base.add(new Spawners(ModEntityTypes.LOST_SOUL.get(), Config.SERVER.COMMON_DEMON_SPAWN_WEIGHT.get(), 1, 4));
			base.add(new Spawners(ModEntityTypes.CACODEMON.get(), Config.SERVER.COMMON_DEMON_SPAWN_WEIGHT.get(), 1, 2));
			base.add(new Spawners(ModEntityTypes.ARCHVILE.get(), Config.SERVER.HEAVY_DEMON_SPAWN_WEIGHT.get(), 1, 2));
			base.add(new Spawners(ModEntityTypes.BARON.get(), Config.SERVER.HEAVY_DEMON_SPAWN_WEIGHT.get(), 1, 1));
			base.add(new Spawners(ModEntityTypes.MANCUBUS.get(), Config.SERVER.HEAVY_DEMON_SPAWN_WEIGHT.get(), 1, 1));
			base.add(new Spawners(ModEntityTypes.REVENANT.get(), Config.SERVER.HEAVY_DEMON_SPAWN_WEIGHT.get(), 1, 1));
			base.add(new Spawners(ModEntityTypes.SPIDERDEMON.get(), Config.SERVER.HEAVY_DEMON_SPAWN_WEIGHT.get(), 1, 1));
			base.add(new Spawners(ModEntityTypes.ZOMBIEMAN.get(), Config.SERVER.COMMON_DEMON_SPAWN_WEIGHT.get(), 1, 4));
			base.add(new Spawners(ModEntityTypes.NIGHTMARE_IMP.get(), Config.SERVER.COMMON_DEMON_SPAWN_WEIGHT.get(), 1, 4));
			base.add(new Spawners(ModEntityTypes.GARGOYLE.get(), Config.SERVER.COMMON_DEMON_SPAWN_WEIGHT.get(), 1, 4));
			base.add(new Spawners(ModEntityTypes.IMP2016.get(), Config.SERVER.COMMON_DEMON_SPAWN_WEIGHT.get(), 1, 4));
			base.add(new Spawners(ModEntityTypes.MECHAZOMBIE.get(), Config.SERVER.COMMON_DEMON_SPAWN_WEIGHT.get(), 1, 4));
			base.add(new Spawners(ModEntityTypes.ARACHNOTRON.get(), Config.SERVER.COMMON_DEMON_SPAWN_WEIGHT.get(), 1, 4));
			base.add(new Spawners(ModEntityTypes.CHAINGUNNER.get(), Config.SERVER.COMMON_DEMON_SPAWN_WEIGHT.get(), 1, 4));
			base.add(new Spawners(ModEntityTypes.SHOTGUNGUY.get(), Config.SERVER.COMMON_DEMON_SPAWN_WEIGHT.get(), 1, 4));
			base.add(new Spawners(ModEntityTypes.MARAUDER.get(), Config.SERVER.HEAVY_DEMON_SPAWN_WEIGHT.get(), 1, 1));
			base.add(new Spawners(ModEntityTypes.PAIN.get(), Config.SERVER.COMMON_DEMON_SPAWN_WEIGHT.get(), 1, 2));
			base.add(new Spawners(ModEntityTypes.HELLKNIGHT.get(), Config.SERVER.HEAVY_DEMON_SPAWN_WEIGHT.get(), 1, 1));
			base.add(new Spawners(ModEntityTypes.HELLKNIGHT2016.get(), Config.SERVER.HEAVY_DEMON_SPAWN_WEIGHT.get(), 1, 1));
			base.add(new Spawners(ModEntityTypes.CYBERDEMON.get(), Config.SERVER.HEAVY_DEMON_SPAWN_WEIGHT.get(), 1, 1));
			base.add(new Spawners(ModEntityTypes.UNWILLING.get(), Config.SERVER.COMMON_DEMON_SPAWN_WEIGHT.get(), 1, 4));
			base.add(new Spawners(ModEntityTypes.POSSESSEDSCIENTIST.get(),
					Config.SERVER.COMMON_DEMON_SPAWN_WEIGHT.get(), 1, 4));
			base.add(new Spawners(ModEntityTypes.POSSESSEDSOLDIER.get(), Config.SERVER.COMMON_DEMON_SPAWN_WEIGHT.get(), 1, 4));
			base.add(new Spawners(ModEntityTypes.GORE_NEST.get(), Config.SERVER.HEAVY_DEMON_SPAWN_WEIGHT.get(), 1, 1));
			base.add(new Spawners(ModEntityTypes.CYBERDEMON2016.get(), Config.SERVER.HEAVY_DEMON_SPAWN_WEIGHT.get(), 1, 1));
		}
	}

	public static void EntitySpawnPlacementRegistry() {
		EntitySpawnPlacementRegistry.register(ModEntityTypes.ARCHVILE.get(),
				EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				ArchvileEntity::spawning);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.LOST_SOUL.get(),
				EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				LostSoulEntity::spawning);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.ZOMBIEMAN.get(),
				EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				ZombiemanEntity::spawning);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.SPIDERDEMON.get(),
				EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				SpiderdemonEntity::spawning);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.ARACHNOTRON.get(),
				EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				ArachnotronEntity::spawning);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.MANCUBUS.get(),
				EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MancubusEntity::spawning);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.BARON.get(),
				EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				BaronEntity::spawning);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.REVENANT.get(),
				EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				RevenantEntity::spawning);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.IMP.get(),
				EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				ImpEntity::spawning);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.NIGHTMARE_IMP.get(),
				EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				NightmareImpEntity::spawning);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.PINKY.get(),
				EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				PinkyEntity::spawning);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.CACODEMON.get(),
				EntitySpawnPlacementRegistry.PlacementType.IN_LAVA, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				CacodemonEntity::spawning);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.IMP2016.get(),
				EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				Imp2016Entity::spawning);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.CHAINGUNNER.get(),
				EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				ChaingunnerEntity::spawning);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.MARAUDER.get(),
				EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MarauderEntity::spawning);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.SHOTGUNGUY.get(),
				EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				ShotgunguyEntity::spawning);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.PAIN.get(),
				EntitySpawnPlacementRegistry.PlacementType.IN_LAVA, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				PainEntity::spawning);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.HELLKNIGHT.get(),
				EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				HellknightEntity::spawning);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.HELLKNIGHT2016.get(),
				EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				Hellknight2016Entity::spawning);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.CYBERDEMON.get(),
				EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				CyberdemonEntity::func_223368_b);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.CYBERDEMON2016.get(),
				EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				Cyberdemon2016Entity::spawning);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.UNWILLING.get(),
				EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				UnwillingEntity::spawning);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.POSSESSEDSCIENTIST.get(),
				EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				PossessedScientistEntity::spawning);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.POSSESSEDSOLDIER.get(),
				EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				PossessedSoldierEntity::spawning);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.ICONOFSIN.get(),
				EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				IconofsinEntity::spawning);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.GORE_NEST.get(),
				EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				GoreNestEntity::spawning);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.MECHAZOMBIE.get(),
				EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MechaZombieEntity::spawning);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.GARGOYLE.get(),
				EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				GargoyleEntity::spawning);
	}
}