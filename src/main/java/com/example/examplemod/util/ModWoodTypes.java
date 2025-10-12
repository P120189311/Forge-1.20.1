package com.example.examplemod.util;

import com.example.examplemod.ExampleMod;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ModWoodTypes {
    public static final WoodType ABYSS = WoodType.register(new WoodType(ExampleMod.MOD_ID + ":abyss", BlockSetType.OAK));
}
