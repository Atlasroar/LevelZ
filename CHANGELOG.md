## 2.4.0
### Added:
- Added support for [Serene Seasons](https://modrinth.com/mod/serene-seasons) as an alternative season provider for the Food Spoilage system (alongside Fabric Seasons) — either one works, picked automatically at startup (Fabric Seasons preferred if both are installed)
### Changed:
- Spoilage timing now degrades gracefully instead of crashing if neither Fabric Seasons nor Serene Seasons is installed (spoilage is simply disabled, with a startup warning logged)
### Dependencies:
- Loosened Fabric Seasons from a required dependency to an optional one (`suggests`), now that Serene Seasons is a valid alternative
- Added optional soft-compat with Serene Seasons and its required [GlitchCore](https://modrinth.com/mod/glitchcore) library (accessed via reflection, since Serene Seasons' Fabric API is compiled against Mojang's official mappings rather than Yarn)

## 2.3.0
### Added:
- Integrated SpoiledZ directly into WandererZ (seasons-based food spoilage system: items spoil over time with cake, furnace/campfire/crafting-result, ground-item, and container tracking), no separate download required anymore
- Added a sixth mod menu button to choose the Food Spoilage config screen
- Added a "SpoiledZ datapacks" section to the Datapack Creation developer wiki page, plus new Player/Developer SpoiledZ wiki pages
### Changed:
- Added the `maven.siphalor.de` Maven repository (required to resolve the bundled Capsaicin dependency)
### Dependencies:
- Added a required separate dependency on [Fabric Seasons](https://modrinth.com/mod/fabric-seasons), which SpoiledZ's spoilage timing is based on
- Bundled [Capsaicin](https://github.com/Siphalor/capsaicin) directly into the jar (no separate install needed), providing the food-eaten/food-properties event hooks
- Added optional soft-compat with Expanded Delight and Vinery (Farmer's Delight compat was already present)

## 2.2.0
### Added:
- Integrated PartyAddon directly into WandererZ (player parties/groups, invite/join/leave/kick, shared vanilla and WandererZ XP distribution among group members, group HUD), no separate download required anymore
- Added a fifth mod menu button to choose the Party config screen
- Added `/party join` and `/party leave` commands
- Added a Player/Developer PartyAddon wiki page
### Changed:
- PartyAddon's shared XP distribution now always uses WandererZ player experience instead of the optional compatibility path
### Dependencies:
- Added optional soft-compat with Clumps (clumped XP orb sharing) and Xaero's World Map (group member map markers)

## 2.1.1
### Added:
- Explicit Mod Menu support: declared as a `suggests` dependency in `fabric.mod.json` so Mod Menu surfaces WandererZ as a recommended companion mod
### Changed:
- Bumped the (optional) Mod Menu dependency from 7.0.0 to 7.2.2, the latest release for Minecraft 1.20.1
- Documented Mod Menu in the README's compatibility/installation sections

## 2.1.0
### Added:
- Integrated Tiered directly into WandererZ (random item rarity tiers with bonus attributes, anvil reforging, Smithing-skill/Luck-weighted reroll odds, tooltip tier borders), no separate download required anymore
- Added a fourth mod menu button to choose the Tiered config screen
- Added `/tiered tier` and `/tiered untier` commands
- Added a "Tiered datapacks" section to the Datapack Creation developer wiki page, plus new Player/Developer Tiered wiki pages
### Changed:
- Tiered's Smithing-skill-weighted reforge bonus now always uses WandererZ player skills instead of the optional compatibility path
- Bumped Fabric Loom and added the `maven.willbl.dev` Maven repository (required to resolve the AutoTag dependency)
### Dependencies:
- Added a required separate dependency on [AutoTag](https://www.curseforge.com/minecraft/mc-mods/autotag)
- Bundled [Reach Entity Attributes](https://github.com/JamiesWhiteShirt/reach-entity-attributes) directly into the jar (no separate install needed)
- Added optional soft-compat with EasyAnvils and TooltipFix

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