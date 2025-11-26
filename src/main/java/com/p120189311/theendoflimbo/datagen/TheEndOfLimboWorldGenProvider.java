package com.p120189311.theendoflimbo.datagen;

import com.p120189311.theendoflimbo.TheEndOfLimboMod;
import com.p120189311.theendoflimbo.worldgen.TheEndOfLimboBiomeModifiers;
import com.p120189311.theendoflimbo.worldgen.TheEndOfLimboConfiguredFeatures;
import com.p120189311.theendoflimbo.worldgen.TheEndOfLimboPlacedFeatures;
import com.p120189311.theendoflimbo.worldgen.biome.TheEndOfLimboBiomes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class TheEndOfLimboWorldGenProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, TheEndOfLimboConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, TheEndOfLimboPlacedFeatures::bootstrap)
            .add(Registries.BIOME, TheEndOfLimboBiomes::boostrap)
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, TheEndOfLimboBiomeModifiers::bootstrap);

    public TheEndOfLimboWorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(TheEndOfLimboMod.MAIN));
    }
}
