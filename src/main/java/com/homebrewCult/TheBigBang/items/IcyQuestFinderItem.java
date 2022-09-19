package com.homebrewCult.TheBigBang.items;

import com.homebrewCult.TheBigBang.init.ModFeatures;
import com.homebrewCult.TheBigBang.world.danger_sign_structures.IceSignStructure;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class IcyQuestFinderItem extends QuestFinderItem {

    public IcyQuestFinderItem(Properties properties) {
        super(properties);
    }

    @Override
    protected BlockPos getNearestStructurePos(World worldIn, BlockPos pos) {
        IceSignStructure iceStructure = ModFeatures.DANGER_SIGN_ICE_STRUCTURE.get();
        if(iceStructure == null)
            return pos;
        return iceStructure.findNearest(worldIn, worldIn.getChunkProvider().getChunkGenerator(), pos, 100, false);
    }
}
