package net.wandererz.mixin.entity;

import java.util.ArrayList;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.wandererz.data.LevelLists;
import net.wandererz.stats.PlayerStatsManager;
import net.minecraft.entity.passive.AllayEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;

@Mixin(AllayEntity.class)
public class AllayEntityMixin {

    @Inject(method = "interactMob", at = @At(value = "HEAD"), cancellable = true)
    protected void interactMobMixin(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> info) {
        ArrayList<Object> levelList = LevelLists.allayList;
        if (!PlayerStatsManager.playerLevelisHighEnough(player, levelList, null, true)) {
            player.sendMessage(Text.translatable("item.wandererz." + levelList.get(0) + ".tooltip", levelList.get(1)).formatted(Formatting.RED), true);
            info.setReturnValue(ActionResult.FAIL);
        }
    }
}
