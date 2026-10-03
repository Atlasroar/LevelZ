package net.partyaddon.init;

import com.blamejared.clumps.api.events.ClumpsEvents;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.player.PlayerEntity;
import net.partyaddon.access.GroupLeaderAccess;
import net.partyaddon.access.GroupManagerAccess;

import java.util.Objects;

public class EventInit {

    private static boolean isClumpsLoaded = FabricLoader.getInstance().isModLoaded("clumps");

    public static void init() {
        if (isClumpsLoaded) {
            ClumpsEvents.VALUE_EVENT.register(event -> {
                int amount = event.getValue();
                PlayerEntity player = event.getPlayer();

                if (ConfigInit.CONFIG.distributeVanillaXP && !((GroupManagerAccess) player).getGroupManager().getGroupPlayerIdList().isEmpty()) {
                    ((GroupLeaderAccess) Objects.requireNonNull(Objects.requireNonNull(player.getServer()).getPlayerManager().getPlayer
                            (((GroupManagerAccess) player).getGroupManager().getGroupLeaderId()))).addLeaderVanillaExperience(amount);
                    event.setValue(0);
                }
                return null;
            });
        }
    }

}
