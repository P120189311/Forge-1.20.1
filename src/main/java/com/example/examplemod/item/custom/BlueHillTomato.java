package com.example.examplemod.item.custom;

import com.example.examplemod.sound.ModSounds;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BlueHillTomato extends Item {
    public BlueHillTomato(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, net.minecraft.world.entity.LivingEntity entity) {
        ItemStack result = super.finishUsingItem(stack, level, entity);

        if (!level.isClientSide && entity instanceof Player player) {
            if (player.getRandom().nextFloat() < 0.25f) {
                level.playSound(null, player.getX(), player.getY(), player.getZ(),
                        ModSounds.BLUE_HILL_TOMATO_EAT_CHANCE.get(),
                        SoundSource.PLAYERS,
                        1.0F, 1.0F
                );
            }
        }

        return result;
    }
}

