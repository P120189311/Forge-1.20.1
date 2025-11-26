package com.p120189311.theendoflimbo.event.custom;

import com.p120189311.theendoflimbo.effect.TheEndOfLimboEffects;
import com.p120189311.theendoflimbo.item.TheEndOfLimboItems;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber
public class TrappedGammaRayRadiation {

    // Track each player's last known "tier" (how many sets of 16)
    private static final Map<Player, Integer> lastGammaTier = new HashMap<>();

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;

        if (player.level().isClientSide || event.phase != TickEvent.Phase.END) return;
        if (player.tickCount % 20 != 0) return; // once per second

        // Count total Trapped Gamma Rays
        int count = 0;
        for (var stack : player.getInventory().items) {
            if (stack.is(TheEndOfLimboItems.TRAPPED_GAMMA_RAY.get())) {
                count += stack.getCount();
            }
        }

        int currentTier = count / 16; // each full set of 16 = 1 tier
        int previousTier = lastGammaTier.getOrDefault(player, 0);

        // Update stored tier for next tick
        lastGammaTier.put(player, currentTier);

        // Skip if player has less than 16 gamma rays
        if (currentTier < 1) return;

        int durationTicks = currentTier * 18000; // 15 min * sets
        MobEffectInstance existing = player.getEffect(TheEndOfLimboEffects.DEMENTIA.get());

        // ✅ Apply only if:
        // 1. Player entered a new tier (e.g. 16→32 or 32→48)
        // 2. Effect is missing or expired
        if (currentTier > previousTier || existing == null || existing.getDuration() <= 0) {
            player.addEffect(new MobEffectInstance(
                    TheEndOfLimboEffects.DEMENTIA.get(),
                    durationTicks,
                    0,
                    false, // ambient
                    false, // showParticles
                    true   // showIcon
            ));
        }
    }
}