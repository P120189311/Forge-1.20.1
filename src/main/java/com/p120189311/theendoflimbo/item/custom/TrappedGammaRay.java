package com.p120189311.theendoflimbo.item.custom;

import com.p120189311.theendoflimbo.entity.custom.TrappedGammaRayProjectileEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class TrappedGammaRay extends Item {
    public TrappedGammaRay(Properties p_41383_) {
        super(p_41383_);
    }

    public InteractionResultHolder<ItemStack> use(Level p_43142_, Player p_43143_, InteractionHand p_43144_) {
        ItemStack $$3 = p_43143_.getItemInHand(p_43144_);
        p_43142_.playSound((Player)null, p_43143_.getX(), p_43143_.getY(), p_43143_.getZ(), SoundEvents.ALLAY_HURT, SoundSource.NEUTRAL, 0.5F, 1.5F / (p_43142_.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!p_43142_.isClientSide) {
            TrappedGammaRayProjectileEntity tgr = new TrappedGammaRayProjectileEntity(p_43142_, p_43143_);
            tgr.setItem($$3);
            tgr.shootFromRotation(p_43143_, p_43143_.getXRot(), p_43143_.getYRot(), 0.0F, 1.5F, 1.0F);
            p_43142_.addFreshEntity(tgr);
        }

        p_43143_.awardStat(Stats.ITEM_USED.get(this));
        if (!p_43143_.getAbilities().instabuild) {
            $$3.shrink(1);
        }

        return InteractionResultHolder.sidedSuccess($$3, p_43142_.isClientSide());
    }
}
