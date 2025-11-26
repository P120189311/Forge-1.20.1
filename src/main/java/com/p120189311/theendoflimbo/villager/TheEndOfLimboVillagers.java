package com.p120189311.theendoflimbo.villager;

import com.p120189311.theendoflimbo.TheEndOfLimboMod;
import com.p120189311.theendoflimbo.block.TheEndOfLimboBlocks;
import com.google.common.collect.ImmutableSet;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TheEndOfLimboVillagers {
    public static final DeferredRegister<PoiType> POI_TYPES =
            DeferredRegister.create(ForgeRegistries.POI_TYPES, TheEndOfLimboMod.MAIN);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS =
            DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, TheEndOfLimboMod.MAIN);

    public static final RegistryObject<PoiType> OVERSEER_POI = POI_TYPES.register("overseer_poi",
            () -> new PoiType(ImmutableSet.copyOf(TheEndOfLimboBlocks.MYSTERY_STAND.get().getStateDefinition().getPossibleStates()),
                    1,1));

    public static final RegistryObject<VillagerProfession> OVERSEER =
            VILLAGER_PROFESSIONS.register("overseer", () -> new VillagerProfession("overseer",
                    holder -> holder.get() == OVERSEER_POI.get(), holder -> holder.get() == OVERSEER_POI.get(),
                    ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_LIBRARIAN));

    public static void register(IEventBus eventBus) {
        POI_TYPES.register(eventBus);
        VILLAGER_PROFESSIONS.register(eventBus);
    }
}
