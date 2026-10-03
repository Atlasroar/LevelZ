package net.wandererz;

import net.fabricmc.api.ModInitializer;
import net.wandererz.init.*;
import net.wandererz.network.PlayerStatsServerPacket;

public class WandererzMain implements ModInitializer {

    @Override
    public void onInitialize() {
        CommandInit.init();
        CompatInit.init();
        ConfigInit.init();
        CriteriaInit.init();
        EntityInit.init();
        EventInit.init();
        JsonReaderInit.init();
        PlayerStatsServerPacket.init();
        TagInit.init();
        ItemInit.init();
    }
}

// You are LOVED!!!
// Jesus loves you unconditionally!
