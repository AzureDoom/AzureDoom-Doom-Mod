package mod.azure.doom.util.packets.weapons;

import java.util.function.Supplier;

import mod.azure.doom.item.weapons.AxeMarauderItem;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.INetHandler;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.ServerPlayNetHandler;
import net.minecraft.util.Hand;
import net.minecraftforge.fml.network.NetworkEvent;

public class AxeMarauderLoadingPacket {

	public int slot;

	public AxeMarauderLoadingPacket(int slot) {
		this.slot = slot;
	}

	public AxeMarauderLoadingPacket(final PacketBuffer packetBuffer) {
		this.slot = packetBuffer.readInt();
	}

	public void encode(final PacketBuffer packetBuffer) {
		packetBuffer.writeInt(this.slot);
	}

	public static void handle(AxeMarauderLoadingPacket packet, Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(() -> {
			NetworkEvent.Context context = ctx.get();
			INetHandler handler = context.getNetworkManager().getPacketListener();
			if (handler instanceof ServerPlayNetHandler) {
				ServerPlayerEntity playerEntity = ((ServerPlayNetHandler) handler).player;
				AxeMarauderItem.reload(playerEntity, Hand.MAIN_HAND);
			}
		});
		ctx.get().setPacketHandled(true);
	}
}
