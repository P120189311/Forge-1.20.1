package com.example.examplemod.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PurificatingWhiteArmorText extends ArmorItem {
    public PurificatingWhiteArmorText(ArmorMaterial p_40386_, ArmorItem.Type p_40387_, Properties p_40388_) {
        super(p_40386_, p_40387_, p_40388_);
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("tooltip.examplemod.purificating_white_armor").withStyle(ChatFormatting.WHITE).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.BOLD));
    }
}
