package com.example.examplemod.effect;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.network.PlayDementiaMusicPacket;
import com.example.examplemod.sound.ModSounds;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.network.PacketDistributor;

import java.util.*;

import static com.mojang.text2speech.Narrator.LOGGER;

public class DementiaEffect extends MobEffect {

    private static final Set<UUID> playedSound = new HashSet<>();
    private static final Map<UUID, Deque<PlayerSnapshot>> historyMap = new HashMap<>();
    private static final Map<UUID, Integer> tickCounters = new HashMap<>();

    private static final int SNAPSHOT_INTERVAL_TICKS = 10; // store snapshot every 0.5 seconds
    private static final int HISTORY_TICKS = 200; // 10 seconds of memory
    private static final double DEMENTIA_CHANCE = 0.3;

    private static final Map<UUID, Integer> nextCheckInterval = new HashMap<>();

    public DementiaEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (!(entity instanceof ServerPlayer player)) return;
        if (!player.isAlive()) return;

        UUID id = player.getUUID();
        tickCounters.putIfAbsent(id, 0);
        nextCheckInterval.putIfAbsent(id, getRandomInterval());

        int ticks = tickCounters.get(id) + 1;
        tickCounters.put(id, ticks);

        // Store snapshots periodically
        if (ticks % SNAPSHOT_INTERVAL_TICKS == 0) {
            historyMap.putIfAbsent(id, new ArrayDeque<>());
            Deque<PlayerSnapshot> history = historyMap.get(id);
            history.addFirst(new PlayerSnapshot(player));

            while (history.size() > HISTORY_TICKS / SNAPSHOT_INTERVAL_TICKS) {
                history.removeLast();
            }
        }

        int targetInterval = nextCheckInterval.get(id);
        Deque<PlayerSnapshot> history = historyMap.get(id);

        if (ticks >= targetInterval) {
            tickCounters.put(id, 0);
            nextCheckInterval.put(id, getRandomInterval()); // new interval for next trigger

            if (Math.random() < DEMENTIA_CHANCE) {
                if (history != null && history.size() >= 20) {
                    PlayerSnapshot[] snapshots = history.toArray(new PlayerSnapshot[0]);
                    int index = 19;
                    if (index < snapshots.length) {
                        snapshots[index].restore(player);
                        player.displayClientMessage(Component.literal("§5Your mind slips..."), true);

                        // Sound
                        Random random = new Random();
                        int choice = random.nextInt(3);
                        String soundId = switch (choice) {
                            case 0 -> "examplemod:libets_delay_short";
                            case 1 -> "examplemod:drifting_time_misplaced_short";
                            default -> "examplemod:its_just_a_burning_memory_short";
                        };

                        ExampleMod.CHANNEL.send(PacketDistributor.PLAYER.with(() -> player),
                                new PlayDementiaMusicPacket(soundId));
                    }
                }
            }
        }
    }

    private int getRandomInterval() {
        return 1200 + (int) (Math.random() * 2400); // 1200–3600 ticks
    }

    @Override
    public boolean isBeneficial() {
        return true;
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

    @Override
    public void removeAttributeModifiers(LivingEntity entity, AttributeMap map, int amplifier) {
        if (entity instanceof ServerPlayer player) {
            playedSound.remove(player.getUUID());
        }
        super.removeAttributeModifiers(entity, map, amplifier);
    }

    private static class PlayerSnapshot {
        private final ResourceKey<Level> dimension;
        private final double x, y, z;
        private final float yaw, pitch;
        private final float health;
        private final int foodLevel;
        private final int fireTicks;
        private final float saturation;
        private final List<MobEffectInstance> effects;

        PlayerSnapshot(ServerPlayer player) {
            this.dimension = player.level().dimension();
            this.x = player.getX();
            this.y = player.getY();
            this.z = player.getZ();
            this.yaw = player.getYRot();
            this.pitch = player.getXRot();
            this.health = player.getHealth();

            FoodData food = player.getFoodData();
            this.foodLevel = food.getFoodLevel();
            this.saturation = food.getSaturationLevel();
            this.fireTicks = player.getRemainingFireTicks();

            this.effects = new ArrayList<>(player.getActiveEffects());
        }

        void restore(ServerPlayer player) {
            if (!player.isAlive()) return;
            ServerLevel targetLevel = player.getServer().getLevel(dimension);
            if (targetLevel != null) {
                player.teleportTo(targetLevel, x, y, z, yaw, pitch);
            }

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
