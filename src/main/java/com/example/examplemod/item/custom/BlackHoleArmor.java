package com.example.examplemod.item.custom;

import com.example.examplemod.item.ModArmorMaterials;
import com.google.common.collect.ImmutableMap;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public class BlackHoleArmor extends ArmorItem {

    private static final Map<ArmorMaterial, MobEffectInstance> MATERIAL_TO_EFFECT_MAP =
            (new ImmutableMap.Builder<ArmorMaterial, MobEffectInstance>())
                    .put(ModArmorMaterials.ABSOLUTE_BLACK,
                            new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 120, 0, true,false, true)).build();

    public BlackHoleArmor(ArmorMaterial p_40386_, ArmorItem.Type p_40387_, Properties p_40388_) {
        super(p_40386_, p_40387_, p_40388_);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        if (!world.isClientSide) {
            double outerRadius = 25.0;
            double damageRadius = 3.0;

            List<LivingEntity> entities = world.getEntitiesOfClass(
                    LivingEntity.class,
                    player.getBoundingBox().inflate(outerRadius),
                    e -> e != player && e.isAlive() && !(e instanceof ArmorStand)
            );

            for (LivingEntity target : entities) {
                double dist = player.distanceTo(target);

                MobType type = target.getMobType();
                if (type == MobType.UNDEAD || type == MobType.ARTHROPOD || type == MobType.ILLAGER) {

                    if (dist <= outerRadius && dist > damageRadius) {
                        // Closer = faster pull
                        double strength = Math.pow(Math.max(0, (outerRadius - dist) / (outerRadius - damageRadius)), 1.5);
                        double speed = 0.01 + strength * 0.03;
                        Vec3 dir = player.position().subtract(target.position()).normalize().scale(speed); // pull speed
                        target.setDeltaMovement(target.getDeltaMovement().add(dir));
                    }

                    // Apply Void Damage if within 3 blocks
                    if (dist <= damageRadius) {
                        applyVoidDamageWithCooldown(target, world);
                    }
                }
            }
                if (isWearingFullBlackSet(player)) {
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

    private static boolean isWearingFullBlackSet(Player player) {
        for (ItemStack armorStack : player.getInventory().armor) {
            if (!(armorStack.getItem() instanceof ArmorItem)) return false;
        }
        ArmorItem boots = (ArmorItem) player.getInventory().getArmor(0).getItem();
        ArmorItem leggings = (ArmorItem) player.getInventory().getArmor(1).getItem();
        ArmorItem chestplate = (ArmorItem) player.getInventory().getArmor(2).getItem();
        ArmorItem helmet = (ArmorItem) player.getInventory().getArmor(3).getItem();

        return boots.getMaterial() == ModArmorMaterials.ABSOLUTE_BLACK
                && leggings.getMaterial() == ModArmorMaterials.ABSOLUTE_BLACK
                && chestplate.getMaterial() == ModArmorMaterials.ABSOLUTE_BLACK
                && helmet.getMaterial() == ModArmorMaterials.ABSOLUTE_BLACK;
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

    public static final String LAST_VOID_DAMAGE_TAG = "LastVoidDamageTick";

    public void applyVoidDamageWithCooldown(LivingEntity target, Level world) {
        int currentTick = (int) world.getGameTime();
        CompoundTag tag = target.getPersistentData();

        int lastDamageTick = tag.getInt(LAST_VOID_DAMAGE_TAG);

        if (currentTick - lastDamageTick >= 20) {
            target.hurt(target.damageSources().fellOutOfWorld(), 2.0F); // Every 1.0F means 0.5 Hearts of damage
            tag.putInt(LAST_VOID_DAMAGE_TAG, currentTick);
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("tooltip.examplemod.black_hole_armor").withStyle(ChatFormatting.DARK_GRAY).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.BOLD));
    }
}
