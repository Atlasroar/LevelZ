package draylar.tiered.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.wandererz.util.ConfigMigration;

public class ConfigInit {
    public static TieredConfig CONFIG = new TieredConfig();

    public static void init() {
        ConfigMigration.migrate("tiered", "json5");
        AutoConfig.register(TieredConfig.class, JanksonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(TieredConfig.class).getConfig();
    }

}
