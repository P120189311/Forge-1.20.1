package com.example.examplemod.worldgen;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.block.ModBlocks;
import com.example.examplemod.util.ModTags;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_ABSOLUTE_BLACK_ORE_KEY = registerKey("absolute_black_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_ABSOLUTE_WHITE_ORE_KEY = registerKey("absolute_white_ore");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceable = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherrackReplaceables = new BlockMatchTest(Blocks.NETHERRACK);
        RuleTest endReplaceables = new BlockMatchTest(Blocks.END_STONE);

        List<OreConfiguration.TargetBlockState> overworldAbsoluteBlackOres = List.of(OreConfiguration.target(stoneReplaceable,
                ModBlocks.ABSOLUTE_BLACK_ORE.get().defaultBlockState())
                //,OreConfiguration.target(deepslateReplaceable, ModBlocks.DEEPSLATE_ABSOLUTE_BLACK_ORE.get().defaultBlockState()
        );
        List<OreConfiguration.TargetBlockState> overworldAbsoluteWhiteOres = List.of(OreConfiguration.target(stoneReplaceable,
                ModBlocks.ABSOLUTE_WHITE_ORE.get().defaultBlockState())
                //,OreConfiguration.target(deepslateReplaceable, ModBlocks.DEEPSLATE_ABSOLUTE_WHITE_ORE.get().defaultBlockState()
        );

        register(context, OVERWORLD_ABSOLUTE_BLACK_ORE_KEY, Feature.ORE, new OreConfiguration(overworldAbsoluteBlackOres, 5));
        /*register(context, NETHER_ABSOLUTE_BLACK_ORE_KEY, Feature.ORE, new OreConfiguration(netherrackReplaceables,
                ModBlocks.NETHER_ABSOLUTE_BLACK_ORE.get().defaultBlockState(), 5));*/
        /*register(context, END_ABSOLUTE_BLACK_ORE_KEY, Feature.ORE, new OreConfiguration(endReplaceables,
                ModBlocks.END_ABSOLUTE_BLACK_ORE.get().defaultBlockState(), 5));*/

        register(context, OVERWORLD_ABSOLUTE_WHITE_ORE_KEY, Feature.ORE, new OreConfiguration(overworldAbsoluteWhiteOres, 5));
        /*register(context, NETHER_ABSOLUTE_WHITE_ORE_KEY, Feature.ORE, new OreConfiguration(netherrackReplaceables,
                ModBlocks.NETHER_ABSOLUTE_WHITE_ORE.get().defaultBlockState(), 5));*/
        /*register(context, END_ABSOLUTE_WHITE_ORE_KEY, Feature.ORE, new OreConfiguration(endReplaceables,
                ModBlocks.END_ABSOLUTE_WHITE_ORE.get().defaultBlockState(), 5));*/
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(ExampleMod.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
