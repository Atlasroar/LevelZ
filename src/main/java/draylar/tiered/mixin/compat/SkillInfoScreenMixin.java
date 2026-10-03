package draylar.tiered.mixin.compat;

import java.text.DecimalFormat;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import draylar.tiered.config.ConfigInit;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.text.Text;
import net.wandererz.screen.SkillInfoScreen;

@Environment(EnvType.CLIENT)
@Mixin(SkillInfoScreen.class)
public class SkillInfoScreenMixin {

    @Shadow
    @Mutable
    @Final
    private String title;
    @Shadow
    private Text translatableText1A = null;
    @Shadow
    private Text translatableText1B = null;

    @Inject(method = "Lnet/wandererz/screen/SkillInfoScreen;init()V", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z", ordinal = 0, shift = Shift.BEFORE))
    private void initMixin(CallbackInfo info) {
        if (title.equals("smithing")) {
            this.translatableText1A = Text.translatable("text.tiered.smithing_info_1_1", new DecimalFormat("0.0").format(ConfigInit.CONFIG.wandererzReforgeModifier * 100));
            this.translatableText1B = Text.translatable("text.tiered.smithing_info_1_2", new DecimalFormat("0.0").format(ConfigInit.CONFIG.wandererzReforgeModifier * 100));
        }
    }

}
