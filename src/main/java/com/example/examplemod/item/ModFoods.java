package com.example.examplemod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties VOID_OF_KNOWLEDGE;

    static {
        VOID_OF_KNOWLEDGE = (new FoodProperties.Builder()).nutrition(20).saturationMod(1F)
                .effect(new MobEffectInstance(MobEffects.BLINDNESS,200),1.0F)
                .effect(new MobEffectInstance(MobEffects.JUMP,200),1.0F)
                .effect(new MobEffectInstance(MobEffects.ABSORPTION,200),1.0F)
                .effect(new MobEffectInstance(MobEffects.BAD_OMEN,200),1.0F)
                .effect(new MobEffectInstance(MobEffects.CONFUSION,200),1.0F)
                .effect(new MobEffectInstance(MobEffects.CONDUIT_POWER,200),1.0F)
                .effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST,200),1.0F)
                .effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE,200),1.0F)
                .effect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN,200),1.0F)
                .effect(new MobEffectInstance(MobEffects.DIG_SPEED,200),1.0F)
                .effect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE,200),1.0F)
                .effect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE,200),1.0F)
                .effect(new MobEffectInstance(MobEffects.GLOWING,200),1.0F)
                .effect(new MobEffectInstance(MobEffects.HEALTH_BOOST,200),1.0F)
                .effect(new MobEffectInstance(MobEffects.HERO_OF_THE_VILLAGE,200),1.0F)
                .effect(new MobEffectInstance(MobEffects.HUNGER,200),1.0F)
                .effect(new MobEffectInstance(MobEffects.INVISIBILITY,200),1.0F)
                .effect(new MobEffectInstance(MobEffects.LEVITATION,200),1.0F)
                .effect(new MobEffectInstance(MobEffects.LUCK,200),1.0F)
                .effect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,200),1.0F)
                .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,200),1.0F)
                .effect(new MobEffectInstance(MobEffects.NIGHT_VISION,200),1.0F)
                .effect(new MobEffectInstance(MobEffects.POISON,200),1.0F)
                .effect(new MobEffectInstance(MobEffects.REGENERATION,200),1.0F)
                .effect(new MobEffectInstance(MobEffects.SLOW_FALLING,200),1.0F)
                .effect(new MobEffectInstance(MobEffects.UNLUCK,200),1.0F)
                .effect(new MobEffectInstance(MobEffects.WATER_BREATHING,200),1.0F)
                .effect(new MobEffectInstance(MobEffects.WEAKNESS,200),1.0F)
                .effect(new MobEffectInstance(MobEffects.WITHER,200),1.0F)
                .effect(new MobEffectInstance(MobEffects.DARKNESS,200),1.0F)
                .effect(new MobEffectInstance(MobEffects.HEAL,1,10),1.0F)
                .alwaysEat().build();
    }
}
