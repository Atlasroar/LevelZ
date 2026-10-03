package net.partyaddon.network;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.netty.buffer.Unpooled;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.c2s.play.CustomPayloadC2SPacket;
import net.minecraft.util.math.BlockPos;
import net.partyaddon.access.GroupManagerAccess;
import net.partyaddon.group.GroupManager;
import net.partyaddon.init.CompatInit;
import net.partyaddon.screen.PartyScreen;

@Environment(EnvType.CLIENT)
public class PartyAddonClientPacket {

    public static void init() {
        ClientPlayNetworking.registerGlobalReceiver(PartyAddonServerPacket.OPEN_PARTY_SCREEN_SC_PACKET, (client, handler, buf, sender) -> {
            client.execute(() -> {
                client.setScreen(new PartyScreen());
            });
        });

        ClientPlayNetworking.registerGlobalReceiver(PartyAddonServerPacket.SYNC_STAR_PLAYER_LIST_SC_PACKET, (client, handler, buf, sender) -> {
            int starPlayerCount = buf.readInt();
            List<UUID> starPlayerIdList = new ArrayList<UUID>();
            for (int i = 0; i < starPlayerCount; i++) {
                starPlayerIdList.add(buf.readUuid());
            }
            client.execute(() -> {
                GroupManager groupManager = ((GroupManagerAccess) client.player).getGroupManager();
                groupManager.updateStarPlayerIdList(starPlayerIdList);
            });
        });

        ClientPlayNetworking.registerGlobalReceiver(PartyAddonServerPacket.SYNC_GROUP_MANAGER_SC_PACKET, (client, handler, buf, sender) -> {
            int availablePlayerCount = buf.readInt();
            List<UUID> availablePlayerIdList = new ArrayList<UUID>();
            if (availablePlayerCount > 0) {
                for (int i = 0; i < availablePlayerCount; i++) {
                    availablePlayerIdList.add(buf.readUuid());
                }
            }

            int starPlayerCount = buf.readInt();
            List<UUID> starPlayerIdList = new ArrayList<UUID>();
            if (starPlayerCount > 0) {
                for (int i = 0; i < starPlayerCount; i++) {
                    starPlayerIdList.add(buf.readUuid());
                }
            }

            int groupPlayerCount = buf.readInt();
            List<UUID> groupPlayerIdList = new ArrayList<UUID>();
            if (groupPlayerCount > 0) {
                for (int i = 0; i < groupPlayerCount; i++) {
                    groupPlayerIdList.add(buf.readUuid());
                }
            }
            UUID groupLeaderId = buf.readBoolean() ? buf.readUuid() : null;
            client.execute(() -> {
                GroupManager groupManager = ((GroupManagerAccess) client.player).getGroupManager();
                groupManager.setAvailablePlayerIdList(availablePlayerIdList);
                groupManager.updateStarPlayerIdList(starPlayerIdList);
                groupManager.updatePlayerGroupIdList(groupPlayerIdList, groupLeaderId);
            });
        });

        ClientPlayNetworking.registerGlobalReceiver(PartyAddonServerPacket.SYNC_INVITATION_SC_PACKET, (client, handler, buf, sender) -> {
            UUID playerId = buf.readUuid();
            client.execute(() -> {
                ((GroupManagerAccess) client.player).getGroupManager().invitePlayerToGroup(playerId);
            });
        });

        ClientPlayNetworking.registerGlobalReceiver(PartyAddonServerPacket.SYNC_DECLINE_INVITATION_SC_PACKET, (client, handler, buf, sender) -> {
            // int playerId = buf.readInt();
            // maybe use playerId to send feedback?
            client.execute(() -> {
                ((GroupManagerAccess) client.player).getGroupManager().declineInvitation();
            });
        });

        ClientPlayNetworking.registerGlobalReceiver(PartyAddonServerPacket.MAP_COMPAT_SC_PACKET, (client, handler, buf, sender) -> {
            int uuidCount = buf.readInt();
            List<UUID> groupPlayerUUIDs = new ArrayList<UUID>();
            List<BlockPos> groupPlayerBlockPoses = new ArrayList<BlockPos>();
            List<Float> groupPlayerYaws = new ArrayList<Float>();
            for (int i = 0; i < uuidCount; i++) {
                groupPlayerUUIDs.add(buf.readUuid());
                groupPlayerBlockPoses.add(buf.readBlockPos());
                groupPlayerYaws.add(buf.readFloat());
            }
            client.execute(() -> {
                CompatInit.syncGroupToMap(client, groupPlayerUUIDs, groupPlayerBlockPoses, groupPlayerYaws);
            });
        });
    }

    // screen
    public static void writeC2SOpenPartyScreenPacket(MinecraftClient client) {
        MinecraftClient.getInstance().getNetworkHandler().sendPacket(new CustomPayloadC2SPacket(PartyAddonServerPacket.OPEN_PARTY_SCREEN_CS_PACKET, new PacketByteBuf(Unpooled.buffer())));
    }

    public static void writeC2SChangeStarListPacket(UUID entityId) {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        buf.writeUuid(entityId);
        CustomPayloadC2SPacket packet = new CustomPayloadC2SPacket(PartyAddonServerPacket.CHANGE_STAR_PLAYER_LIST_CS_PACKET, buf);
        MinecraftClient.getInstance().getNetworkHandler().sendPacket(packet);
    }

    public static void writeC2SInvitePlayerToGroupPacket(UUID invitedPlayerId) {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        buf.writeUuid(invitedPlayerId);
        CustomPayloadC2SPacket packet = new CustomPayloadC2SPacket(PartyAddonServerPacket.INVITE_PLAYER_TO_GROUP_CS_PACKET, buf);
        MinecraftClient.getInstance().getNetworkHandler().sendPacket(packet);
    }

    public static void writeC2SAcceptInvitationPacket(UUID invitationPlayerId) {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        buf.writeUuid(invitationPlayerId);
        CustomPayloadC2SPacket packet = new CustomPayloadC2SPacket(PartyAddonServerPacket.ACCEPT_INVITATION_CS_PACKET, buf);
        MinecraftClient.getInstance().getNetworkHandler().sendPacket(packet);
    }

    public static void writeC2SDeclineInvitationPacket(UUID entityId) {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        buf.writeUuid(entityId);
        CustomPayloadC2SPacket packet = new CustomPayloadC2SPacket(PartyAddonServerPacket.DECLINE_INVITATION_CS_PACKET, buf);
        MinecraftClient.getInstance().getNetworkHandler().sendPacket(packet);
    }

    public static void writeC2SLeaveGroupPacket() {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        CustomPayloadC2SPacket packet = new CustomPayloadC2SPacket(PartyAddonServerPacket.LEAVE_GROUP_CS_PACKET, buf);
        MinecraftClient.getInstance().getNetworkHandler().sendPacket(packet);
    }

    public static void writeC2SKickPlayerPacket(UUID groupLeaderId, UUID kickPlayerId) {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        buf.writeUuid(groupLeaderId);
        buf.writeUuid(kickPlayerId);
        CustomPayloadC2SPacket packet = new CustomPayloadC2SPacket(PartyAddonServerPacket.KICK_PLAYER_CS_PACKET, buf);
        MinecraftClient.getInstance().getNetworkHandler().sendPacket(packet);
    }

    public static void writeC2SSyncGroupMemberPacket(MinecraftClient client) {
        client.getNetworkHandler().sendPacket(new CustomPayloadC2SPacket(PartyAddonServerPacket.MAP_COMPAT_CS_PACKET, new PacketByteBuf(Unpooled.buffer())));
    }

}
