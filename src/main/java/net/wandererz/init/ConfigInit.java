package net.wandererz.init;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.loader.api.FabricLoader;
import net.wandererz.config.WandererzConfig;
import net.wandererz.util.ConfigMigration;

public class ConfigInit {

    public static final boolean isOriginsLoaded = FabricLoader.getInstance().isModLoaded("origins");

    public static WandererzConfig CONFIG = new WandererzConfig();

    public static void init() {
        ConfigMigration.migrate("wandererz", "json5");
        AutoConfig.register(WandererzConfig.class, JanksonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(WandererzConfig.class).getConfig();
    }

}