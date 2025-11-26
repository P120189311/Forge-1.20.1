package com.p120189311.theendoflimbo.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class TheEndOfLimboTiers {
    // Damage by default = 1, AttackSpeed by default = 4
    public static final ForgeTier ABSOLUTE_BLACK = new ForgeTier(5, 500, 7f,
            2f, 5, BlockTags.NEEDS_IRON_TOOL,
            () -> Ingredient.of(TheEndOfLimboItems.BLACKY.get()));

    public static final ForgeTier ABSOLUTE_WHITE = new ForgeTier(5, 500, 7f,
            2f, 5, BlockTags.NEEDS_IRON_TOOL,
            () -> Ingredient.of(TheEndOfLimboItems.WHITY.get()));
}