package net.partyaddon.mixin;

import java.util.Optional;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.partyaddon.access.GroupManagerAccess;
import net.partyaddon.group.GroupManager;
import net.partyaddon.network.PartyAddonServerPacket;

@Mixin(PlayerManager.class)
public class PlayerManagerMixin {

    @Inject(method = "remove", at = @At("HEAD"))
    private void removeMixin(ServerPlayerEntity player, CallbackInfo info) {
        GroupManager.leaveGroup(player, false);
    }

    // Required
    @Inject(method = "respawnPlayer", at = @At("TAIL"), locals = LocalCapture.CAPTURE_FAILSOFT)
    private void respawnPlayerMixin(ServerPlayerEntity player, boolean alive, CallbackInfoReturnable<ServerPlayerEntity> info, BlockPos blockPos, float f, boolean bl, ServerWorld serverWorld,
            Optional<Vec3d> optional2, ServerWorld serverWorld2, ServerPlayerEntity serverPlayerEntity) {
        PartyAddonServerPacket.writeS2CSyncGroupManagerPacket(serverPlayerEntity, ((GroupManagerAccess) player).getGroupManager());
    }

}
