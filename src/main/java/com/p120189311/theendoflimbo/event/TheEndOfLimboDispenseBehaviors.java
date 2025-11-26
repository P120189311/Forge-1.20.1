package com.p120189311.theendoflimbo.event;

import com.p120189311.theendoflimbo.entity.custom.TrappedGammaRayProjectileEntity;
import com.p120189311.theendoflimbo.item.TheEndOfLimboItems;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;

public class TheEndOfLimboDispenseBehaviors {
    public static void register() {
        DispenserBlock.registerBehavior(TheEndOfLimboItems.TRAPPED_GAMMA_RAY.get(), new AbstractProjectileDispenseBehavior() {
            @Override
            protected Projectile getProjectile(Level level, Position pos, ItemStack stack) {
                TrappedGammaRayProjectileEntity projectile = new TrappedGammaRayProjectileEntity(level, pos.x(), pos.y(), pos.z());
                projectile.setItem(stack);
                return projectile;
            }

            @Override
            protected float getPower() {
                // Control how fast it shoots (default snowball = 1.1F)
                return 1.1F;
            }

            @Override
            protected float getUncertainty() {
                // Spread / inaccuracy
                return 6.0F;
            }
        });
    }
}