package com.p120189311.theendoflimbo.compat;

import com.p120189311.theendoflimbo.TheEndOfLimboMod;
import com.p120189311.theendoflimbo.recipe.MysteryStandRecipe;
import com.p120189311.theendoflimbo.screen.MysteryStandScreen;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;

@JeiPlugin
public class JEITheEndOfLimboPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(TheEndOfLimboMod.MAIN, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new IncantationCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<MysteryStandRecipe> incantationRecipes = recipeManager.getAllRecipesFor(MysteryStandRecipe.Type.INSTANCE);
        registration.addRecipes(IncantationCategory.MYSTERY_STAND_TYPE, incantationRecipes);

    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(MysteryStandScreen.class, 49,35,24,15, IncantationCategory.MYSTERY_STAND_TYPE);
        registration.addRecipeClickArea(MysteryStandScreen.class, 103,35,24,15, IncantationCategory.MYSTERY_STAND_TYPE);
    }
}
