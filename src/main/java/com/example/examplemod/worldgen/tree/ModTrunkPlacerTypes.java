package com.example.examplemod.worldgen.tree;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.worldgen.tree.custom.AbyssTrunkPlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModTrunkPlacerTypes {
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACER =
            DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, ExampleMod.MOD_ID);

    public static final RegistryObject<TrunkPlacerType<AbyssTrunkPlacer>> ABYSS_TRUNK_PLACER =
            TRUNK_PLACER.register("abyss_trunk_placer", () -> new TrunkPlacerType<>(AbyssTrunkPlacer.CODEC));

    public static void register(IEventBus eventBus) {
        TRUNK_PLACER.register(eventBus);
    }
}
