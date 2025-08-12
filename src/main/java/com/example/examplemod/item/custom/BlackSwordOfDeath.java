package com.example.examplemod.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BlackSwordOfDeath extends SwordItem {

    private static final List<DelayedEffect> delayedEffects = new ArrayList<>();

    public BlackSwordOfDeath(Tier p_43269_, int p_43270_, float p_43271_, Properties p_43272_) {
        super(p_43269_, p_43270_, p_43271_, p_43272_);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        if (!pTarget.level.isClientSide) {

            if (pAttacker instanceof net.minecraft.world.entity.player.Player player) {
                if (player.getCooldowns().isOnCooldown(this)) {
                    return super.hurtEnemy(pStack, pTarget, pAttacker); // Does Normal Damage Only
                }
                player.getCooldowns().addCooldown(this, 100);
            }

            if (pTarget.getType() == EntityType.WITHER_SKELETON || pTarget.getType() == EntityType.WITHER) {
                for (int i = 1; i <= 4; i++) {
                    delayedEffects.add(new DelayedEffect(pTarget, MobEffects.MOVEMENT_SLOWDOWN, pAttacker, 20 * i));
                }
                delayedEffects.add(new DelayedEffect(pTarget, MobEffects.HEAL, pAttacker, 100));
            } else if (pTarget.getMobType() == MobType.UNDEAD) {
                pTarget.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 1), pAttacker);
                delayedEffects.add(new DelayedEffect(pTarget, MobEffects.HEAL, pAttacker, 100));
            } else {
                pTarget.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 1), pAttacker);
                delayedEffects.add(new DelayedEffect(pTarget, MobEffects.HARM, pAttacker, 100));
            }
        }
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }

    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            Iterator<DelayedEffect> iterator = delayedEffects.iterator();
            while (iterator.hasNext()) {
                DelayedEffect eff = iterator.next();
                eff.ticks--;
                if (eff.ticks <= 0) {
                    if (eff.target.isAlive()) {
                        if (eff.effect == MobEffects.MOVEMENT_SLOWDOWN) {
                            eff.target.hurt(DamageSource.OUT_OF_WORLD, 1.0F);
                        } else if (eff.effect != null) {
                            eff.target.addEffect(new MobEffectInstance(eff.effect, 1), eff.attacker);
                        }
                        iterator.remove();
                    }
                }
            }
        }
    }

    private static class DelayedEffect {
        LivingEntity target;
        MobEffect effect;
        LivingEntity attacker;
        int ticks;

        DelayedEffect(LivingEntity target, MobEffect effect, LivingEntity attacker, int ticks) {
            this.target = target;
            this.effect = effect;
            this.attacker = attacker;
            this.ticks = ticks;
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("tooltip.examplemod.absolute_black_sword").withStyle(ChatFormatting.DARK_GRAY).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.BOLD));
    }
}