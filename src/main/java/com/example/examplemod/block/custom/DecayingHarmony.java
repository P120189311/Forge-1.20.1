package com.example.examplemod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

public class DecayingHarmony extends FlowerBlock {
    public DecayingHarmony(Properties properties) {
        // Suspicious Stew gives Blindness (5 seconds)
        super(() -> MobEffects.BLINDNESS, 100, properties);
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        super.entityInside(state, level, pos, entity);

        if (!level.isClientSide && entity instanceof LivingEntity livingEntity) {
            // Apply Blindness for 5 seconds
            livingEntity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 100, 0));
            // 100 ticks = 5 seconds
        }
    }
}
