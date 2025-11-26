package com.p120189311.theendoflimbo.network;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class PlayDementiaMusicPacket {

    private final String soundId;

    public PlayDementiaMusicPacket(String soundId) {
        this.soundId = soundId;
    }

    // ===== ENCODE =====
    public static void encode(PlayDementiaMusicPacket msg, net.minecraft.network.FriendlyByteBuf buf) {
        buf.writeUtf(msg.soundId);
    }

    // ===== DECODE =====
    public static PlayDementiaMusicPacket decode(net.minecraft.network.FriendlyByteBuf buf) {
        return new PlayDementiaMusicPacket(buf.readUtf());
    }

    // ===== HANDLE =====
    public static void handle(PlayDementiaMusicPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
            Minecraft mc = Minecraft.getInstance();
            LocalPlayer player = mc.player;
            if (player == null || mc.level == null) return;

            SoundEvent sound = ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(msg.soundId));

            // Play following the player (client-side)
            player.playNotifySound(sound, SoundSource.RECORDS, 1.0f, 1.0f);
        }));
        ctx.get().setPacketHandled(true);
    }

    // ===== CLIENT-SIDE PLAYBACK =====
    @OnlyIn(Dist.CLIENT)
    private static void playMusicClient(String soundId) {
        Minecraft mc = Minecraft.getInstance();
        Level level = mc.level;
        if (level == null) return;

        SoundEvent sound = ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(soundId));
        if (sound == null) {
            System.err.println("[DementiaEffect] Unknown sound ID: " + soundId);
            return;
        }

        // Play locally, following the player
        mc.getSoundManager().play(SimpleSoundInstance.forMusic(sound));
        // OR: if you want positional playback that moves with the player:
        // mc.getSoundManager().play(SimpleSoundInstance.forLocalAmbience(sound, 1.0F, 1.0F));

        System.out.println("[DementiaEffect] Playing sound: " + soundId);
    }
}