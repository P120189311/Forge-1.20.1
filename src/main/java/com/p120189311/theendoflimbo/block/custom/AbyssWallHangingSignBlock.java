package com.p120189311.theendoflimbo.block.custom;

import com.p120189311.theendoflimbo.block.entity.TheEndOfLimboHangingSignBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

public class AbyssWallHangingSignBlock extends WallHangingSignBlock {
    public AbyssWallHangingSignBlock(Properties p_251606_, WoodType p_252140_) {
        super(p_251606_, p_252140_);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos p_250745_, BlockState p_250905_) {
        return new TheEndOfLimboHangingSignBlockEntity(p_250745_, p_250905_);
    }
}
