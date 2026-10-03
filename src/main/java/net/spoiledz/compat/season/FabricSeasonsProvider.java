package net.spoiledz.compat.season;

import io.github.lucaargolo.seasons.FabricSeasons;
import net.minecraft.world.World;

/**
 * {@link SeasonProvider} backed by Fabric Seasons (io.github.lucaargolo). Only ever loaded/linked
 * when Fabric Seasons ("seasons") is present, per {@link SeasonProviderInit}.
 */
public class FabricSeasonsProvider implements SeasonProvider {

    @Override
    public String getSeasonId(World world) {
        return FabricSeasons.getCurrentSeason(world).asString();
    }

    @Override
    public int getSeasonLengthTicks(World world) {
        return FabricSeasons.getCurrentSeason(world).getSeasonLength();
    }
}
