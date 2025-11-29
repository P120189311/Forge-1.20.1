package com.p120189311.theendoflimbo.worldgen.dimension;

import com.mojang.datafixers.util.Pair;
import com.p120189311.theendoflimbo.TheEndOfLimboMod;
import com.p120189311.theendoflimbo.worldgen.biome.TheEndOfLimboBiomes;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;

import java.util.List;
import java.util.OptionalLong;

public class AbyssDimension {

    public static final ResourceKey<LevelStem> ABYSS_DIMENSION_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            new ResourceLocation(TheEndOfLimboMod.MAIN,"abyss_dimension"));
    public static final ResourceKey<Level> ABYSS_DIMENSION_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            new ResourceLocation(TheEndOfLimboMod.MAIN,"abyss_dimension"));
    public static final ResourceKey<DimensionType> ABYSS_DIMENSION_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            new ResourceLocation(TheEndOfLimboMod.MAIN,"abyss_dimension_type"));

    public static void bootstrapType(BootstapContext<DimensionType> context) {
        context.register(ABYSS_DIMENSION_TYPE, new DimensionType(
                OptionalLong.of(12000), // fixedTime
                false, // hasSkylight
                false, // hasCeiling
                false, // ultraWarm
                false, // natural
                1.0, // coordinateScale
                true, // bedWorks
                false, // respawnAnchorWorks
                -64, // minY
                384, // height
                384, // logicalHeight
                BlockTags.INFINIBURN_OVERWORLD, // infiniburn
                BuiltinDimensionTypes.OVERWORLD_EFFECTS, // effectsLocation
                1.0f, // ambientLight
                new DimensionType.MonsterSettings(false, false, ConstantInt.of(0), 0)));
    }

    public static void bootstrapStem(BootstapContext<LevelStem> context) {
        HolderGetter<Biome> biomeRegistry = context.lookup(Registries.BIOME);
        HolderGetter<DimensionType> dimTypes = context.lookup(Registries.DIMENSION_TYPE);
        HolderGetter<NoiseGeneratorSettings> noiseGenSettings = context.lookup(Registries.NOISE_SETTINGS);

        NoiseBasedChunkGenerator wrappedChunkGenerator = new NoiseBasedChunkGenerator(
                new FixedBiomeSource(biomeRegistry.getOrThrow(TheEndOfLimboBiomes.ABYSS_BIOME)),
                noiseGenSettings.getOrThrow(NoiseGeneratorSettings.LARGE_BIOMES));

        Holder<Biome> abyss = biomeRegistry.getOrThrow(TheEndOfLimboBiomes.ABYSS_BIOME);
        Holder<Biome> deepOcean = biomeRegistry.getOrThrow(Biomes.DEEP_OCEAN);

        NoiseBasedChunkGenerator noiseBasedChunkGenerator = new NoiseBasedChunkGenerator(
                MultiNoiseBiomeSource.createFromList(
                        new Climate.ParameterList<>(List.of(

                                Pair.of(Climate.parameters(1.0F, 0.0F, 1.0F, 0.0F, -1.0F, 0.0F, 0.0F), abyss),

                                Pair.of(Climate.parameters(-1.0F, 0.0F, -1.0F, 0.0F, -1.0F, 0.0F, 0.0F), deepOcean)

                        ))),
                noiseGenSettings.getOrThrow(NoiseGeneratorSettings.FLOATING_ISLANDS));

        LevelStem stem = new LevelStem(dimTypes.getOrThrow(AbyssDimension.ABYSS_DIMENSION_TYPE), wrappedChunkGenerator);

        context.register(ABYSS_DIMENSION_KEY, stem);
    }
}
