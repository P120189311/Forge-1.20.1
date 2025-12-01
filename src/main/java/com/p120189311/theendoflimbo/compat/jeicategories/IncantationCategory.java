package com.p120189311.theendoflimbo.compat.jeicategories;

import com.p120189311.theendoflimbo.TheEndOfLimboMod;
import com.p120189311.theendoflimbo.block.TheEndOfLimboBlocks;
import com.p120189311.theendoflimbo.recipe.MysteryStandRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class IncantationCategory implements IRecipeCategory<MysteryStandRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(TheEndOfLimboMod.MAIN, "incantation");
    public static final ResourceLocation TEXTURE = new ResourceLocation(TheEndOfLimboMod.MAIN, "textures/gui/mystery_stand_incantation_gui.png");

    public static final RecipeType<MysteryStandRecipe> MYSTERY_STAND_TYPE =
            new RecipeType<>(UID, MysteryStandRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public IncantationCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE,4,4,168,77);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(TheEndOfLimboBlocks.MYSTERY_STAND.get()));
    }

    @Override
    public RecipeType<MysteryStandRecipe> getRecipeType() {
        return MYSTERY_STAND_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.theendoflimbo.mystery_stand");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, MysteryStandRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT,22,7).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT,130,7).addIngredients(recipe.getIngredients().get(1));
        builder.addSlot(RecipeIngredientRole.INPUT,22,54).addIngredients(recipe.getIngredients().get(2));
        builder.addSlot(RecipeIngredientRole.INPUT,130,54).addIngredients(recipe.getIngredients().get(3));

        builder.addSlot(RecipeIngredientRole.OUTPUT,76,31).addItemStack(recipe.getResultItem(null));
    }
}
