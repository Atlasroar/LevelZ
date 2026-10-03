package net.wandererz;

import net.fabricmc.api.ClientModInitializer;
import net.wandererz.init.KeyInit;
import net.wandererz.init.RenderInit;
import net.wandererz.network.PlayerStatsClientPacket;

public class WandererzClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        KeyInit.init();
        PlayerStatsClientPacket.init();
        RenderInit.init();
    }

}