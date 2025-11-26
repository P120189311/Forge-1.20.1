package com.p120189311.theendoflimbo.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class TheEndOfLimboSignBlockEntity extends SignBlockEntity {
    public TheEndOfLimboSignBlockEntity(BlockPos p_155700_, BlockState p_155701_) {
        super(TheEndOfLimboBlockEntities.MOD_SIGN.get(), p_155700_, p_155701_);
    }

    @Override
    public BlockEntityType<?> getType() {
        return TheEndOfLimboBlockEntities.MOD_SIGN.get();
    }
}
