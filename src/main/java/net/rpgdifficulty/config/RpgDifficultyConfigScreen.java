package net.rpgdifficulty.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.gui.ConfigScreenProvider;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.rpgdifficulty.access.ScreenAccess;

// Builds the RpgDifficulty config screen, including the optional in-game-time clock widget.
// Factored out of a ModMenuApi entrypoint so it can be reused by LevelZ's combined config chooser.
@Environment(EnvType.CLIENT)
public class RpgDifficultyConfigScreen {

    @SuppressWarnings("deprecation")
    public static Screen create(Screen parent) {
        ConfigScreenProvider<RpgDifficultyConfig> supplier = (ConfigScreenProvider<RpgDifficultyConfig>) AutoConfig.getConfigScreen(RpgDifficultyConfig.class, parent);
        supplier.setBuildFunction(builder -> {
            builder.setAfterInitConsumer(screen -> {
                MinecraftClient minecraftClient = MinecraftClient.getInstance();
                if (minecraftClient.world != null)
                    ((ScreenAccess) screen).addAnotherDrawable(new ConfigClock(minecraftClient, screen.width - 35, 14));
            });
            return builder.build();
        });
        return supplier.get();
    }
}
