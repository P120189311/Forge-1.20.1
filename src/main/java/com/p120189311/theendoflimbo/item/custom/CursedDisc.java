package com.p120189311.theendoflimbo.item.custom;

import com.p120189311.theendoflimbo.sound.TheEndOfLimboSounds;
import com.p120189311.theendoflimbo.worldgen.dimension.AbyssDimension;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.protocol.game.ClientboundSoundEntityPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.JukeboxBlock;
import net.minecraft.world.level.block.entity.JukeboxBlockEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.*;

public class CursedDisc extends RecordItem {
    private static final Random RANDOM = new Random();

    public CursedDisc(int comparatorValue, net.minecraft.sounds.SoundEvent sound, Properties properties, int lengthInTicks) {
        super(comparatorValue, sound, properties, lengthInTicks);
    }

    private static final Map<UUID, Integer> tpCooldown = new HashMap<>();

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        var player = context.getPlayer();

        InteractionResult result = super.useOn(context);

        if (!level.isClientSide() && player instanceof ServerPlayer serverPlayer) {
            if (level.getBlockState(pos).getBlock() instanceof JukeboxBlock) {
                JukeboxBlockEntity jukebox = (JukeboxBlockEntity) level.getBlockEntity(pos);

                if (jukebox != null && !jukebox.getFirstItem().isEmpty()) {
                    RandomSource random = level.getRandom();

                    if (random.nextFloat() < 0.5f) { // 50% chance
                        MinecraftForge.EVENT_BUS.register(new DelayedSoundTask((ServerLevel) level, serverPlayer, 200));

                        UUID id = serverPlayer.getUUID();

                        // BLOCK IF PLAYER IS STILL ON COOLDOWN
                        if (tpCooldown.containsKey(id)) {
                            return result;
                        }

                        tpCooldown.put(id, 1600);

                        ServerLevel customLevel =
                                serverPlayer.getServer().getLevel(AbyssDimension.ABYSS_DIMENSION_LEVEL_KEY);

                        if (customLevel != null) {
                            MinecraftForge.EVENT_BUS.register(
                                    new DelayedTeleportTask(customLevel, serverPlayer, 800)
                            );
                        }
                    }
                }
            }
        }

        return result;
    }

    /** Helper class that waits X ticks before playing the sound for that player **/
    private static class DelayedSoundTask {
        private final ServerLevel level;
        private final ServerPlayer player;
        private int ticksLeft;

        public DelayedSoundTask(ServerLevel level, ServerPlayer player, int delayTicks) {
            this.level = level;
            this.player = player;
            this.ticksLeft = delayTicks;
        }

        @SubscribeEvent
        public void onServerTick(TickEvent.ServerTickEvent event) {
            if (event.phase != TickEvent.Phase.END) return;

            if (--ticksLeft <= 0) {
                if (player.isAlive()) {
                    player.connection.send(
                            new ClientboundSoundEntityPacket(
                                    Holder.direct(TheEndOfLimboSounds.ABHTCAQ.get()),
                                    SoundSource.RECORDS,
                                    player,
                                    2F,
                                    0.85F,
                                    level.random.nextLong()
                            )
                    );
                }
                // Unregister this task once done
                MinecraftForge.EVENT_BUS.unregister(this);
            }
        }
    }

    private static class DelayedTeleportTask {

        private final ServerLevel level;
        private final ServerPlayer player;
        private int ticksLeft;

        public DelayedTeleportTask(ServerLevel level, ServerPlayer player, int delayTicks) {
            this.level = level;
            this.player = player;
            this.ticksLeft = delayTicks;
        }

        @SubscribeEvent
        public void onServerTick(TickEvent.ServerTickEvent event) {
            if (event.phase != TickEvent.Phase.END) return;

            if (--ticksLeft <= 0) {

                if (!player.isAlive()) {
                    MinecraftForge.EVENT_BUS.unregister(this);
                    return;
                }

                ServerLevel overworld = player.getServer().overworld();
                ServerLevel abyss = player.getServer().getLevel(AbyssDimension.ABYSS_DIMENSION_LEVEL_KEY);

                if (overworld == null || abyss == null) {
                    MinecraftForge.EVENT_BUS.unregister(this);
                    return;
                }

                ServerLevel destination;

                if (player.level() == abyss) {
                    destination = overworld;
                } else {
                    destination = abyss;
                }

                BlockPos spawnPos = destination.getSharedSpawnPos();

                player.teleportTo(
                        destination,
                        spawnPos.getX() + 0.5,
                        spawnPos.getY(),
                        spawnPos.getZ() + 0.5,
                        player.getYRot(),
                        player.getXRot()
                );

                player.fallDistance = 0;

                MinecraftForge.EVENT_BUS.unregister(this);
            }
        }
    }

    public static Map<UUID, Integer> getCooldownMap() {
        return tpCooldown;
    }
}