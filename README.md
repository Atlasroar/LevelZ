![WandererZ](docs/images/wandererz-title.png)

# WandererZ

WandererZ (formerly LevelZ) is a Fabric mod which adds player levels and skills, with a job system, RPG-style
mob difficulty scaling, and an item tiering/reforging system built directly in — no separate addon downloads
required.

### Features

- **Levels & Skills** — gain XP and level up skills (health, strength, agility, defense, stamina, luck,
  archery, trade, smithing, mining, farming, alchemy) that unlock blocks, items, and entities as you progress.
- **Jobs** — take on one of eight jobs (Miner, Farmer, Lumberjack, Fisher, Builder, Brewer, Smither, Warrior),
  earn job XP for job-specific actions, and level them up independently of your skills.
- **RPG Difficulty** — mobs get stronger with distance, time played, and height, with extra boss scaling and
  bonus XP/loot for tougher fights. Scaling is driven by your WandererZ player level.
- **Tiered** — tools, weapons, armor, and shields can randomly roll one of six rarity tiers (common through
  unique) with bonus attributes, plus an anvil-based reforging system to reroll an item's tier (modified by
  your smithing skill level and luck).
- **Deep configurability** — every system (leveling, jobs, difficulty scaling, tiering) ships its own config
  screen via Mod Menu / Cloth Config, and most default settings can be overridden per-world with datapacks.
- **Wide compatibility** — built-in support/compat hooks for Trinkets, REI, EMI, Jade, WTHIT, Origins,
  TreeChop, Farmer's Delight, FallingTree, Numismatic Overhaul, EasyAnvils, TooltipFix, and more.

### Installation

WandererZ is a mod built for the [Fabric Loader](https://fabricmc.net/). It requires
[Fabric API](https://www.curseforge.com/minecraft/mc-mods/fabric-api),
[Cloth Config API](https://www.curseforge.com/minecraft/mc-mods/cloth-config),
[LibZ](https://www.curseforge.com/minecraft/mc-mods/libz), and
[AutoTag](https://www.curseforge.com/minecraft/mc-mods/autotag) to be installed separately; all other
dependencies are optional compatibility hooks installed with the mod.

### License

WandererZ is licensed under GPLv3. The merged Jobs, RPG Difficulty, and Tiered systems retain their original
upstream license terms (GPLv3 and MIT respectively) in their source headers/notices.

### Documentation

Full player and developer documentation — including per-skill/job/difficulty/tiering configuration options, the
commands reference, and **datapack creation guides for each of the four merged mods** — lives on the
[project wiki](https://github.com/Atlasroar/LevelZ/wiki). Start with the
[Player Guide](https://github.com/Atlasroar/LevelZ/wiki/Player-Guide) or the
[Developer Guide](https://github.com/Atlasroar/LevelZ/wiki/Developer-Guide).

### Commands

`/playerstats playername add skill integer` — Increase the specific skill by the integer value\
`/playerstats playername remove skill integer` — Decrease the specific skill by the integer value\
`/playerstats playername set skill integer` — Set the specific skill to the integer value\
`/playerstats playername get skill` — Print the specific skill level\
`/jobmanager playername add jobname integer` — Increase the specific job by the integer value\
`/jobmanager playername remove jobname integer` — Decrease the specific job by the integer value\
`/jobmanager playername set jobname integer` — Set the specific job to the integer value\
`/jobmanager playername get jobname` — Print the specific job level\
`/info material` — Print the material string of the item in hand\
`/tiered tier playername common|uncommon|rare|epic|legendary|unique` — Force the player's held item to a tier\
`/tiered untier playername` — Remove the tier from the player's held item
