package com.p120189311.theendoflimbo.effect;

import com.p120189311.theendoflimbo.TheEndOfLimboMod;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TheEndOfLimboEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS
            = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, TheEndOfLimboMod.MAIN);

    public static final RegistryObject<MobEffect> DEMENTIA = MOB_EFFECTS.register("dementia",
            () -> new DementiaEffect(MobEffectCategory.NEUTRAL, 0xFFFFFF));

    public static void register(IEventBus eventBus){
        MOB_EFFECTS.register(eventBus);
    }
}
