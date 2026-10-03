package net.spoiledz.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.spoiledz.util.SpoiledUtil;

@Mixin(ItemEntity.class)
public class ItemEntityMixin {

    @Inject(method = "tick", at = @At("TAIL"))
    private void spoiledzTickMixin(CallbackInfo info) {
        ItemEntity self = (ItemEntity) (Object) this;
        if (!self.getWorld().isClient() && self.getWorld().getTime() % 100 == 0) {
            ItemStack stack = self.getStack();
            if (SpoiledUtil.isFullySpoiled(self.getWorld(), stack)) {
                self.setStack(SpoiledUtil.getRottenFleshReplacement(stack));
            }
        }
    }

}
