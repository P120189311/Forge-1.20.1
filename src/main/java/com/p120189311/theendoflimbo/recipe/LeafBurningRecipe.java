package com.p120189311.theendoflimbo.recipe;

import net.minecraft.world.item.ItemStack;

public class LeafBurningRecipe {
    private final ItemStack input;
    private final ItemStack output;
    private final float chance;

    public LeafBurningRecipe(ItemStack input, ItemStack output, float chance) {
        this.input = input;
        this.output = output;
        this.chance = chance;
    }

    public ItemStack getInput() {
        return input;
    }

    public ItemStack getOutput() {
        return output;
    }

    public float getChance() {
        return chance;
    }
}
