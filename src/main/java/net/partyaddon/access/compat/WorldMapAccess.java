package net.partyaddon.access.compat;

import java.util.List;
import java.util.UUID;

import net.minecraft.util.math.BlockPos;

public interface WorldMapAccess {

    public void setGroupPlayers(List<UUID> groupPlayerUUIDList, List<BlockPos> groupPlayerPosList, List<Float> groupPlayerYaws);

}
