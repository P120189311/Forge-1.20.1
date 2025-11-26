package com.p120189311.theendoflimbo.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BlackHoleArmorText extends ArmorItem {
    public BlackHoleArmorText(ArmorMaterial p_40386_, ArmorItem.Type p_40387_, Properties p_40388_) {
        super(p_40386_, p_40387_, p_40388_);
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("tooltip.theendoflimbo.black_hole_armor").withStyle(ChatFormatting.DARK_GRAY).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.BOLD));
    }
}
