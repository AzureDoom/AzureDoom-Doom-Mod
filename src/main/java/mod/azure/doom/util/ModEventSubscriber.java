package mod.azure.doom.util;

import mod.azure.doom.DoomMod;
import mod.azure.doom.util.compat.BYGSpawns;
import mod.azure.doom.util.compat.BoPSpawns;
import mod.azure.doom.util.compat.ExtraModSpawns;
import mod.azure.doom.util.compat.NetherExSpawns;
import mod.azure.doom.util.registry.ModEntitySpawn;
import mod.azure.doom.util.registry.ModEntityTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.IForgeRegistryEntry;

@EventBusSubscriber(modid = DoomMod.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventSubscriber {

	@SubscribeEvent
	public static void registerEntities(final RegistryEvent.Register<EntityType<?>> event) {
		event.getRegistry().registerAll(ModEntityTypes.IMP.get(), ModEntityTypes.ARCHVILE.get(),
				ModEntityTypes.BARON.get(), ModEntityTypes.CACODEMON.get(), ModEntityTypes.MANCUBUS.get(),
				ModEntityTypes.GORE_NEST.get(), ModEntityTypes.PINKY.get(), ModEntityTypes.ARACHNOTRON.get(),
				ModEntityTypes.CYBERDEMON.get(), ModEntityTypes.CYBERDEMON2016.get(), ModEntityTypes.UNWILLING.get(),
				ModEntityTypes.IMP2016.get(), ModEntityTypes.NIGHTMARE_IMP.get(), ModEntityTypes.CHAINGUNNER.get(),
				ModEntityTypes.SHOTGUNGUY.get(), ModEntityTypes.MARAUDER.get(), ModEntityTypes.REVENANT.get(),
				ModEntityTypes.SPIDERDEMON.get(), ModEntityTypes.HELLKNIGHT.get(), ModEntityTypes.ICONOFSIN.get(),
				ModEntityTypes.POSSESSEDSOLDIER.get(), ModEntityTypes.BULLETS.get(),
				ModEntityTypes.CHAINGUN_BULLET.get(), ModEntityTypes.ENERGY_CELL.get(),
				ModEntityTypes.SHOTGUN_SHELL.get(), ModEntityTypes.ARGENT_BOLT.get(),
				ModEntityTypes.HELLKNIGHT2016.get(), ModEntityTypes.ENERGY_CELL_MOB.get(), ModEntityTypes.FIRING.get(),
				ModEntityTypes.ROCKET_MOB.get(), ModEntityTypes.MECHAZOMBIE.get(), ModEntityTypes.GARGOYLE.get(),
				ModEntityTypes.POSSESSEDSCIENTIST.get(), ModEntityTypes.ZOMBIEMAN.get(), ModEntityTypes.LOST_SOUL.get(),
				ModEntityTypes.BARENBLAST.get());
		ModEntitySpawn.registerEntityWorldSpawns();
		ModEntitySpawn.EntitySpawnPlacementRegistry();
		if (ModList.get().isLoaded("extendednether")) {
			ExtraModSpawns.registerEntityModWorldSpawns();
		}
		if (ModList.get().isLoaded("netherex")) {
			NetherExSpawns.registerEntityModWorldSpawns();
		}
		if (ModList.get().isLoaded("biomesoplenty")) {
			BoPSpawns.registerEntityModWorldSpawns();
		}
		if (ModList.get().isLoaded("byg")) {
			BYGSpawns.registerEntityModWorldSpawns();
		}
	}

	public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final String name) {
		return setup(entry, new ResourceLocation(DoomMod.MODID, name));
	}

	public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final ResourceLocation registryName) {
		entry.setRegistryName(registryName);
		return entry;
	}

}