package com.p120189311.theendoflimbo.worldgen.biome;

import com.p120189311.theendoflimbo.TheEndOfLimboMod;
import net.minecraft.resources.ResourceLocation;
import terrablender.api.Regions;

public class TheEndOfLimboTerrablender {
    public static void registerBiomes() {
        Regions.register(new TheEndOfLimboOverworldRegion(new ResourceLocation(TheEndOfLimboMod.MAIN, "overworld"),50));
    }
}
