package net.wandererz.util;

import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * One-time migration of legacy top-level config files (e.g. {@code config/wandererz.json5})
 * into the dedicated {@code config/WandererZ/} subfolder used from v2.6.0 onward.
 * Safe to call every launch: it's a no-op once the legacy file has been moved (or never existed).
 */
public class ConfigMigration {

    private static final Logger LOGGER = LoggerFactory.getLogger("WandererZ-ConfigMigration");

    public static void migrate(String fileName, String extension) {
        Path configDir = FabricLoader.getInstance().getConfigDir();
        Path legacyFile = configDir.resolve(fileName + "." + extension);
        Path newFile = configDir.resolve("WandererZ").resolve(fileName + "." + extension);

        if (!Files.exists(legacyFile) || Files.exists(newFile)) {
            return;
        }

        try {
            Files.createDirectories(newFile.getParent());
            Files.move(legacyFile, newFile, StandardCopyOption.REPLACE_EXISTING);
            LOGGER.info("Migrated legacy config {} to {}", legacyFile, newFile);
        } catch (IOException e) {
            LOGGER.warn("Failed to migrate legacy config {} to {}", legacyFile, newFile, e);
        }
    }

}
