package com.example.examplemod.item.custom;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.item.ModArmorMaterials;
import com.google.common.collect.ImmutableMap;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public class PurificatingWhiteArmor extends ArmorItem {

    private static final int HEAL_INTERVAL = 100; // 5 seconds (20 ticks × 5)
    private static final float HEAL_AMOUNT = 2.0F; // a heart
    private static final double HEAL_RADIUS = 5.0D; // 5 block radius
    private static final int PARTICLE_INTERVAL = 2;

    private static final Map<ArmorMaterial, MobEffectInstance> MATERIAL_TO_EFFECT_MAP =
        (new ImmutableMap.Builder<ArmorMaterial, MobEffectInstance>())
                .put(ModArmorMaterials.ABSOLUTE_WHITE,
                        new MobEffectInstance(MobEffects.REGENERATION, 120, 0, true, false, true)).build();

    public PurificatingWhiteArmor(ArmorMaterial p_40386_, Type p_266831_, Properties p_40388_) {
        super(p_40386_, p_266831_, p_40388_);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        if(!world.isClientSide()) {
            if (isWearingFullWhiteSet(player)) {
                evaluateArmorEffects(player);
            }
        }
    }

    private void evaluateArmorEffects(Player player) {
        for (Map.Entry<ArmorMaterial, MobEffectInstance> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {
            ArmorMaterial mapArmorMaterial = entry.getKey();
            MobEffectInstance mapStatusEffect = entry.getValue();

            if(hasCorrectArmorOn(mapArmorMaterial, player)) {
                addStatusEffectForMaterial(player, mapArmorMaterial, mapStatusEffect);
            }
        }
    }

    private void addStatusEffectForMaterial(Player player, ArmorMaterial mapArmorMaterial,
                                            MobEffectInstance mapStatusEffect) {
        boolean hasPlayerEffect = player.hasEffect(mapStatusEffect.getEffect());

        if(hasCorrectArmorOn(mapArmorMaterial, player) && !hasPlayerEffect) {
            player.addEffect(new MobEffectInstance(mapStatusEffect));
        }
    }

    private static boolean isWearingFullWhiteSet(Player player) {
        for (ItemStack armorStack : player.getInventory().armor) {
            if (!(armorStack.getItem() instanceof ArmorItem)) return false;
        }
        ArmorItem boots = (ArmorItem) player.getInventory().getArmor(0).getItem();
        ArmorItem leggings = (ArmorItem) player.getInventory().getArmor(1).getItem();
        ArmorItem chestplate = (ArmorItem) player.getInventory().getArmor(2).getItem();
        ArmorItem helmet = (ArmorItem) player.getInventory().getArmor(3).getItem();

        return boots.getMaterial() == ModArmorMaterials.ABSOLUTE_WHITE
                && leggings.getMaterial() == ModArmorMaterials.ABSOLUTE_WHITE
                && chestplate.getMaterial() == ModArmorMaterials.ABSOLUTE_WHITE
                && helmet.getMaterial() == ModArmorMaterials.ABSOLUTE_WHITE;
    }

    private boolean hasCorrectArmorOn(ArmorMaterial material, Player player) {
        for (ItemStack armorStack : player.getInventory().armor) {
            if (!(armorStack.getItem() instanceof ArmorItem)) {
                return false;
            }
        }
        ArmorItem boots = ((ArmorItem)player.getInventory().getArmor(0).getItem());
        ArmorItem leggings = ((ArmorItem)player.getInventory().getArmor(1).getItem());
        ArmorItem breastplate = ((ArmorItem)player.getInventory().getArmor(2).getItem());
        ArmorItem helmet = ((ArmorItem)player.getInventory().getArmor(3).getItem());

        return helmet.getMaterial() == material && breastplate.getMaterial() == material &&
                leggings.getMaterial() == material && boots.getMaterial() == material;
    }

    @Mod.EventBusSubscriber(modid = ExampleMod.MOD_ID)
    public static class WhiteArmorHealingHandler {

        @SubscribeEvent
        public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
            Player player = event.player;

            if (event.phase != TickEvent.Phase.END) return;

            if (!isWearingFullWhiteSet(player)) return;

            // Healing every HEAL_INTERVAL ticks
            if (player.tickCount % HEAL_INTERVAL == 0) {
                if (!player.level().isClientSide) {
                    healNearby(player, HEAL_RADIUS, HEAL_AMOUNT);
                } else {
                    // Only on client, spawn hearts above healed entities
                    List<LivingEntity> healedEntities = player.level().getEntitiesOfClass(
                            LivingEntity.class,
                            player.getBoundingBox().inflate(HEAL_RADIUS),
                            e -> e.isAlive() && !(e instanceof Enemy)
                    );

                    for (LivingEntity entity : healedEntities) {
                        player.level().addParticle(
                                ParticleTypes.HEART,
                                entity.getX(),
                                entity.getY() + entity.getBbHeight() + 0.5,
                                entity.getZ(),
                                0, 0.1, 0
                        );
                    }
                }
            }

            // Particles every PARTICLE_INTERVAL ticks (client-side only)
            if (player.level().isClientSide && player.tickCount % PARTICLE_INTERVAL == 0) {
                spawnHealingParticles(player, HEAL_RADIUS);

            }
        }

        // Heal all non-hostile living entities around the player
        private static List<LivingEntity> healNearby(Player player, double radius, float amount) {
            AABB area = player.getBoundingBox().inflate(radius);
            List<LivingEntity> entities = player.level().getEntitiesOfClass(LivingEntity.class, area,
                    e -> e.isAlive() && !(e instanceof Enemy)
            );

            for (LivingEntity entity : entities) {

                if (BlackSwordOfDeath.hasPendingEffect(entity)) continue;
                // Remove all negative effects
                entity.getActiveEffects().forEach(effect -> {
                    if (!effect.getEffect().isBeneficial()) entity.removeEffect(effect.getEffect());
                });

                // Heal after removing bad effects
                entity.heal(amount);
            }
            return entities;
        }

        // Heal Players only
        /*private static void healNearby(Player player, double radius, float amount) {
    List<Player> nearbyPlayers = player.level().getEntitiesOfClass(
            Player.class,
            player.getBoundingBox().inflate(radius),
            p -> p != null && p.isAlive() && !p.isSpectator()
    );

    for (Player target : nearbyPlayers) {
        // Remove all negative effects from the player
        target.getActiveEffects().forEach(effect -> {
            if (!effect.getEffect().isBeneficial()) {
                target.removeEffect(effect.getEffect());
            }
        });

        // Heal after removing negative effects
        target.heal(amount); // 1.0F = half a heart
    }
}*/

    }

    private static void spawnHealingParticles(Player player, double radius) {
        int particleCount = 80; // Smoother circle = add 40
        for (int i = 0; i < particleCount; i++) {
            double angle = 2 * Math.PI * i / particleCount;
            double x = player.getX() + Math.cos(angle) * radius;
            double y = player.getY() + 0D; // Height Adjustment
            double z = player.getZ() + Math.sin(angle) * radius;
            player.level().addParticle(ParticleTypes.ELECTRIC_SPARK, x, y, z, 0, 0, 0);
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("tooltip.examplemod.purificating_white_armor").withStyle(ChatFormatting.WHITE).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.BOLD));
    }
}

