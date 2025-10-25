package com.example.examplemod.effect;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodData;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.*;

public class DementiaEffect extends MobEffect {

    private static final Map<UUID, Deque<PlayerSnapshot>> historyMap = new HashMap<>();
    private static final Map<UUID, Integer> tickCounters = new HashMap<>();

    private static final int SNAPSHOT_INTERVAL_TICKS = 5; // store snapshop every 0.25 seconds
    private static final int HISTORY_TICKS = 60; // 3 seconds of memory
    private static final double DEMENTIA_CHANCE = 1;
    private static final int CHECK_INTERVAL_TICKS = 200; // every 10 seconds

    public DementiaEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (!(entity instanceof ServerPlayer player)) return;
        if (!player.isAlive()) return;

        UUID id = player.getUUID();
        tickCounters.putIfAbsent(id, 0);
        int ticks = tickCounters.getOrDefault(id, 0) + 1;
        tickCounters.put(id, ticks);

        // Effect
        if (ticks % SNAPSHOT_INTERVAL_TICKS == 0) {
            historyMap.putIfAbsent(id, new ArrayDeque<>());
            Deque<PlayerSnapshot> history = historyMap.get(id);
            history.addFirst(new PlayerSnapshot(player));

            while (history.size() > HISTORY_TICKS / SNAPSHOT_INTERVAL_TICKS) {
                history.removeLast();
            }
        }

        int counter = tickCounters.get(id);
        Deque<PlayerSnapshot> history = historyMap.get(id);

        if (counter >= CHECK_INTERVAL_TICKS) {
            tickCounters.put(id, 0);

            if (Math.random() < DEMENTIA_CHANCE) {
                if (history != null && history.size() >= 12) {
                    PlayerSnapshot[] snapshots = history.toArray(new PlayerSnapshot[0]);
                    int index = 11;
                    if (index < snapshots.length) {
                        snapshots[index].restore(player);
                        player.displayClientMessage(Component.literal("§5Your mind slips..."), true);
                    }
                }
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }

    @SubscribeEvent
    public void onPlayerDeath(LivingDeathEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        UUID id = player.getUUID();
        DementiaEffect.historyMap.remove(id);
        DementiaEffect.tickCounters.remove(id);
    }

    public static void clearPlayerData(UUID playerId) {
        historyMap.remove(playerId);
        tickCounters.remove(playerId);
    }

    private static class PlayerSnapshot {
        private final double x, y, z;
        private final float health;
        private final int foodLevel;
        private final int fireTicks;
        private final float saturation;
        private final List<MobEffectInstance> effects;

        PlayerSnapshot(ServerPlayer player) {
            this.x = player.getX();
            this.y = player.getY();
            this.z = player.getZ();
            this.health = player.getHealth();

            FoodData food = player.getFoodData();
            this.foodLevel = food.getFoodLevel();
            this.saturation = food.getSaturationLevel();
            this.fireTicks = player.getRemainingFireTicks();

            this.effects = new ArrayList<>(player.getActiveEffects());
        }

        void restore(ServerPlayer player) {
            if (!player.isAlive()) return;
            player.teleportTo(player.serverLevel(), x, y, z, player.getYRot(), player.getXRot());
            player.setHealth(health);

            FoodData food = player.getFoodData();
            food.setFoodLevel(foodLevel);
            food.setSaturation(saturation);
            player.setRemainingFireTicks(fireTicks);

            MobEffectInstance currentDementia = player.getEffect(ModEffects.DEMENTIA.get());

            // Remove all effects first
            player.removeAllEffects();

            // --- Restore snapshot effects (duration rewound to snapshot) ---
            for (MobEffectInstance old : effects) {
                if (old.getEffect() != ModEffects.DEMENTIA.get()) {
                    // Restore exactly as snapshot, ignoring current durations
                    player.addEffect(new MobEffectInstance(old.getEffect(), old.getDuration(), old.getAmplifier()));
                }
            }

            // --- Reapply Dementia effect with current duration ---
            if (currentDementia != null) {
                player.addEffect(new MobEffectInstance(currentDementia));
            }
        }
    }
}
