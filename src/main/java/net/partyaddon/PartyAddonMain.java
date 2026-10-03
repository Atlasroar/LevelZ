package net.partyaddon;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.partyaddon.init.CommandInit;
import net.partyaddon.init.ConfigInit;
import net.partyaddon.init.EventInit;
import net.partyaddon.network.PartyAddonServerPacket;

public class PartyAddonMain implements ModInitializer {

    // JobsAddon and LevelZ are now bundled directly into WandererZ, so both checks collapse to one.
    public static final boolean isWandererzLoaded = FabricLoader.getInstance().isModLoaded("wandererz");

    @Override
    public void onInitialize() {
        if (!net.wandererz.init.ConfigInit.CONFIG.enablePartyAddon) {
            return;
        }
        ConfigInit.init();
        EventInit.init();
        CommandInit.init();
        PartyAddonServerPacket.init();
    }

}
