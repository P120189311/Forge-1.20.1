package com.p120189311.theendoflimbo.worldgen;

import com.p120189311.theendoflimbo.TheEndOfLimboMod;
import com.p120189311.theendoflimbo.block.TheEndOfLimboBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class TheEndOfLimboPlacedFeatures {
    public static final ResourceKey<PlacedFeature> ABSOLUTE_BLACK_ORE_PLACED_KEY = registerKey("absolute_black_ore_placed");
    public static final ResourceKey<PlacedFeature> NETHER_ABSOLUTE_BLACK_ORE_PLACED_KEY = registerKey("nether_absolute_black_ore_placed");
    public static final ResourceKey<PlacedFeature> END_ABSOLUTE_BLACK_ORE_PLACED_KEY = registerKey("end_absolute_black_ore_placed");

    public static final ResourceKey<PlacedFeature> ABSOLUTE_WHITE_ORE_PLACED_KEY = registerKey("absolute_white_ore_placed");
    public static final ResourceKey<PlacedFeature> NETHER_ABSOLUTE_WHITE_ORE_PLACED_KEY = registerKey("nether_absolute_white_ore_placed");
    public static final ResourceKey<PlacedFeature> END_ABSOLUTE_WHITE_ORE_PLACED_KEY = registerKey("end_absolute_white_ore_placed");

    public static final ResourceKey<PlacedFeature> ABYSS_PLACED_KEY = registerKey("abyss_placed");

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, ABSOLUTE_BLACK_ORE_PLACED_KEY, configuredFeatures.getOrThrow(TheEndOfLimboConfiguredFeatures.OVERWORLD_ABSOLUTE_BLACK_ORE_KEY),
                TheEndOfLimboOrePlacement.commonOrePlacement(8,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80))));
        /*register(context, NETHER_ABSOLUTE_BLACK_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.NETHER_ABSOLUTE_BLACK_ORE_KEY),
                ModOrePlacement.commonOrePlacement(8,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80))));
        register(context, END_ABSOLUTE_BLACK_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.END_ABSOLUTE_BLACK_ORE_KEY),
                ModOrePlacement.commonOrePlacement(8,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80))));*/

        register(context, ABSOLUTE_WHITE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(TheEndOfLimboConfiguredFeatures.OVERWORLD_ABSOLUTE_WHITE_ORE_KEY),
                TheEndOfLimboOrePlacement.commonOrePlacement(8,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80))));
        /*register(context, NETHER_ABSOLUTE_WHITE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.NETHER_ABSOLUTE_WHITE_ORE_KEY),
                ModOrePlacement.commonOrePlacement(8,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80))));
        register(context, END_ABSOLUTE_WHITE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.END_ABSOLUTE_WHITE_ORE_KEY),
                ModOrePlacement.commonOrePlacement(8,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80))));*/

        register(context, ABYSS_PLACED_KEY, configuredFeatures.getOrThrow(TheEndOfLimboConfiguredFeatures.ABYSS_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3,0.1f, 2), TheEndOfLimboBlocks.ABYSS_SAPLING.get()));
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(TheEndOfLimboMod.MAIN, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
