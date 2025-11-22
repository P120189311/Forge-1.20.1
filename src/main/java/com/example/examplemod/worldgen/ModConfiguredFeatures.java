package com.example.examplemod.worldgen;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.block.ModBlocks;
import com.example.examplemod.util.ModTags;
import com.example.examplemod.worldgen.tree.custom.AbyssFoliagePlacer;
import com.example.examplemod.worldgen.tree.custom.AbyssTrunkPlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.AcaciaFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.DarkOakFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.DarkOakTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_ABSOLUTE_BLACK_ORE_KEY = registerKey("absolute_black_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_ABSOLUTE_WHITE_ORE_KEY = registerKey("absolute_white_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> ABYSS_KEY = registerKey("abyss");

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

        register(context, ABYSS_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.ABYSS_LOG.get()),
                new AbyssTrunkPlacer(10,5,5),

                BlockStateProvider.simple(ModBlocks.ABYSS_LEAVES.get()),
                new AbyssFoliagePlacer(ConstantInt.of(2),
                        ConstantInt.of(0),
                        ConstantInt.of(8)
                ),

        new TwoLayersFeatureSize(1,0,2)).build());
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(ExampleMod.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
