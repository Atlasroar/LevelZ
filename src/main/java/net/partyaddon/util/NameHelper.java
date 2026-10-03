package net.partyaddon.util;

import java.util.UUID;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class NameHelper {

    // public static Text getPlayerName(World world, TextRenderer textRenderer, int playerId) {

    // if (world.getEntityById(playerId) != null) {

    // String playerName = world.getEntityById(playerId).getName().getString();
    // if (textRenderer.getWidth(playerName) > 55)
    // playerName = playerName.substring(0, 9) + "..";

    // // if (PartyAddonMain.isLevelZLoaded) {
    // // return Text.translatable("text.partyaddon.hud.name", ((PlayerStatsManagerAccess) world.getEntityById(playerId)).getPlayerStatsManager().getOverallLevel(), playerName);
    // // }
    // return Text.of(playerName);
    // }
    // return Text.translatable("text.partyaddon.hud.empty_name");
    // }

    public static Text getPlayerName(MinecraftClient client, UUID playerId, int maxWidth) {

        if (isPlayerUUIDNotNull(client, playerId)) {

            String playerName = client.getNetworkHandler().getPlayerListEntry(playerId).getProfile().getName();
            // if (client.textRenderer.getWidth(playerName) > length && substringLength != 0) {
            if (maxWidth != 0 && client.textRenderer.getWidth(playerName) > maxWidth) {

                // playerName.substring(0, playerName.lastIndexOf(playerName)); // get automatic length :D
                // WORK HERE!!!!!!!!!!!!!!
                // client.textRenderer.trimToWidth(text, maxWidth)
                // int test = client.textRenderer.getWidth(playerName) / length;

                // playerName = playerName.substring(0, playerName.lastIndexOf(playerName)) + "..";
                // playerName = playerName.substring(0, substringLength) + "..";
                return Text.of(client.textRenderer.trimToWidth(playerName, maxWidth) + "..");
            }

            // if (PartyAddonMain.isLevelZLoaded) {
            // return Text.translatable("text.partyaddon.hud.name", ((PlayerStatsManagerAccess) world.getEntityById(playerId)).getPlayerStatsManager().getOverallLevel(), playerName);
            // }
            return Text.of(playerName);
        }
        return Text.translatable("text.partyaddon.hud.empty_name");
    }

    public static boolean isPlayerUUIDNotNull(MinecraftClient client, UUID playerId) {
        return client.getNetworkHandler().getPlayerListEntry(playerId) != null;
    }

}
