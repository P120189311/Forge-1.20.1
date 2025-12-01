package com.p120189311.theendoflimbo.compat.jeicategories;

import com.p120189311.theendoflimbo.TheEndOfLimboMod;
import com.p120189311.theendoflimbo.block.TheEndOfLimboBlocks;
import com.p120189311.theendoflimbo.item.TheEndOfLimboItems;
import com.p120189311.theendoflimbo.recipe.LeafBurningRecipe;
import com.p120189311.theendoflimbo.recipe.MysteryStandRecipe;
import com.p120189311.theendoflimbo.recipe.PurificationRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

public class PurificationCategory implements IRecipeCategory<PurificationRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(TheEndOfLimboMod.MAIN, "purification");

    public static final RecipeType<PurificationRecipe> PURIFICATION_TYPE =
            new RecipeType<>(UID, PurificationRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public PurificationCategory(IGuiHelper gui) {
        background = gui.createBlankDrawable(168, 77);
        icon = gui.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(TheEndOfLimboItems.NOCTURNAL_DWIGHT.get()));
    }

    @Override
    public RecipeType<PurificationRecipe> getRecipeType() {
        return PURIFICATION_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.literal("Purification");
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, PurificationRecipe recipe, IFocusGroup focuses) {

        builder.addSlot(RecipeIngredientRole.INPUT, 22, 31).addIngredients(recipe.getInput());

        builder.addSlot(RecipeIngredientRole.CATALYST, 76, 0).addIngredients(recipe.getCatalyst());

        builder.addSlot(RecipeIngredientRole.OUTPUT, 129, 31).addItemStack(recipe.getOutput());
    }
}