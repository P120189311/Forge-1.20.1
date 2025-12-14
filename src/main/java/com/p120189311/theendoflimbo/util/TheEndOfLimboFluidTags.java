package com.p120189311.theendoflimbo.util;

import com.p120189311.theendoflimbo.TheEndOfLimboMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;

public class TheEndOfLimboFluidTags {
    public static final TagKey<Fluid> SUFFOCATING_WATER =
            tag("suffocating_water");

    private static TagKey<Fluid> tag(String name) {
        return FluidTags.create(new ResourceLocation(TheEndOfLimboMod.MAIN, name));
    }
}
