package net.jobsaddon;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.jobsaddon.init.RenderInit;
import net.jobsaddon.network.JobsClientPacket;

@Environment(EnvType.CLIENT)
public class JobsAddonClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        if (!net.wandererz.init.ConfigInit.CONFIG.enableJobsAddon) {
            return;
        }
        RenderInit.init();
        JobsClientPacket.init();
    }

}
