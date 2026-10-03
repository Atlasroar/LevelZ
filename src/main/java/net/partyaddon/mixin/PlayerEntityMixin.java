package net.partyaddon.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import net.partyaddon.access.GroupManagerAccess;
import net.partyaddon.group.GroupManager;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements GroupManagerAccess {

    private GroupManager groupManager = new GroupManager((PlayerEntity) (Object) this);

    public PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    private void readCustomDataFromNbtMixin(NbtCompound nbt, CallbackInfo info) {
        this.groupManager.readNbt(nbt);
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    private void writeCustomDataToNbtMixin(NbtCompound nbt, CallbackInfo info) {
        this.groupManager.writeNbt(nbt);
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void tickMixin(CallbackInfo info) {
        groupManager.tick();
    }

    @Override
    public GroupManager getGroupManager() {
        return this.groupManager;
    }

    @Override
    public void setGroupManager(GroupManager groupManager) {
        this.groupManager = groupManager;
    }

    @Inject(method = "remove", at = @At("HEAD"))
    private void removeMixin(Entity.RemovalReason reason, CallbackInfo info) {
        if (!this.getWorld().isClient() && !reason.equals(RemovalReason.UNLOADED_WITH_PLAYER) && !this.groupManager.getGroupPlayerIdList().isEmpty()) {
            // this.groupManager.leaveGroup();
            // for (int i = 0; i < this.groupManager.getGroupPlayerIdList().size(); i++) {

            // }
            // PartyAddonServerPacket.writeS2CSyncGroupManagerPacket((ServerPlayerEntity) (Object) this);
        }
    }

}
