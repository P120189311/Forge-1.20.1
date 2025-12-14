package com.p120189311.theendoflimbo.fluid;

import com.p120189311.theendoflimbo.TheEndOfLimboMod;
import com.p120189311.theendoflimbo.block.TheEndOfLimboBlocks;
import com.p120189311.theendoflimbo.item.TheEndOfLimboItems;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TheEndOfLimboFluids {
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(ForgeRegistries.FLUIDS, TheEndOfLimboMod.MAIN);

    public static final RegistryObject<FlowingFluid> SOURCE_SUFFOCATING_WATER = FLUIDS.register("suffocating_water_fluid",
            () -> new ForgeFlowingFluid.Source(TheEndOfLimboFluids.SUFFOCATING_WATER_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_SUFFOCATING_WATER = FLUIDS.register("flowing_suffocating_water",
            () -> new ForgeFlowingFluid.Flowing(TheEndOfLimboFluids.SUFFOCATING_WATER_FLUID_PROPERTIES));

    public static final ForgeFlowingFluid.Properties SUFFOCATING_WATER_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            TheEndOfLimboFluidTypes.SUFFOCATING_WATER_FLUID_TYPE, SOURCE_SUFFOCATING_WATER, FLOWING_SUFFOCATING_WATER)
            .slopeFindDistance(4).levelDecreasePerBlock(1).block(TheEndOfLimboBlocks.SUFFOCATING_WATER)
            .bucket(TheEndOfLimboItems.SUFFOCATING_WATER_BUCKET);

    public static void register(IEventBus eventBus){
        FLUIDS.register(eventBus);
    }
}
