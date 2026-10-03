package net.spoiledz.compat.season;

import net.minecraft.world.World;

/**
 * Normalizes access to whichever season/calendar mod is installed (Fabric Seasons or Serene
 * Seasons) so the rest of SpoiledZ can compute spoilage timings without caring which provider is
 * backing the current season cycle.
 */
public interface SeasonProvider {

    /**
     * @return the current season id, one of "spring", "summer", "fall" or "winter" (matching
     *         {@link net.spoiledz.SpoiledZMain#SEASONS}).
     */
    String getSeasonId(World world);

    /**
     * @return the length of a single season, in ticks.
     */
    int getSeasonLengthTicks(World world);
}
