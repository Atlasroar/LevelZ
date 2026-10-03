package net.spoiledz.compat.season;

import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.loader.api.FabricLoader;

/**
 * Selects which {@link SeasonProvider} SpoiledZ's food-spoilage timing should use at runtime.
 * Fabric Seasons ("seasons") is preferred when both are installed; falls back to Serene Seasons
 * ("sereneseasons") otherwise. Neither is a hard dependency - if neither is installed, spoilage
 * timing is disabled (items are treated as never-aging) rather than crashing.
 */
public class SeasonProviderInit {

    private static final Logger LOGGER = LoggerFactory.getLogger("SpoiledZ");

    @Nullable
    public static SeasonProvider PROVIDER;

    public static void init() {
        if (FabricLoader.getInstance().isModLoaded("seasons")) {
            PROVIDER = new FabricSeasonsProvider();
        } else if (FabricLoader.getInstance().isModLoaded("sereneseasons")) {
            PROVIDER = new SereneSeasonsProvider();
        } else {
            PROVIDER = null;
            LOGGER.warn("Neither Fabric Seasons nor Serene Seasons is installed - SpoiledZ's food spoilage timing will be disabled.");
        }
    }
}
