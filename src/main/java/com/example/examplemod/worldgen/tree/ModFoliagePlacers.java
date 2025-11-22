package com.example.examplemod.worldgen.tree;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.worldgen.tree.custom.AbyssFoliagePlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModFoliagePlacers {
    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS =
            DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, ExampleMod.MOD_ID);

    public static final RegistryObject<FoliagePlacerType<AbyssFoliagePlacer>> ABYSS_FOLIAGE_PLACER =
            FOLIAGE_PLACERS.register("abyss_foliage_placer", () -> new FoliagePlacerType<>(AbyssFoliagePlacer.CODEC));

    public static void register (IEventBus eventBus){
        FOLIAGE_PLACERS.register(eventBus);
    }
}
