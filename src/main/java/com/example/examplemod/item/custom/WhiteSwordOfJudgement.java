package com.example.examplemod.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.SpectralArrow;
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

public class WhiteSwordOfJudgement extends SwordItem {
    public WhiteSwordOfJudgement(Tier p_43269_, int p_43270_, float p_43271_, Properties p_43272_) {
        super(p_43269_, p_43270_, p_43271_, p_43272_);
    }
    private static final List<DelayedArrow> delayedArrows = new ArrayList<>();


    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        Level level = pAttacker.getCommandSenderWorld();

        if (level.isClientSide) {
            return super.hurtEnemy(pStack, pTarget, pAttacker);
        }

        if (pAttacker instanceof net.minecraft.world.entity.player.Player player) {
            if (player.getCooldowns().isOnCooldown(pStack.getItem())) {
                return super.hurtEnemy(pStack, pTarget, pAttacker);
            }

            player.getCooldowns().addCooldown(pStack.getItem(), 100);
        }

        for (int i = 0; i < 6; i++) {
            delayedArrows.add(new DelayedArrow(level, pAttacker, pTarget, 12 + i * 12));
        }
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }

    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            Iterator<WhiteSwordOfJudgement.DelayedArrow> iterator = delayedArrows.iterator();
            while (iterator.hasNext()) {
                WhiteSwordOfJudgement.DelayedArrow da = iterator.next();
                da.ticks--;
                if (da.ticks <= 0) {
                    if (da.target.isAlive()) {
                        double targetX = da.target.getX();
                        double targetY = da.target.getY() + da.target.getBbHeight() * 0.5;
                        double targetZ = da.target.getZ();

                        double angle = Math.random() * Math.PI * 2;
                        double distance = 6.0 + Math.random() * 2.0;
                        double spawnX = targetX + Math.cos(angle) * distance;
                        double spawnY = targetY + 6 + Math.random() * 2;
                        double spawnZ = targetZ + Math.sin(angle) * distance;

                        SpectralArrow arrow = new SpectralArrow(da.level, da.attacker);
                        arrow.setPos(spawnX, spawnY, spawnZ);

                        double dirX = targetX - spawnX;
                        double dirY = targetY - spawnY;
                        double dirZ = targetZ - spawnZ;
                        double length = Math.sqrt(dirX * dirX + dirY * dirY + dirZ * dirZ);
                        arrow.setDeltaMovement(dirX / length * 1.6, dirY / length * 1.6, dirZ / length * 1.6);

                        arrow.setBaseDamage(1);
                        arrow.setCritArrow(true);
                        arrow.pickup = Arrow.Pickup.DISALLOWED;

                        da.level.addFreshEntity(arrow);
                    }
                    iterator.remove();
                }
            }
        }
    }

    private static class DelayedArrow {
        Level level;
        LivingEntity attacker;
        LivingEntity target;
        int ticks;

        DelayedArrow(Level level, LivingEntity attacker, LivingEntity target, int ticks) {
            this.level = level;
            this.attacker = attacker;
            this.target = target;
            this.ticks = ticks;
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("tooltip.examplemod.absolute_white_sword").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.BOLD));
    }
}

