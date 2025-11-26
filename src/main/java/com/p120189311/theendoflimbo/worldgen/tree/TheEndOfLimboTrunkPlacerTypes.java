package com.p120189311.theendoflimbo.worldgen.tree;

import com.p120189311.theendoflimbo.TheEndOfLimboMod;
import com.p120189311.theendoflimbo.worldgen.tree.custom.AbyssTrunkPlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class TheEndOfLimboTrunkPlacerTypes {
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACER =
            DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, TheEndOfLimboMod.MAIN);

    public static final RegistryObject<TrunkPlacerType<AbyssTrunkPlacer>> ABYSS_TRUNK_PLACER =
            TRUNK_PLACER.register("abyss_trunk_placer", () -> new TrunkPlacerType<>(AbyssTrunkPlacer.CODEC));

    public static void register(IEventBus eventBus) {
        TRUNK_PLACER.register(eventBus);
    }
}
