package com.example.examplemod.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ModTiers {
    //Damage by default = 1, AttackSpeed by default = 4
    public static final ForgeTier ABSOLUTE_BLACK = new ForgeTier(5, 2000, 7f,
            2f, 5, BlockTags.NEEDS_IRON_TOOL,
            () -> Ingredient.of(ModItems.BLACKY.get()));

    public static final ForgeTier ABSOLUTE_WHITE = new ForgeTier(5, 2000, 7f,
            2f, 5, BlockTags.NEEDS_IRON_TOOL,
            () -> Ingredient.of(ModItems.WHITY.get()));
}