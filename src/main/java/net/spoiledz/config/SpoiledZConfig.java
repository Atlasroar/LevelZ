package net.spoiledz.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "WandererZ/spoiledz")
@Config.Gui.Background("minecraft:textures/block/stone.png")
public class SpoiledZConfig implements ConfigData {

    @Comment("Season count for 100% spoilage")
    public int seasonSpoilage = 4;
    @Comment("On spoiled craft, reset spoiling time")
    public boolean freshCrafting = false;
    @Comment("In ticks. Negative effects are always applied (never random) once food has 25% freshness or less remaining")
    public int effectDuration = 400;

}