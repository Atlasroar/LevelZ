package net.partyaddon.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "partyaddon")
@Config.Gui.Background("minecraft:textures/block/stone.png")
public class PartyAddonConfig implements ConfigData {

    // public int test0x = 0;
    // public int test1x = 0;
    // public int test2x = 0;
    // public int test3x = 0;
    // public int test4x = 0;
    // public int test5x = 0;
    // public int test6x = 0;

    // public int test0 = 0;
    // public int test1 = 0;
    // public int test2 = 0;
    // public int test3 = 0;
    // public int test4 = 0;
    // public int test5 = 0;
    // public int test6 = 0;
    // public int test7 = 0;
    // public int test8 = 0;
    // public int test9 = 0;
    // public int test10 = 0;
    // public int test11 = 0;
    // public int test12 = 0;
    // public int test13 = 0;
    // public int test14 = 0;
    // public int test15 = 0;
    // public int test16 = 0;

    public boolean distributeVanillaXP = true;
    @Comment("WandererZ compatibility")
    public boolean distributeLevelZXP = true;
    @Comment("Time in ticks")
    public int invitationTime = 1200;
    public int groupSize = 7;
    public float groupXpBonus = 0.05f;
    public float fullGroupBonus = 0.5f;

    public boolean showGroupHud = true; // Client only
    public int hudMaxWidth = 55; // Client only
    public float hudOpacity = 0.75f; // Client only
    public int hudPosX = 0; // Client only
    public int hudPosY = 0; // Client only
}