package com.example.examplemod.villager;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.block.ModBlocks;
import com.google.common.collect.ImmutableSet;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModVillagers {
    public static final DeferredRegister<PoiType> POI_TYPES =
            DeferredRegister.create(ForgeRegistries.POI_TYPES, ExampleMod.MOD_ID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS =
            DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, ExampleMod.MOD_ID);

    public static final RegistryObject<PoiType> OVERSEER_POI = POI_TYPES.register("overseer_poi",
            () -> new PoiType(ImmutableSet.copyOf(ModBlocks.MYSTERY_STAND.get().getStateDefinition().getPossibleStates()),
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
