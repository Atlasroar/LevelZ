package net.partyaddon.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.gui.hud.PlayerListHud;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.partyaddon.access.GroupManagerAccess;
import net.partyaddon.network.PartyAddonServerPacket;

@SuppressWarnings("unused")
@Mixin(ServerWorld.class)
public class ServerWorldMixin {

    // @Inject(method = "addPlayer", at = @At("TAIL"))
    // private void addPlayerMixin(ServerPlayerEntity player, CallbackInfo info) {
    // PartyAddonServerPacket.writeS2CSyncGroupManagerPacket(player);
    // }
}
