package net.partyaddon.init;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.partyaddon.access.GroupManagerAccess;
import net.partyaddon.group.GroupManager;

public class CommandInit {

    public static void init() {
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated, environment) -> {
            dispatcher.register(CommandManager.literal("party").then(CommandManager.literal("join").executes((commandContext) -> {
                return executePartyCommand(commandContext.getSource(), "join");
            })).then(CommandManager.literal("leave").executes((commandContext) -> {
                return executePartyCommand(commandContext.getSource(), "leave");
            })));
        });
    }

    private static int executePartyCommand(ServerCommandSource source, String string) {
        if (source.getEntity() != null && source.getEntity() instanceof ServerPlayerEntity serverPlayerEntity) {
            GroupManager groupManager = ((GroupManagerAccess) serverPlayerEntity).getGroupManager();
            if (string.equals("join")) {
                GroupManager.tryJoinGroup(serverPlayerEntity, groupManager.getInvitationPlayerId());
            } else if (string.equals("leave")) {
                GroupManager.leaveGroup(serverPlayerEntity, false);
            }
            return 1;
        }
        return 0;
    }
}
