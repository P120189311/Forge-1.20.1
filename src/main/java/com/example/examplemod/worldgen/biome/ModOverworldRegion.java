package com.example.examplemod.worldgen.biome;

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

public class ModOverworldRegion extends Region {

    Climate.Parameter abyssTemp      = Climate.Parameter.span(0.6F, 0.9F);
    Climate.Parameter abyssHumidity  = Climate.Parameter.span(-1.0F, 0.0F);
    Climate.Parameter abyssCont      = Climate.Parameter.span(0.5F, 1.0F);
    Climate.Parameter abyssErosion   = Climate.Parameter.span(0.0F, 0.4F);
    Climate.Parameter abyssDepth     = Climate.Parameter.span(0.0F, 1.0F);
    Climate.Parameter abyssWeird     = Climate.Parameter.span(-0.05F, 0.05F);

    public ModOverworldRegion(ResourceLocation name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper)
    {
        VanillaParameterOverlayBuilder builder = new VanillaParameterOverlayBuilder();
        new ParameterUtils.ParameterPointListBuilder()
                .temperature(abyssTemp)
                .humidity(ParameterUtils.Humidity.span(ParameterUtils.Humidity.WET, ParameterUtils.Humidity.HUMID))
                .continentalness(abyssCont)
                .erosion(abyssErosion)
                .depth(ParameterUtils.Depth.FULL_RANGE)
                .weirdness(ParameterUtils.Weirdness.VALLEY, ParameterUtils.Weirdness.LOW_SLICE_VARIANT_ASCENDING, ParameterUtils.Weirdness.MID_SLICE_VARIANT_ASCENDING)
                .build().forEach(point -> builder.add(point, ModBiomes.ABYSS_BIOME));

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
