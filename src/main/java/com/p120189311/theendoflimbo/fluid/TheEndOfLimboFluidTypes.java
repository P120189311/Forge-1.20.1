package com.p120189311.theendoflimbo.fluid;

import com.p120189311.theendoflimbo.TheEndOfLimboMod;
import com.p120189311.theendoflimbo.event.custom.SuffocatingWaterEffect;
import com.p120189311.theendoflimbo.sound.TheEndOfLimboSounds;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.SoundAction;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.joml.Vector3f;

public class TheEndOfLimboFluidTypes {
    public static final ResourceLocation WATER_STILL_RL = new ResourceLocation("block/water_still");
    public static final ResourceLocation WATER_FLOWING_RL = new ResourceLocation("block/water_flow");
    public static final ResourceLocation SUFFOCATING_OVERLAY_RL = new ResourceLocation(TheEndOfLimboMod.MAIN, "misc/in_suffocating_water");

    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, TheEndOfLimboMod.MAIN);

    public static final RegistryObject<FluidType> SUFFOCATING_WATER_FLUID_TYPE = register("suffocating_water_fluid",
            FluidType.Properties.create().lightLevel(0).density(8000).viscosity(8000).canExtinguish(true)
                    .canSwim(false).supportsBoating(true)
                    .canDrown(false)

                    .sound(SoundAction.get("drink"), SoundEvents.BLAZE_HURT)
                    .sound(SoundActions.BUCKET_FILL,SoundEvents.BUCKET_FILL)
                    .sound(SoundActions.BUCKET_EMPTY,SoundEvents.BUCKET_EMPTY));

    private static RegistryObject<FluidType> register(String name, FluidType.Properties properties){
        return FLUID_TYPES.register(name, () -> new BaseFluidType(WATER_STILL_RL, WATER_FLOWING_RL, SUFFOCATING_OVERLAY_RL,
                0xA1f13554, new Vector3f(241f / 255f , 53f  / 255f, 84f  / 255f), properties));
    }

    public static void register(IEventBus eventBus){
        FLUID_TYPES.register(eventBus);
        MinecraftForge.EVENT_BUS.register(new SuffocatingWaterEffect());
    }
}
