package net.partyaddon.screen.widget;

import net.libz.api.InventoryTab;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.partyaddon.network.PartyAddonClientPacket;

public class PartyTab extends InventoryTab {

    public PartyTab(Text title, Identifier texture, int preferedPos, Class<?>... screenClasses) {
        super(title, texture, preferedPos, screenClasses);
    }

    @Override
    public void onClick(MinecraftClient client) {
        PartyAddonClientPacket.writeC2SOpenPartyScreenPacket(client);
    }

}
