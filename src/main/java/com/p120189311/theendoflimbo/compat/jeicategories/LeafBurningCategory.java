package com.p120189311.theendoflimbo.compat.jeicategories;

import com.p120189311.theendoflimbo.block.TheEndOfLimboBlocks;
import com.p120189311.theendoflimbo.item.TheEndOfLimboItems;
import com.p120189311.theendoflimbo.recipe.LeafBurningRecipe;
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

public class LeafBurningCategory implements IRecipeCategory<LeafBurningRecipe> {

    public static final RecipeType<LeafBurningRecipe> LEAF_BURNING_TYPE =
            new RecipeType<>(new ResourceLocation("theendoflimbo", "leaf_burning"), LeafBurningRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public LeafBurningCategory(IGuiHelper gui) {
        background = gui.createBlankDrawable(168, 77);
        icon = gui.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(Blocks.OAK_LEAVES));
    }

    @Override
    public RecipeType<LeafBurningRecipe> getRecipeType() {
        return LEAF_BURNING_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.literal("Leaf Burning");
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
    public void setRecipe(IRecipeLayoutBuilder builder, LeafBurningRecipe recipe, IFocusGroup focuses) {

        // Input: leaves (center-left)
        builder.addSlot(RecipeIngredientRole.INPUT, 22, 31)
                .addItemStack(recipe.getInput());

        // Output 1: Stick
        builder.addSlot(RecipeIngredientRole.OUTPUT, 58, 15)
                .addItemStack(new ItemStack(Items.STICK));

        // Output 2: Sapling
        builder.addSlot(RecipeIngredientRole.OUTPUT, 90, 15)
                .addItemStack(new ItemStack(TheEndOfLimboBlocks.ABYSS_SAPLING.get()));

        // Output 3: Nocturnal Dwight
        builder.addSlot(RecipeIngredientRole.OUTPUT, 122, 15)
                .addItemStack(new ItemStack(TheEndOfLimboItems.NOCTURNAL_DWIGHT.get()));
    }

    @Override
    public void draw(LeafBurningRecipe recipe, IRecipeSlotsView view, GuiGraphics gui, double mouseX, double mouseY) {
        int stickChance = 5;
        int saplingChance = 5;
        int dwightChance = 1;

        // Centered text helper
        Font font = Minecraft.getInstance().font;

        drawCentered(gui, font, stickChance + "%", 58 + 8, 0, 0xFFFFFF);   // Stick
        drawCentered(gui, font, saplingChance + "%", 90 + 8, 0, 0xFFFFFF); // Sapling
        drawCentered(gui, font, dwightChance + "%", 122 + 8, 0, 0xFFFFFF);  // Dwight
    }

    private void drawCentered(GuiGraphics gui, Font font, String text, int centerX, int y, int color) {
        int width = font.width(text);
        gui.drawString(font, text, centerX - width / 2, y, color, false);
    }
}