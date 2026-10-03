package net.spoiledz.compat.season;

import java.lang.reflect.Method;
import java.util.Locale;

import net.minecraft.world.World;

/**
 * {@link SeasonProvider} backed by Serene Seasons (Glitchfiend), accessed entirely via
 * reflection. Serene Seasons' Fabric API is compiled against Mojang's official mappings rather
 * than Fabric intermediary, so its classes can't be referenced directly (modCompileOnly) from
 * this Yarn-mapped project; reflection avoids needing a compile-time dependency on it at all,
 * while still working at runtime since reflection resolves against the actual loaded classes.
 */
public class SereneSeasonsProvider implements SeasonProvider {

    private static final String SEASON_HELPER_CLASS = "sereneseasons.api.season.SeasonHelper";

    @Override
    public String getSeasonId(World world) {
        try {
            Object seasonState = getSeasonState(world);
            Object season = seasonState.getClass().getMethod("getSeason").invoke(seasonState);
            String seasonName = ((Enum<?>) season).name();
            // Serene Seasons calls autumn "AUTUMN" while SpoiledZ (matching Fabric Seasons'
            // naming) calls it "fall" - normalize so SpoiledZMain.SEASONS lookups match.
            if (seasonName.equals("AUTUMN")) {
                return "fall";
            }
            return seasonName.toLowerCase(Locale.ROOT);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("Failed to read the current season from Serene Seasons", e);
        }
    }

    @Override
    public int getSeasonLengthTicks(World world) {
        try {
            Object seasonState = getSeasonState(world);
            return (int) seasonState.getClass().getMethod("getSeasonDuration").invoke(seasonState);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("Failed to read the season length from Serene Seasons", e);
        }
    }

    private static Object getSeasonState(World world) throws ReflectiveOperationException {
        Class<?> seasonHelperClass = Class.forName(SEASON_HELPER_CLASS);
        for (Method method : seasonHelperClass.getMethods()) {
            if (method.getName().equals("getSeasonState") && method.getParameterCount() == 1) {
                return method.invoke(null, world);
            }
        }
        throw new NoSuchMethodException("SeasonHelper#getSeasonState(Level)");
    }
}
