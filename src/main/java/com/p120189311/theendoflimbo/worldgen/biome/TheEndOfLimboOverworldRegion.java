package com.p120189311.theendoflimbo.worldgen.biome;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.ParameterUtils;
import terrablender.api.Region;
import terrablender.api.RegionType;
import terrablender.api.VanillaParameterOverlayBuilder;

import java.util.function.Consumer;

public class TheEndOfLimboOverworldRegion extends Region {

    Climate.Parameter abyssTemp      = Climate.Parameter.span(0.55F, 1.0F);
    Climate.Parameter abyssHumidity  = Climate.Parameter.span(-1.0F, 1.0F);
    Climate.Parameter abyssCont      = Climate.Parameter.span(0.03F, 0.3F);
    Climate.Parameter abyssErosion   = Climate.Parameter.span(0.0F, 0.4F);
    Climate.Parameter abyssDepth     = Climate.Parameter.span(0.0F, 1.0F);
    Climate.Parameter abyssWeird     = Climate.Parameter.span(0.0F, 0.0F);

    public TheEndOfLimboOverworldRegion(ResourceLocation name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper)
    {
        VanillaParameterOverlayBuilder builder = new VanillaParameterOverlayBuilder();
        new ParameterUtils.ParameterPointListBuilder()
                .temperature(abyssTemp)
                .humidity(abyssHumidity)
                .continentalness(abyssCont)
                .erosion(abyssErosion)
                .depth(ParameterUtils.Depth.FULL_RANGE)
                .weirdness(abyssWeird)
                .build().forEach(point -> builder.add(point, TheEndOfLimboBiomes.ABYSS_BIOME));

        builder.build().forEach(mapper);
    }

    // Normal Build
    /*@Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint,
            ResourceKey<Biome>>> mapper) {
        super.addModifiedVanillaOverworldBiomes(mapper, modifiedVanillaOverworldBuilder -> {
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.MUSHROOM_FIELDS, ModBiomes.ABYSS_BIOME);
        });
    }*/
}
