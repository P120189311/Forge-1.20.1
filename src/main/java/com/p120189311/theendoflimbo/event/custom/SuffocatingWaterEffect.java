package com.p120189311.theendoflimbo.event.custom;

import com.p120189311.theendoflimbo.TheEndOfLimboMod;
import com.p120189311.theendoflimbo.block.TheEndOfLimboBlocks;
import com.p120189311.theendoflimbo.effect.TheEndOfLimboEffects;
import com.p120189311.theendoflimbo.fluid.TheEndOfLimboFluidTypes;
import com.p120189311.theendoflimbo.util.TheEndOfLimboFluidTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = TheEndOfLimboMod.MAIN, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SuffocatingWaterEffect {
    private final Map<UUID, Integer> suffocatingTicks = new HashMap<>();

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        UUID playerUUID = player.getUUID();

        if (!player.level().isClientSide) {

            boolean eyesIn = player.isEyeInFluidType(TheEndOfLimboFluidTypes.SUFFOCATING_WATER_FLUID_TYPE.get());
            boolean bodyIn = player.isInFluidType(TheEndOfLimboFluidTypes.SUFFOCATING_WATER_FLUID_TYPE.get());

            boolean fullySubmerged = eyesIn && bodyIn;

            if (fullySubmerged) {
                suffocatingTicks.put(playerUUID, 20 * 5);
                player.addEffect(new MobEffectInstance(TheEndOfLimboEffects.SUFFOCATING.get(), suffocatingTicks.get(playerUUID), 0));
            } else if (suffocatingTicks.containsKey(playerUUID)) {
                int ticks = suffocatingTicks.get(playerUUID);
                ticks--;

                if (ticks > 0) {
                    suffocatingTicks.put(playerUUID, ticks);
                    player.addEffect(new MobEffectInstance(TheEndOfLimboEffects.SUFFOCATING.get(), 20, 0));
                } else {
                    suffocatingTicks.remove(playerUUID);
                }
            }
        }
    }
}
