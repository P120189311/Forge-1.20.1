package com.p120189311.theendoflimbo.block.custom;

import com.p120189311.theendoflimbo.block.entity.TheEndOfLimboHangingSignBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

public class AbyssHangingSignBlock extends CeilingHangingSignBlock {
    public AbyssHangingSignBlock(Properties p_250481_, WoodType p_248716_) {
        super(p_250481_, p_248716_);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos p_249338_, BlockState p_250706_) {
        return new TheEndOfLimboHangingSignBlockEntity(p_249338_, p_250706_);
    }
}
