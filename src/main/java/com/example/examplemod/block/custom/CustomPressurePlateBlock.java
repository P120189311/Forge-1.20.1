package com.example.examplemod.block.custom;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class CustomPressurePlateBlock extends PressurePlateBlock {

    private final int pressedTime;
    
    public CustomPressurePlateBlock(Sensitivity sensitivity, Properties properties, BlockSetType blockSetType, int pressedTime) {
        super(sensitivity, properties, blockSetType);
        this.pressedTime = pressedTime;
    }

    @Override
    protected int getPressedTime() {
        return pressedTime;
    }
}
