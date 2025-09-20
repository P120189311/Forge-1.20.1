package com.example.examplemod.sound;

import com.example.examplemod.ExampleMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, ExampleMod.MOD_ID);

    public static final RegistryObject<SoundEvent> BLUE_HILL_TOMATO_EAT_CHANCE = registerSoundEvents("blue_hill_tomato_eat_chance");
    public static final RegistryObject<SoundEvent> MYSTERY_STAND_USE_CHANCE = registerSoundEvents("mystery_stand_use_chance");

    public static final RegistryObject<SoundEvent> AMBULANCE_BEAT = registerSoundEvents("ambulance_beat");
    public static final RegistryObject<SoundEvent> DROP_IT_LIKE_ITS_HOT = registerSoundEvents("drop_it_like_its_hot");
    public static final RegistryObject<SoundEvent> BAFANGLAICAI = registerSoundEvents("bafanglaicai");

    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(ExampleMod.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
