### Added:
- Renamed the project to **WandererZ** (formerly LevelZ) — new title branding, with Jobs and RPG Difficulty still fully integrated
- New player-facing and developer wiki documentation reflecting the WandererZ branding
- Added a dedicated "Datapack Creation" developer wiki page covering core, Jobs, and RPG Difficulty datapacks (moved out of the README)
### Changed:
- **Breaking:** mod ID changed from `levelz` to `wandererz` — the jar filename, Java package (`net.levelz` → `net.wandererz`), config file (`levelz.json5` → `wandererz.json5`), and resource namespaces (`assets/levelz`, `data/levelz` → `assets/wandererz`, `data/wandererz`) all changed accordingly
- Existing worlds/datapacks referencing the `levelz` namespace must be updated to `wandererz` (see the wiki's Datapack Creation page)
- Simplified the README to a features overview; detailed datapack creation docs moved to the wiki

## 1.6.0
### Added:
- Integrated RpgDifficulty directly into LevelZ (distance/time/height based mob strengthening, boss scaling, special zombies, extra XP/loot), no separate download required anymore
- RpgDifficulty's mob difficulty scaling now always uses LevelZ player levels instead of the optional compatibility path
- Added a third mod menu button to choose the RPG Difficulty config screen
### Changed:
- Bumped Fabric Loader and Fabric API to newer compatible versions
- Bumped Fabric Loom and the Gradle wrapper to versions compatible with RpgDifficulty's dependencies

## 1.5.0
### Added:
- Integrated JobsAddon directly into LevelZ (Miner, Farmer, Lumberjack, Fisher, Builder, Brewer, Smither and Warrior jobs), no separate download required anymore
- Added a mod menu screen to choose between the LevelZ and Jobs config screens
### Changed:
- Bumped Fabric Loader, Fabric API and Cloth Config to newer compatible versions