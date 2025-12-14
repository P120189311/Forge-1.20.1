/*package com.p120189311.theendoflimbo.worldgen.noise;

import com.p120189311.theendoflimbo.TheEndOfLimboMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.NoiseRouter;
import net.minecraft.world.level.levelgen.NoiseSettings;
import net.minecraft.world.level.levelgen.SurfaceRules;

import java.util.List;

public class AbyssNoise {
    public static final ResourceKey<NoiseGeneratorSettings> ABYSS_NOISE =
            ResourceKey.create(Registries.NOISE_SETTINGS,
                    new ResourceLocation(TheEndOfLimboMod.MAIN, "abyss_noise"));

    public static void bootstrap(BootstapContext<NoiseGeneratorSettings> context) {

        int minY = -64;
        int height = 384;

        NoiseSettings noiseSettings = NoiseSettings.create(
                minY,        // minY
                height,      // height
                1,           // horizontal size
                2            // vertical size
        );

        NoiseGeneratorSettings abyss = new NoiseGeneratorSettings(
                noiseSettings,

                // Default blocks
                Blocks.STONE.defaultBlockState(),
                Blocks.AIR.defaultBlockState(),

                // Use the overworld noise router unless you want custom noise
                NoiseRouter.CODEC(context.lookup(Registries.NOISE).getOrThrow(a)),

                // Use overworld surface rules (grass/dirt)
                SurfaceRules.bandlands(),

                // No special spawn targets
                List.of(),

                0,     // sea level
                false, // disableMobGeneration
                false, // aquifersEnabled
                false, // oreVeinsEnabled
                false  // useLegacyRandomSource
        );

        context.register(ABYSS_NOISE, abyssSettings);
    }
}
*/