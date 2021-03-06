package mod.azure.doom.util.packets.weapons;

import java.util.function.Supplier;

import mod.azure.doom.item.weapons.SuperShotgun;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.INetHandler;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.ServerPlayNetHandler;
import net.minecraft.util.Hand;
import net.minecraftforge.fml.network.NetworkEvent;

public class SSGLoadingPacket {

	public int slot;

	public SSGLoadingPacket(int slot) {
		this.slot = slot;
	}

	public SSGLoadingPacket(final PacketBuffer packetBuffer) {
		this.slot = packetBuffer.readInt();
	}

	public void encode(final PacketBuffer packetBuffer) {
		packetBuffer.writeInt(this.slot);
	}

	public static void handle(SSGLoadingPacket packet, Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(() -> {
			NetworkEvent.Context context = ctx.get();
			INetHandler handler = context.getNetworkManager().getPacketListener();
			if (handler instanceof ServerPlayNetHandler) {
				ServerPlayerEntity playerEntity = ((ServerPlayNetHandler) handler).player;
				SuperShotgun.reload(playerEntity, Hand.MAIN_HAND);
			}
		});
		ctx.get().setPacketHandled(true);
	}
}
