package net.spoiledz.init;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.spoiledz.config.SpoiledZConfig;
import net.wandererz.util.ConfigMigration;

public class ConfigInit {

    public static SpoiledZConfig CONFIG = new SpoiledZConfig();

    public static void init() {
        ConfigMigration.migrate("spoiledz", "json5");
        AutoConfig.register(SpoiledZConfig.class, JanksonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(SpoiledZConfig.class).getConfig();
    }
}
