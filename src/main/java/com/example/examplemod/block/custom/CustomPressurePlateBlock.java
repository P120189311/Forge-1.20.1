package com.example.examplemod.block.custom;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.PressurePlateBlock;

public class CustomPressurePlateBlock extends PressurePlateBlock {

    private final int pressedTime;
    
    public CustomPressurePlateBlock(Sensitivity sensitivity, Properties properties, SoundEvent clickOnSound, SoundEvent clickOffSound, int pressedTime) {
        super(sensitivity, properties, clickOnSound, clickOffSound);
        this.pressedTime = pressedTime;
    }

    @Override
    protected int getPressedTime() {
        return pressedTime;
    }
}
