package com.example.examplemod.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.entity.SignText;
import net.minecraft.world.level.block.state.BlockState;

public class ModHangingSignBlockEntity extends SignBlockEntity {
    public ModHangingSignBlockEntity(BlockPos p_155700_, BlockState p_155701_) {
        super(ModBlockEntities.MOD_HANGING_SIGN.get(), p_155700_, p_155701_);
    }

    @Override
    public BlockEntityType<?> getType() {
        return ModBlockEntities.MOD_HANGING_SIGN.get();
    }

    @Override
    public void onLoad() {
        super.onLoad();
        setCustomTextColor(DyeColor.WHITE, DyeColor.WHITE);
    }

    private void setCustomTextColor(DyeColor frontColor, DyeColor backColor) {
        SignText front = this.getText(true);
        SignText back = this.getText(false);

        // Apply new colors + glow settings
        SignText newFront = front.setColor(frontColor).setHasGlowingText(true);
        SignText newBack = back.setColor(backColor).setHasGlowingText(true);

        // Apply them back to the entity
        this.setText(newFront, true);
        this.setText(newBack, false);
    }
}
