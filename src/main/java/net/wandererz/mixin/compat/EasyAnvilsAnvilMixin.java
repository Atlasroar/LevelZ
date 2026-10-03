package net.wandererz.mixin.compat;

import java.util.ArrayList;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.At;

import net.wandererz.data.LevelLists;
import net.wandererz.stats.PlayerStatsManager;
import net.minecraft.block.AnvilBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Mixin(value = AnvilBlock.class, priority = 999)
public class EasyAnvilsAnvilMixin {

    @Inject(method = "onUse", at = @At("HEAD"), cancellable = true)
    private void onUseMixin(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<ActionResult> info) {
        ArrayList<Object> levelList = LevelLists.anvilList;
        if (!PlayerStatsManager.playerLevelisHighEnough(player, levelList, null, true)) {
            player.sendMessage(Text.translatable("item.wandererz." + levelList.get(0) + ".tooltip", levelList.get(1)).formatted(Formatting.RED), true);
            info.setReturnValue(ActionResult.FAIL);
        }
    }

}
