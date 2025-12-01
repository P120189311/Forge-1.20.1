package com.p120189311.theendoflimbo.worldgen.tree;

import com.p120189311.theendoflimbo.TheEndOfLimboMod;
import com.p120189311.theendoflimbo.worldgen.tree.custom.AbyssFoliagePlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class TheEndOfLimboFoliagePlacers {
    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS =
            DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, TheEndOfLimboMod.MAIN);

    public static final RegistryObject<FoliagePlacerType<AbyssFoliagePlacer>> ABYSS_FOLIAGE_PLACER =
            FOLIAGE_PLACERS.register("abyss_foliage_placer", () -> new FoliagePlacerType<>(AbyssFoliagePlacer.CODEC));

    public static void register (IEventBus eventBus){
        FOLIAGE_PLACERS.register(eventBus);
    }
}
