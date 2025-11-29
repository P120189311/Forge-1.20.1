package com.p120189311.theendoflimbo.event;

import com.p120189311.theendoflimbo.item.TheEndOfLimboItems;
import com.p120189311.theendoflimbo.item.custom.CursedDisc;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashSet;
import java.util.Map;
import java.util.UUID;

@Mod.EventBusSubscriber
public class TheEndOfLimboGlobalCooldownHandler {

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Map<UUID, Integer> cooldown = CursedDisc.getCooldownMap();

        // Remove expired cooldowns
        cooldown.entrySet().removeIf(entry -> entry.getValue() <= 0);

        // Decrease active cooldowns
        for (UUID id : new HashSet<>(cooldown.keySet())) {
            cooldown.put(id, cooldown.get(id) - 1);
        }
    }
}
